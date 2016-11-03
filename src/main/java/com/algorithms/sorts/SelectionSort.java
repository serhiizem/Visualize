package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.algorithms.sorts.Sorting.swap;

@Component("selectionSort")
public class SelectionSort extends Sortable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(SelectionSort.class);

    private Queue<SortRepresentation> sortRepresentationQueue;

    @Autowired
    public SelectionSort(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    @Override
    public void sort(Integer[] array) {

        for (int x = 0; x < array.length; x++) {

            int minimum = x;

            for(int y = x; y < array.length; y++) {
                if(array[minimum] > array[y]) minimum = y;
            }

            this.putSortRepresentationInAQueue(array);
            swap(x, minimum, array);
        }
    }

    private void putSortRepresentationInAQueue(Integer[] intermediateResult) {
        sortRepresentationQueue.enqueue(new SortRepresentation(intermediateResult.clone()));
    }
}
