package com.algorithms.service;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.Range;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

@Service
public class XlsService implements Writing {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private Reflections reflections = new Reflections("com.algorithms");

    @Autowired
    public XlsService(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    @Override
    public void generateStatistics(Range range) {

        Comparable[] generatedArray = null;
        int arraySize = range.getArraySize();
        int minValue = range.getMinValue();
        int maxValue = range.getMaxValue();


        Reflections reflections = new Reflections("com.algorithms");

        HSSFWorkbook workbook = new HSSFWorkbook();

        for(Class<?> c: reflections.getSubTypesOf(GenerationStrategy.class)) {
            System.out.println(c.getCanonicalName());
            for(Method m: getMethods (c, withAnnotation(Filler.class))) {
                GenerationStrategy gs;
                workbook.createSheet(c.getSimpleName());
                try {
                    gs = (GenerationStrategy) c.newInstance();
                    generatedArray = (Comparable[]) m.invoke(gs, arraySize, minValue, maxValue);
                    runAllSortingAlgorithmsForTheInput(generatedArray);

                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private void runAllSortingAlgorithmsForTheInput(Comparable[] input) {

        for(Class<?> c: reflections.getSubTypesOf(Sorting.class)) {
            System.out.println(c.getCanonicalName());
            for(Method m: getMethods(c, withAnnotation(Sorter.class))) {
                Sorting sorting = null;
                try {
                    sorting = (Sorting) c.getConstructor(Queue.class)
                            .newInstance(sortRepresentationQueue);
                    m.invoke(sorting, new Object[] {input});
                } catch (InstantiationException | IllegalAccessException
                        | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
