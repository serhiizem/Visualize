package com.algorithms.controller;

import java.util.concurrent.TimeUnit;

public class BubbleSort implements Sorting {

    @Override
    public Comparable[] sort(Comparable[] array) {

        for (int i = array.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if(array[j].compareTo(array[j + 1])  > 0) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    swap(j, j + 1, array);
                }
            }
        }

        return array;
    }
}