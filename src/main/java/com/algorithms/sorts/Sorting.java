package com.algorithms.sorts;

public interface Sorting {
    void sort();

    static void swap(int i, int j, Integer[] array) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
