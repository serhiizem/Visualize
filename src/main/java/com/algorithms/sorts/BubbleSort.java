package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

public class BubbleSort extends Sortable implements Sorting {

    private Long startTime;
    private Long elapsedTime;

    public BubbleSort() {
    }

    public BubbleSort(SortRepresentation sortRepresentation) {
        super(sortRepresentation);
    }

    public void sort() {

        sortRepresentation.setSortStarted(true);
        startTime = System.currentTimeMillis();

        Integer[] array = sortRepresentation.getIntermediate();

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
        elapsedTime = System.currentTimeMillis() - startTime;
        sortRepresentation.getElapsedTime();
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
