package com.algorithms.sorts;

public interface Sorting {

    void sort(Comparable[] array);

    static void swap(Comparable[] array, int i, int j) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    static boolean isLess(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }
}