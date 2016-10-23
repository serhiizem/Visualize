package com.algorithms.config;

import com.algorithms.sorts.Sorting;
import com.algorithms.util.AlgorithmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FactoryMock {

    @Qualifier("bubbleSort")    private Sorting bubbleSort;
    @Qualifier("insertionSort") private Sorting insertionSort;
    @Qualifier("selectionSort") private Sorting selectionSort;
    @Qualifier("mergeSort")     private Sorting mergeSort;

    @Autowired
    public FactoryMock(Sorting bubbleSort,
                       Sorting insertionSort,
                       Sorting selectionSort,
                       Sorting mergeSort) {
        this.bubbleSort = bubbleSort;
        this.insertionSort = insertionSort;
        this.selectionSort = selectionSort;
        this.mergeSort = mergeSort;
    }

    public Sorting getAlgorithm(AlgorithmType type) {
        switch (type) {
            case BUBBLE_SORT:
                return bubbleSort;
            case INSERTION_SORT:
                return insertionSort;
            case SELECTION_SORT:
                return selectionSort;
            case MERGE_SORT:
                return mergeSort;
            default:
                return null;
        }
    }
}