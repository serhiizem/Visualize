package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.entity.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.isLess;
import static com.algorithms.sorts.Sorting.swap;

@Component("bubbleSort")
public class BubbleSort extends Queueable implements Sorting {

    @Autowired
    public BubbleSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    public void sort(Comparable[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (isLess(array[j], (array[j-1]))) {
                    this.putIntermediateResultInAQueue(array);
                    swap(array, j, j - 1);
                }
            }
        }
        this.putIntermediateResultInAQueue(array);
    }
}
