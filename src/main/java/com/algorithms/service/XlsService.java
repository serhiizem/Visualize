package com.algorithms.service;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.Range;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import com.algorithms.util.SheetUtil;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * Manages process of writing result of the sorting
 * analysis into an xls file
 *
 * @author Zemlianiy
 * @version 1.0
 * @since
 */
@Service
@SuppressWarnings("unchecked")
public class XlsService implements Writing {

    private static final Logger log = LoggerFactory.getLogger(XlsService.class);

    public static final int FIRST_ROW_OF_THE_TABLE = 1;
    public static final int FIRST_COLUMN_OF_THE_TABLE = 1;
    public static final int NUMBER_OF_ALGORITHMS = 7;
    private Queue<SortRepresentation> sortRepresentationQueue;
    private Reflections reflections;
    private Random random;

    @Autowired
    public XlsService(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
        reflections = new Reflections("com.algorithms");
        random = new Random(47);
    }

    @Override
    public void generateStatistics() {
        int rowIndex = FIRST_ROW_OF_THE_TABLE;
        int columnIndex = FIRST_COLUMN_OF_THE_TABLE;

        for (Class<?> strategyClass : reflections.getSubTypesOf(GenerationStrategy.class)) {
            SheetUtil sheetUtil = new SheetUtil(strategyClass.getSimpleName());
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
                    this.sortArrayWithTheGivenAlgorithm(valuesToSort, sortClass, sortingAlgorithmObject);
                    Long elapsedTime = this.getElapsedTimeForTheGivenSort(sortingAlgorithmObject);

                    sheetUtil.createHeaderForRow(rowIndex, sortClass.getSimpleName());
                    sheetUtil.createHeaderForColumn(columnIndex, n);
                    sheetUtil.writeValueToCell(rowIndex++, columnIndex, elapsedTime);
                }
                columnIndex++;
                rowIndex = FIRST_ROW_OF_THE_TABLE;
            }
            columnIndex = FIRST_COLUMN_OF_THE_TABLE;

            sheetUtil.writeToFile("sortsReport.xls");
        }
    }

    private Long getElapsedTimeForTheGivenSort(Sorting sortingAlgorithmObject) {
        Long elapsedTime = null;
        try {
            elapsedTime = (Long) sortingAlgorithmObject.getClass().getSuperclass()
                    .getMethod("getElapsedTime").invoke(sortingAlgorithmObject);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.info("You have tried to invoke getElapsedTime() method, but it has failed." +
                    "It may be caused by the number of facts: 1) There is no such method on " +
                    "the class you are trying to invoke it on.  2) Given method has been declared " +
                    "private 3) There has been an error during an actual work of the given method. " +
                    "In this case please, check an implementation once again. Initial cause: " +
                    e.getCause());
        }
        return elapsedTime;
    }

    private void sortArrayWithTheGivenAlgorithm(Comparable[] arrayToSort,
                                                Class algorithmClass,
                                                Sorting objectToInstantiateOn) {
        try {
            Method[] declaredMethods = algorithmClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                Sorter sorterAnnotation = method.getAnnotation(Sorter.class);
                if (sorterAnnotation != null) {
                    method.invoke(objectToInstantiateOn, new Object[]{arrayToSort});
                }
            }} catch(IllegalAccessException | InvocationTargetException e){
            log.info("There has been an error during an invocation of the sorter method." +
                    "Please, check if a sorter method you are trying to invoke has " +
                    "not been declared private. This error may also occur if there has " +
                    "been an error during an actual work of the given method. In this " +
                    "case please, check an implementation once again. Initial cause: " +
                    e.getCause());
        }
    }

    private Comparable[] createArrayFromRangeUsingGivenStrategy(Range range,
                                                                Class strategyClass) {
        Comparable[] generatedArray = null;
        try {
            Method[] declaredMethods = strategyClass.getDeclaredMethods();
            for (Method method : declaredMethods) {
                Filler fillerAnnotation = method.getAnnotation(Filler.class);
                if (fillerAnnotation != null) {
                    GenerationStrategy generationStrategy =
                            this.instantiateStrategy(strategyClass);
                    generatedArray = (Comparable[]) method.invoke(generationStrategy,
                            range.getArraySize(), range.getMinValue(), range.getMaxValue());
                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.info("There has been an error during an invocation of the filler method." +
                    "Please, check if a filler method you are trying to invoke has " +
                    "not been declared private. This error may also occur if there has " +
                    "been an error during an actual work of the given method. In this " +
                    "case please, check an implementation once again. Initial cause: " +
                    e.getCause());
        }
        return generatedArray;
    }

    private GenerationStrategy instantiateStrategy(Class strategyClass) {
        GenerationStrategy generationStrategy = null;
        try {
            generationStrategy =
                    (GenerationStrategy) strategyClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.info("GenerationStrategy object failed to be instantiated." +
                    "Please, check whether the class you are trying to instantiate " +
                    "has a nullary constructor or you are not trying to instantiate " +
                    "an abstract class or interface. Initial cause: " + e.getCause());
        }
        return generationStrategy;
    }

    private Sorting instantiateSortingAlgorithm(Class sortingClass) {
        Sorting sortingAlgorithm = null;
        try {
            sortingAlgorithm = (Sorting) sortingClass.getConstructor(Queue.class)
                    .newInstance(sortRepresentationQueue);
        } catch (InstantiationException | IllegalAccessException |
                NoSuchMethodException | InvocationTargetException e) {
            log.info("Sorting object failed to be instantiated." +
                    "Please check whether a correct parameter type has been specified for the" +
                    "constructor, nor you are not trying to instantiate " +
                    "an abstract class or interface. The following may also occur if there is " +
                    "no such method under the requested class. Initial cause: " + e.getCause());
        }
        return sortingAlgorithm;
    }

    private Range getSampleDataRange(int rangeSize) {
        int minValue = random.nextInt(rangeSize);
        // max value should be four times larger than min value
        int maxValue = random.nextInt(rangeSize) + 4 * rangeSize;

        return new Range(rangeSize, minValue, maxValue);
    }
}
