package com.algorithms.controller;

import com.algorithms.sorts.BubbleSort;
import com.algorithms.sorts.InsertionSort;
import com.algorithms.sorts.MergeSort;
import com.algorithms.sorts.Sortable;
import com.algorithms.util.AlgorithmType;

public class AlgorithmFactory {

    public static Sortable getAlgorithm(AlgorithmType type) {
        switch (type) {
            case MERGE_SORT:
                return new MergeSort();
            case INSERTION_SORT:
                return new InsertionSort();
            case SELECTION_SORT:
                return new MergeSort();
            case BUBBLE_SORT:
                return new BubbleSort();
            default:
                return null;
        }
    }
}
