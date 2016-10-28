package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("insertionSort")
public class InsertionSort extends Sortable implements Sorting {

    public InsertionSort() {}

    @Autowired
    public InsertionSort(SortRepresentation sortRepresentation) {
        super(sortRepresentation);
    }

    @Override
    public void sort() {

        sortRepresentation.setSortStarted(true);

        Integer[] array = sortRepresentation.getIntermediateResult();

        for(int x = 1; x < array.length; x++) {

//            5, 10, 4, 8, 3
            Integer temp = array[x];
            int j = x;
            while ((j > 0) && array[j - 1] > temp) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                array[j] = array[j - 1];
                sortRepresentation.setIntermediateResult(array);
                j--;
            }
            array[j] = temp;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sortRepresentation.setIntermediateResult(array);
        }
        sortRepresentation.setSortStarted(false);
    }

    public Integer[] getResult() {
        return sortRepresentation.getIntermediateResult();
    }
}
