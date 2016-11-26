package com.algorithms.sorts;

import com.algorithms.annotations.Sorter;
import com.algorithms.util.Queue;
import com.algorithms.entity.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.swap;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.nanoTime;

/**
 * Default implementation of the reversed bubble sort
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@Component("bubbleSortReverse")
public class BubbleSortReverse extends Queueable implements Sorting {

    @Autowired
    public BubbleSortReverse(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Sorter
    @Override
    public void sort(Comparable[] array) {
        long startTime = nanoTime();
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(Sorting.isLess(array[j + 1], array[j])) {
                    this.putIntermediateResultInAQueue(array);
                    swap(array, j, j + 1);
                }
            }
        }
        this.elapsedTime = nanoTime() - startTime;
        this.putIntermediateResultInAQueue(array);
    }
}