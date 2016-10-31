package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("selectionSort")
public class SelectionSort extends Sortable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(SelectionSort.class);

    @Autowired
    public SelectionSort(SortRepresentation sortRepresentation) {
        super(sortRepresentation);
    }

    @Override
    public void sort() {

        log.info("After aspect did his job: {}",
                sortRepresentation.isSortStarted());

        Integer[] array = sortRepresentation.getIntermediateResult();
//        sortRepresentation.setIntermediateResult(array);
//        sortRepresentation.setSortStarted(true);

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

            sortRepresentation.setIntermediateResult(array);
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
        return sortRepresentation.getIntermediateResult();
    }
}
