package com.algorithms.config;

import com.algorithms.sorts.Sorting;
import com.algorithms.util.AlgorithmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DefaultAlgorithmFactory implements AlgorithmFactory {

    @Qualifier("bubbleSort")        private Sorting bubbleSort;
    @Qualifier("bubbleSortReverse") private Sorting bubbleSortReverse;
    @Qualifier("insertionSort")     private Sorting insertionSort;
    @Qualifier("selectionSort")     private Sorting selectionSort;
    @Qualifier("mergeSort")         private Sorting mergeSort;
    @Qualifier("quickSort")         private Sorting quickSort;
    @Qualifier("javaSort")          private Sorting javaSort;

    @Autowired
    public DefaultAlgorithmFactory(Sorting bubbleSort,
                                   Sorting bubbleSortReverse,
                                   Sorting insertionSort,
                                   Sorting selectionSort,
                                   Sorting mergeSort,
                                   Sorting quickSort,
                                   Sorting javaSort) {
        this.bubbleSort = bubbleSort;
        this.bubbleSortReverse = bubbleSortReverse;
        this.insertionSort = insertionSort;
        this.selectionSort = selectionSort;
        this.mergeSort = mergeSort;
        this.quickSort = quickSort;
        this.javaSort = javaSort;
    }

    @Override
    public Sorting getAlgorithm(AlgorithmType type) {
        switch (type) {
            case BUBBLE_SORT:
                return bubbleSort;
            case BUBBLE_SORT_REVERSE:
                return bubbleSortReverse;
            case INSERTION_SORT:
                return insertionSort;
            case SELECTION_SORT:
                return selectionSort;
            case MERGE_SORT:
                return mergeSort;
            case QUICK_SORT:
                return quickSort;
            case JAVA_SORT:
                return javaSort;
            default:
                return null;
        }
    }
}