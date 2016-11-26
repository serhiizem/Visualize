package com.algorithms.sorts;

import com.algorithms.annotations.Sorter;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.isLess;
import static com.algorithms.sorts.Sorting.swap;
import static java.lang.System.nanoTime;

/**
 * Default implementation of the selection sort algorithm
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@Component("selectionSort")
public class SelectionSort extends Queueable implements Sorting {

    @Autowired
    public SelectionSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Sorter
    @Override
    public void sort(Comparable[] array) {
        long startTime = nanoTime();
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int minimum = i;
            for(int j = i; j < n; j++) {
                if(isLess(array[j], array[minimum])) minimum = j;
            }
            this.putIntermediateResultInAQueue(array);
            swap(array, i, minimum);
        }
        this.elapsedTime = nanoTime() - startTime;
    }
}
