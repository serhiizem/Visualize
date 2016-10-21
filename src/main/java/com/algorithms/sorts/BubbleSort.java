package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;
import java.util.concurrent.TimeUnit;

public class BubbleSort extends Sortable implements Sorting {

    private SortRepresentation sortRepresentation;

    public BubbleSort(Integer[] array, SortRepresentation sortRepresentation) {
        super(array);
        this.sortRepresentation = sortRepresentation;
    }

    @Override
    public void sort() {

        sortRepresentation.setSortStarted(true);

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(array[j] > array[j + 1]) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sortRepresentation.setIntermediate(array);
                    swap(j, j + 1, array);
                }
            }
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sortRepresentation.setSortStarted(false);
    }

    public void swap(int i, int j, Integer[] array) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Integer[] getResult() {
        return array;
    }
}
