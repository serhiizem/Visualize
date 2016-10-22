package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SelectionSort extends Sortable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(SelectionSort.class);

    public SelectionSort(SortRepresentation sortRepresentation) {
        super(sortRepresentation);
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void sort() {

        Integer[] array = sortRepresentation.getIntermediate();
        sortRepresentation.setIntermediate(array);
        sortRepresentation.setSortStarted(true);

        for (int x = 0; x < array.length; x++) {

            int minimum = x;

            for(int y = x; y < array.length; y++) {
                if(array[minimum] > array[y]) minimum = y;
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sortRepresentation.setIntermediate(array);
            swap(x, minimum, array);
        }
        sortRepresentation.setSortStarted(false);
    }

    public void swap(int i, int j, Integer[] array) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Integer[] getResult() {
        return sortRepresentation.getIntermediate();
    }
}
