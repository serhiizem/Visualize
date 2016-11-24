package com.algorithms.reflection;

import com.algorithms.annotations.Filler;
import com.algorithms.annotations.Sorter;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.generation.GenerationStrategy;
import com.algorithms.sorts.Queueable;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.reflections.ReflectionUtils.getMethods;
import static org.reflections.ReflectionUtils.withAnnotation;

public class SortInvocation {

    private Queue<SortRepresentation> sortRepresentationQueue;

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
    }

    @Test
    public void shouldInvokeAllTheMethodsAnnotatedWithAtSorterAndAtFiller()
            throws InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {

        Comparable[] returnedArray = null;
        Reflections reflections = new Reflections("com.algorithms");
        for(Class<?> c: reflections.getSubTypesOf(GenerationStrategy.class)) {
            System.out.println(c.getCanonicalName());
            for(Method m: getMethods (c, withAnnotation(Filler.class))) {
                GenerationStrategy gs = (GenerationStrategy) c.newInstance();
                returnedArray = (Comparable[]) m.invoke(gs, 10, 20, 50);
            }
        }

        for(Class<?> c: reflections.getSubTypesOf(Sorting.class)) {
            System.out.println(c.getCanonicalName());
            for(Method m: getMethods(c, withAnnotation(Sorter.class))) {
                Sorting sorting = (Sorting) c.getConstructor(Queue.class)
                        .newInstance(sortRepresentationQueue);
                m.invoke(sorting, new Object[] {returnedArray});
            }
        }
    }
}
