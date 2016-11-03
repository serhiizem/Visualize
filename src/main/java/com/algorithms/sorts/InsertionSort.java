package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("insertionSort")
public class InsertionSort extends Queueable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(InsertionSort.class);

    @Autowired
    public InsertionSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    public void sort(Integer[] array) {

        for(int x = 1; x < array.length; x++) {

            Integer temp = array[x];
            int j = x;
            while ((j > 0) && array[j - 1] > temp) {
                array[j] = array[j - 1];
                this.putSortRepresentationInAQueue(array);
                j--;
            }
            array[j] = temp;
            this.putSortRepresentationInAQueue(array);
        }
    }
}