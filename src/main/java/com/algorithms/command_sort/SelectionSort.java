package com.algorithms.command_sort;

import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

public class SelectionSort extends Sortable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(SelectionSort.class);
    private SortRepresentation sortRepresentation;

    public SelectionSort(SortRepresentation sortRepresentation) {
        this.sortRepresentation = sortRepresentation;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void sort() {

        sortRepresentation.setIntermediate(array);

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

            log.info("Representation is being renewed");
            sortRepresentation.setIntermediate(array);
            swap(x, minimum, array);
            log.info("Swap has performed");
        }
    }

    public void swap(int i, int j, Integer[] array) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
