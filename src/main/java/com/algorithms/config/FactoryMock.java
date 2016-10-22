package com.algorithms.config;

import com.algorithms.sorts.BubbleSort;
import com.algorithms.sorts.InsertionSort;
import com.algorithms.sorts.MergeSort;
import com.algorithms.sorts.SelectionSort;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.AlgorithmType;
import com.algorithms.util.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryMock {

    private BubbleSort bubbleSort;

    @Autowired
    public FactoryMock(BubbleSort bubbleSort) {
        this.bubbleSort = bubbleSort;
    }

    public Sorting getAlgorithm(AlgorithmType type) {
        switch (type) {
            case BUBBLE_SORT:
                return bubbleSort;
            default:
                return null;
        }
    }
}