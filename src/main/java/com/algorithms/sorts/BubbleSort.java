package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.swap;

@Component("bubbleSort")
public class BubbleSort implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(BubbleSort.class);

    private Queue<SortRepresentation> sortRepresentationQueue;

    @Autowired
    public BubbleSort(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    public void sort(Integer[] array) {

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(array[j] > array[j + 1]) {
                    putSortRepresentationInAQueue(array);
                    swap(j, j + 1, array);
                }
            }
        }
    }

    private void putSortRepresentationInAQueue(Integer[] intermediateResult) {
        SortRepresentation sortRepresentation = new SortRepresentation(intermediateResult);
        sortRepresentationQueue.enqueue(sortRepresentation);
    }
}
