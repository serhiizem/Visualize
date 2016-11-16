package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.isLess;
import static com.algorithms.sorts.Sorting.swap;
import static java.lang.System.currentTimeMillis;

@Component("selectionSort")
public class SelectionSort extends Queueable implements Sorting {

    @Autowired
    public SelectionSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Override
    public void sort(Comparable[] array) {
        startTime = currentTimeMillis();
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int minimum = i;
            for(int j = i; j < n; j++) {
                if(isLess(array[j], array[minimum])) minimum = j;
            }
            putIntermediateResultInAQueue(array, currentTimeMillis() - startTime);
            swap(array, i, minimum);
        }
    }
}
