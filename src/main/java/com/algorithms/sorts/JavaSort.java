package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.entity.SortRepresentation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.nanoTime;

@Component("javaSort")
public class JavaSort extends Queueable implements Sorting {

    public JavaSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Override
    public void sort(Comparable[] array) {
        long startTime = nanoTime();
        this.putIntermediateResultInAQueue(array, nanoTime() - startTime);
        Arrays.sort(array);
        this.putIntermediateResultInAQueue(array, nanoTime() - startTime);
    }
}
