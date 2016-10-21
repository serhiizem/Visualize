package com.algorithms.sorts;

import org.springframework.stereotype.Component;

@Component
public class SortInvoker {

    public void startSortingAlgorithm(Sorting sorting) {
        sorting.sort();
    }
}
