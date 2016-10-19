package com.algorithms;

public abstract class SortAlgorithm {

    public void swap(int i, int j, Integer[] array) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public abstract Integer[] sort(Integer[] arrayToSort);
}
