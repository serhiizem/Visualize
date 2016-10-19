package com.algorithms;

public abstract class SortAlgorithm {

    private Integer[] array;

    public SortAlgorithm(Integer[] array) {
        this.array = array;
    }

    public void swap(int i, int j, Integer[] array) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Integer[] getCurrentPartition() {
        return this.array;
    }

    public abstract Integer[] sort(Integer[] arrayToSort);
}
