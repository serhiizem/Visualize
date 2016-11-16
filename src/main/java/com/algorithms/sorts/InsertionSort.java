package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.isLess;
import static com.algorithms.sorts.Sorting.swap;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.setOut;

@Component("insertionSort")
public class InsertionSort extends Queueable implements Sorting {

    @Autowired
    public InsertionSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    public void sort(Comparable[] array) {
        startTime = currentTimeMillis();

        for(int i = 1; i < array.length; i++) {
            for(int j = i; j > 0 && isLess(array[j], array[j - 1]); j--) {
                this.putIntermediateResultInAQueue(array, currentTimeMillis() - startTime);
                swap(array, j, j - 1);
            }
        }
        this.putIntermediateResultInAQueue(array, currentTimeMillis() - startTime);
    }
}