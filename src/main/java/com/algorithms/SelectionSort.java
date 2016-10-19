package com.algorithms;

import java.util.concurrent.TimeUnit;

public class SelectionSort extends SortAlgorithm {

    public SelectionSort(Integer[] array) {
        super(array);
    }

    @Override
    public Integer[] sort(Integer[] array) {

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
            swap(x, minimum, array);
        }

        return array;
    }
}