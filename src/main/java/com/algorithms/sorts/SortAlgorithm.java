package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;

public abstract class SortAlgorithm {

    protected Integer[] array;
    protected SortRepresentation sortRepresentation;

    public SortAlgorithm() {
    }

    public SortAlgorithm(Integer[] array) {
        this.array = array;
        this.sortRepresentation = new SortRepresentation();
    }

    public void swap(int i, int j, Integer[] array) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public abstract Integer[] sort();
}
