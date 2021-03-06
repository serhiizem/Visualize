package com.algorithms.service;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.Entry;
import com.algorithms.entity.Range;
import com.algorithms.entity.Scatter;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.service.interfaces.Writing;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static com.algorithms.service.XlsService.HEADER_WIDTH;

/**
 * This class is responsible for sorting statistics generation.
 *
 * <p>A specific statistics representation is generated by invoking a {@link #writeReport()}
 * method</p>
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
@Service
public class StatisticsService implements Writing {

    public static final String OUTPUT_FILE = "sortsReport.xls";

    private static final Logger log = LoggerFactory.getLogger(StatisticsService.class);

    private ReflectionService reflection;
    private Queue<SortRepresentation> queue;
    private XlsService sheetUtil = new XlsService();

    @Autowired
    public StatisticsService(Queue<SortRepresentation> sortRepresentationQueue,
                             ReflectionService reflectionService) {
        this.reflection = reflectionService;
        this.queue = sortRepresentationQueue;
    }

    private List<Entry> getPerformanceResults(List<Method> sortingMethods,
                                              Comparable[] valuesToSort) {

        List<Entry> entries = new ArrayList<>();

        sortingMethods.forEach(method -> {
            Class<?> sortingClass = method.getDeclaringClass();
            Sorting sortingAlgorithmObject = reflection
                    .instantiateSortingAlgorithm(sortingClass, queue);
            reflection.preventQueueToBeFilledByTheSort(sortingAlgorithmObject);
            reflection.sortArrayWithTheGivenAlgorithm(valuesToSort, sortingAlgorithmObject);
            Long elapsedTime = reflection.getElapsedTimeForTheGivenSort(sortingAlgorithmObject);
            entries.add(new Entry(sortingClass.getSimpleName(), elapsedTime));
        });

        return entries;
    }

    @Override
    public void writeReport() {

        List<Method> generatorMethods = reflection
                .getAllMethodsOfClassWithTheGivenAnnotation(GenerationStrategy.class, Filler.class);

        List<Method> sortingMethods = reflection
                .getAllMethodsOfClassWithTheGivenAnnotation(Sorting.class, Sorter.class);

        for (Method generatorMethod : generatorMethods) {

            Class<?> generatorClass = generatorMethod.getDeclaringClass();
            sheetUtil.createActiveSheet(generatorClass.getSimpleName());
            sheetUtil.createMultipleRows(sortingMethods.size() + HEADER_WIDTH);
            Scatter scatter = new Scatter();
            for (Range range : scatter) {
                Comparable[] generatedArray = reflection
                        .createArrayFromRangeUsingGivenStrategy(range, generatorClass);
                Comparable[] valuesToSort = generatedArray.clone();
                List<Entry> sortingResults = this.getPerformanceResults(sortingMethods, valuesToSort);
                sheetUtil.fillColumn(sortingResults, "size: " + range.getArraySize());
            }
            sheetUtil.createChart();
        }
        sheetUtil.writeToFile(OUTPUT_FILE);
    }
}
