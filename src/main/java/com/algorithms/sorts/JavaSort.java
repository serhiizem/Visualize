package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.lang.System.currentTimeMillis;

@Component("javaSort")
public class JavaSort extends Queueable implements Sorting {

    public JavaSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Override
    public void sort(Comparable[] array) {
        startTime = currentTimeMillis();
        Arrays.sort(array);
        putIntermediateResultInAQueue(array, startTime - currentTimeMillis());
    }
}
