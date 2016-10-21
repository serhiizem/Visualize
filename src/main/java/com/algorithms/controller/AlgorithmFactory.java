package com.algorithms.controller;

import com.algorithms.sorts.BubbleSort;
import com.algorithms.sorts.InsertionSort;
import com.algorithms.sorts.MergeSort;
import com.algorithms.sorts.Sortable;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.AlgorithmType;
import com.algorithms.util.SortRepresentation;

public class AlgorithmFactory {

    public static Sorting getAlgorithm(SortRepresentation sortRepresentation, AlgorithmType type) {
        switch (type) {
            case MERGE_SORT:
                return new MergeSort(sortRepresentation);
            case INSERTION_SORT:
                return new InsertionSort(sortRepresentation);
            case SELECTION_SORT:
                return new MergeSort(sortRepresentation);
            case BUBBLE_SORT:
                return new BubbleSort(sortRepresentation);
            default:
                return null;
        }
    }
}
