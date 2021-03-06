package com.algorithms.reflection;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.Method;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

public class SortInvocation {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private Reflections reflections;

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
        reflections = new Reflections("com.algorithms");
    }

    @Test
    public void shouldInvokeAllTheMethodsAnnotatedWithAtSorterAndAtFiller()
            throws Exception {

        Comparable[] generatedArray = null;
        for (Class<?> c : reflections.getSubTypesOf(GenerationStrategy.class)) {
            for (Method m : getMethods(c, withAnnotation(Filler.class))) {
                GenerationStrategy gs = (GenerationStrategy) c.newInstance();
                generatedArray = (Comparable[]) m.invoke(gs, 10, 20, 50);
            }
        }

        for (Class<?> c : reflections.getSubTypesOf(Sorting.class)) {
            Sorting sorting = (Sorting) c.getConstructor(Queue.class)
                    .newInstance(sortRepresentationQueue);
            Method setAnalysedMethod = c.getSuperclass().getDeclaredMethod("setAnalysed", Boolean.class);
            setAnalysedMethod.invoke(sorting, true);
            for (Method m : getMethods(c, withAnnotation(Sorter.class))) {
                m.invoke(sorting, new Object[]{generatedArray});
            }
        }
    }
}
