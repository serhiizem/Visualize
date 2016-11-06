package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.swap;

@Component("bubbleSort")
public class BubbleSort extends Queueable implements Sorting {

    @Autowired
    public BubbleSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    public void sort(Integer[] array) {

        long nanoTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if ((array[j] < array[j-1])) {
                    putIntermediateResultInAQueue(array, System.nanoTime() - nanoTime);
                    swap(j, j - 1, array);
                }
            }
        }
        putIntermediateResultInAQueue(array, System.nanoTime() - nanoTime);
    }
}
