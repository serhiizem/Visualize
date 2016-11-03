package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.swap;

@Component("selectionSort")
public class SelectionSort extends Queueable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(SelectionSort.class);

    @Autowired
    public SelectionSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Override
    public void sort(Integer[] array) {

        for (int x = 0; x < array.length; x++) {

            int minimum = x;

            for(int y = x; y < array.length; y++) {
                if(array[minimum] > array[y]) minimum = y;
            }
            putSortRepresentationInAQueue(array);
            swap(x, minimum, array);
        }
    }
}
