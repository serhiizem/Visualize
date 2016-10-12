package com.algorithms.controller;

public interface Sorting<T extends Comparable> {

    T[] sort(Comparable[] array);

    default void swap(int i, int j, Comparable[] array) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
