package com.algorithms.sorts;

import com.algorithms.annotations.Sorter;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.isLess;
import static com.algorithms.sorts.Sorting.swap;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.nanoTime;

/**
 * Default implementation of the bubble sort algorithm
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@Component("bubbleSort")
public class BubbleSort extends Queueable implements Sorting {

    @Autowired
    public BubbleSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Sorter
    @Override
    public void sort(Comparable[] array) {

        long startTime = nanoTime();
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (isLess(array[j], (array[j-1]))) {
                    this.putIntermediateResultInAQueue(array, nanoTime() - startTime);
                    swap(array, j, j - 1);
                }
            }
        }
        this.putIntermediateResultInAQueue(array, nanoTime() - startTime);
    }
}
