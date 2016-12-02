package com.algorithms.service;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.Range;
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
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

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

    @Autowired
    public SortsStatisticsService(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
        reflections = new Reflections("com.algorithms");
        random = new Random(47);
    }

    @Override
    public void writeReport() {
        int rowIndex = FIRST_ROW_OF_THE_TABLE;
        int columnIndex = FIRST_COLUMN_OF_THE_TABLE;

        for (Class<?> strategyClass : reflections.getSubTypesOf(GenerationStrategy.class)) {
            XlsService sheetUtil = new XlsService(strategyClass.getSimpleName());
            sheetUtil.createMultipleRows(NUMBER_OF_ALGORITHMS);

            log.info("Starting filler: {}", strategyClass.getSimpleName());
            for (int n = 5; n < 25; n += 5) {
                Range range = getSampleDataRange(n);
                Comparable[] generatedArray = createArrayFromRangeUsingGivenStrategy(range, strategyClass);

                for (Class<?> sortClass : reflections.getSubTypesOf(Sorting.class)) {
                    Comparable[] valuesToSort = generatedArray.clone();
                    log.info("Starting sortClass: {}, for the number of elements: {}",
                            sortClass.getSimpleName(), n);
                    Sorting sortingAlgorithmObject = this.instantiateSortingAlgorithm(sortClass);
                    this.preventQueueToBeFilledByTheSort(sortingAlgorithmObject);
                    this.sortArrayWithTheGivenAlgorithm(valuesToSort, sortingAlgorithmObject);
                    Long elapsedTime = this.getElapsedTimeForTheGivenSort(sortingAlgorithmObject);

                    sheetUtil.createHeaderForRow(rowIndex, sortClass.getSimpleName());
                    sheetUtil.createHeaderForColumn(columnIndex, n);
                    sheetUtil.writeValueToCell(rowIndex++, columnIndex, elapsedTime);
                }
                columnIndex++;
                rowIndex = FIRST_ROW_OF_THE_TABLE;
            }
            columnIndex = FIRST_COLUMN_OF_THE_TABLE;

            sheetUtil.createChart();
            sheetUtil.writeToFile(OUTPUT_FILE);
        }
    }

    private void preventQueueToBeFilledByTheSort(Sorting sortingObject) {
        Method setAnalysed = null;
        try {
            setAnalysed =
                    this.getMethodFromObjectParentClass(sortingObject, "setAnalysed", Boolean.class);
            setAnalysed.invoke(sortingObject, true);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new QueueDeactivatingInvocationException(sortingObject, setAnalysed, e);
        }
    }

    private Long getElapsedTimeForTheGivenSort(Sorting sortingAlgorithmObject) {
        Method elapsedTimeGetter = null;
        try {
            elapsedTimeGetter =
                    this.getMethodFromObjectParentClass(sortingAlgorithmObject, "getElapsedTime");
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

    private Comparable[] invokeGenerationMethodOnRange(GenerationStrategy generationStrategy,
                                                       Method method, Range range) {
        try {
            return (Comparable[]) method.invoke(generationStrategy,
                    range.getArraySize(), range.getMinValue(), range.getMaxValue());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new FillerInvocationException(generationStrategy, method, e);
        }
    }

    private Method getMethodFromObjectParentClass(Object object, String methodName, Class... args) {
        Method requestedMethod;
        try {
            requestedMethod = object.getClass().getSuperclass()
                    .getMethod(methodName, args);
            return requestedMethod;
        } catch (NoSuchMethodException e) {
            throw new NoSuchParentMethodException(object, methodName, e);
        }
    }

    private void sortArrayWithTheGivenAlgorithm(Comparable[] arrayToSort,
                                                Sorting sortingObject) {
        Method[] declaredMethods = sortingObject.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            Sorter sorterAnnotation = method.getAnnotation(Sorter.class);
            if (sorterAnnotation != null) {
                this.invokeSortingMethodOnArray(sortingObject, method, arrayToSort);
            }
        }
    }

    private Comparable[] createArrayFromRangeUsingGivenStrategy(Range range,
                                                                Class strategyClass) {
        Comparable[] generatedArray = null;
        GenerationStrategy generationStrategy;
        Method[] declaredMethods = strategyClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Filler fillerAnnotation = method.getAnnotation(Filler.class);
            if (fillerAnnotation != null) {
                generationStrategy =
                        this.instantiateStrategy(strategyClass);
                generatedArray =
                        this.invokeGenerationMethodOnRange(generationStrategy, method, range);
            }
        }
        return generatedArray;
    }

    private GenerationStrategy instantiateStrategy(Class strategyClass) {
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
