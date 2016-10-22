package com.algorithms.util;

import com.algorithms.sorts.BubbleSort;
import com.algorithms.sorts.InsertionSort;
import com.algorithms.sorts.MergeSort;
import com.algorithms.sorts.Sorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmFactory {

    private static SortRepresentation sortRepresentation;

    @Autowired
    public AlgorithmFactory(SortRepresentation sortRepresentation) {
        this.sortRepresentation = sortRepresentation;
    }

    public static Sorting getAlgorithm(AlgorithmType type) {
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
