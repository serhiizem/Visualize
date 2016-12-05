package com.algorithms.service;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.Range;
import com.algorithms.entity.Scatter;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.exceptions.ElapsedTimeInvocationException;
import com.algorithms.exceptions.FillerInvocationException;
import com.algorithms.exceptions.GenerationStrategyInvocationException;
import com.algorithms.exceptions.NoSuchParentMethodException;
import com.algorithms.exceptions.QueueDeactivatingInvocationException;
import com.algorithms.exceptions.SortingAlgorithmInstantiationException;
import com.algorithms.exceptions.SortingMethodInvocationException;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.service.interfaces.Writing;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Zemlianiy
 * @version 1.0
 * @since
 */
@Service
public class SortsStatisticsService implements Writing {

    private static final Logger log = LoggerFactory.getLogger(SortsStatisticsService.class);

    public static final int FIRST_ROW_OF_THE_TABLE = 1;
    public static final int FIRST_COLUMN_OF_THE_TABLE = 1;
    public static final int NUMBER_OF_ALGORITHMS = 7;
    public static final String OUTPUT_FILE = "sortsReport.xls";
    private Queue<SortRepresentation> sortRepresentationQueue;
    private Reflections reflections;
    private Random random;
    private XlsService xlsService;
    Scatter sampleRanges = new Scatter();
    private ReflectionService reflectionService = new ReflectionService();
    TestXls testXls = new TestXls();

    @Autowired
    public SortsStatisticsService(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
        reflections = new Reflections("com.algorithms");
        random = new Random(47);
//        xlsService = new XlsService();
    }

    public void invokeSorters() {

        createSheetsForAllGenerators();
        generateStats();
    }

    private void createSheetsForAllGenerators() {
        reflectionService.getSubclassesOfType(GenerationStrategy.class)
                .forEach(c -> testXls.createSheetWithName(c.getSimpleName()));
    }

    private void generateStats() {
        for (Class<?> strategy: reflectionService.getSubclassesOfType(GenerationStrategy.class)) {
            for (Range range : sampleRanges) {
                for (Method filler : reflectionService
                        .getAnnotatedMethodsForClass(Filler.class, strategy)) {

                    GenerationStrategy generationStrategy = reflectionService.instantiateStrategy(strategy);
                    Comparable[] generatedArray = reflectionService
                            .invokeGenerationMethodOnRange(generationStrategy, filler, range);

                    for (Class<?> sortingClass: reflectionService.getSubclassesOfType(Sorting.class)) {
                        for (Method sorting : reflectionService
                                .getAnnotatedMethodsForClass(Sorter.class, sortingClass)) {
                            Sorting sortInstance = reflectionService
                                    .instantiateSortingAlgorithm(sortingClass, sortRepresentationQueue);
                            reflectionService.invokeSortingMethodOnArray(sortInstance, sorting, generatedArray);
                            Long elapsedTime = reflectionService.getElapsedTimeForTheGivenSort(sortInstance);
                            xlsService.writeEntry(sortingClass.getSimpleName(), elapsedTime);
                        }
                    }
                }
            }
        }
    }

    private class TestXls {

        public static final int FIRST_ROW_OF_THE_TABLE = 1;
        public static final int FIRST_COLUMN_OF_THE_TABLE = 1;
        private XSSFWorkbook workbook = new XSSFWorkbook();

        public void createSheetWithName(String sheetName) {
            workbook.createSheet(sheetName);
        }
    }

    @Override
    public void writeReport() {
        int rowIndex = FIRST_ROW_OF_THE_TABLE;
        int columnIndex = FIRST_COLUMN_OF_THE_TABLE;


        for (Class<?> strategyClass : reflectionService.getSubclassesOfType(GenerationStrategy.class)) {

            XlsService sheetUtil = new XlsService(strategyClass.getSimpleName());
            sheetUtil.createMultipleRows(NUMBER_OF_ALGORITHMS);

            log.info("Starting filler: {}", strategyClass.getSimpleName());
            for (Range range: sampleRanges) {
                Comparable[] generatedArray = reflectionService
                        .createArrayFromRangeUsingGivenStrategy(range, strategyClass);

                for (Class<?> sortClass : reflectionService.getSubclassesOfType(GenerationStrategy.class)) {
                    Comparable[] valuesToSort = generatedArray.clone();
                    log.info("Starting sortClass: {}, for the number of elements: {}",
                            sortClass.getSimpleName(), range.getArraySize());
                    Sorting sortingAlgorithmObject = reflectionService
                            .instantiateSortingAlgorithm(sortClass, sortRepresentationQueue);
                    reflectionService.preventQueueToBeFilledByTheSort(sortingAlgorithmObject);
                    reflectionService.sortArrayWithTheGivenAlgorithm(valuesToSort, sortingAlgorithmObject);
                    Long elapsedTime = reflectionService.getElapsedTimeForTheGivenSort(sortingAlgorithmObject);

                    sheetUtil.createHeaderForRow(rowIndex, sortClass.getSimpleName());
                    sheetUtil.createHeaderForColumn(columnIndex, range.getArraySize());
                    sheetUtil.writeValueToCell(rowIndex++, columnIndex, elapsedTime);
                }
                columnIndex++;
                rowIndex = FIRST_ROW_OF_THE_TABLE;
            }
            columnIndex = FIRST_COLUMN_OF_THE_TABLE;

//            sheetUtil.createChart();
            sheetUtil.writeToFile(OUTPUT_FILE);
        }
    }

    private void preventQueueToBeFilledByTheSort(Sorting sortingObject) {
        Method setAnalysed = null;
        try {
            setAnalysed =
                    this.getSubclassMethodForGivenInstance(
                            "setAnalysed", sortingObject, Boolean.class);
            setAnalysed.invoke(sortingObject, true);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new QueueDeactivatingInvocationException(sortingObject, setAnalysed, e);
        }
    }

    private Long getElapsedTimeForTheGivenSort(Sorting sortingAlgorithmObject) {
        Method elapsedTimeGetter = null;
        try {
            elapsedTimeGetter =
                    this.getSubclassMethodForGivenInstance(
                            "getElapsedTime", sortingAlgorithmObject);
            return (Long) elapsedTimeGetter.invoke(sortingAlgorithmObject);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ElapsedTimeInvocationException(sortingAlgorithmObject, elapsedTimeGetter, e);
        }
    }

    private void invokeSortingMethodOnArray(Sorting sortingObject,
                                            Method method,
                                            Comparable[] arrayToSort) {
        try {
            method.invoke(sortingObject, new Object[]{arrayToSort});
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new SortingMethodInvocationException(sortingObject, method, e);
        }
    }

    private Method getSubclassMethodForGivenInstance(String methodName, Object instance, Class... args) {
        Method requestedMethod;
        try {
            requestedMethod = instance.getClass().getSuperclass()
                    .getMethod(methodName, args);
            return requestedMethod;
        } catch (NoSuchMethodException e) {
            throw new NoSuchParentMethodException(instance, methodName, e);
        }
    }

    private void sortArrayWithTheGivenAlgorithm(Comparable[] arrayToSort,
                                                Sorting sortingObject) {
        Class sortingClass = sortingObject.getClass();
        for (Method sorter: this.getAnnotatedMethodsForClass(Sorter.class, sortingClass)) {
            this.invokeSortingMethodOnArray(sortingObject, sorter, arrayToSort);
        }
    }

    private Comparable[] createArrayFromRangeUsingGivenStrategy(Range range,
                                                                Class strategyClass) {
        Comparable[] generatedArray = null;
        for (Method method : this.getAnnotatedMethodsForClass(Filler.class, strategyClass)) {
            GenerationStrategy generationStrategy =
                    this.instantiateStrategy(strategyClass);
            generatedArray =
                    this.invokeGenerationMethodOnRange(generationStrategy, method, range);
        }
        return generatedArray;
    }

    private List<Method> getAnnotatedMethodsForClass(Class<? extends Annotation> annotation,
                                                     Class<?> targetClass) {
        return reflections.getSubTypesOf(targetClass)
                .stream().map(Class::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .filter(m -> m.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

    private Comparable[] invokeGenerationMethodOnRange(GenerationStrategy generationStrategy,
                                                       Method method, Range range) {
        try {
            return (Comparable[]) method.invoke(generationStrategy,
                    range.getArraySize(), range.getMinValue(), range.getMaxValue());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new FillerInvocationException(generationStrategy, method, e);
        }
    }

    private GenerationStrategy instantiateStrategy(Class<?> strategyClass) {
        try {
            return (GenerationStrategy) strategyClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new GenerationStrategyInvocationException(strategyClass, e);
        }
    }

    private Sorting instantiateSortingAlgorithm(Class sortingClass) {
        try {
            return (Sorting) sortingClass.getConstructor(Queue.class)
                    .newInstance(sortRepresentationQueue);
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            throw new SortingAlgorithmInstantiationException(sortingClass, e);
        }
    }

    private Range getSampleDataRange(int rangeSize) {
        int minValue = random.nextInt(rangeSize);
        // max value should be four times larger than min value
        int maxValue = random.nextInt(rangeSize) + 4 * rangeSize;

        return new Range(rangeSize, minValue, maxValue);
    }
}
