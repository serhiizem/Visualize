package com.algorithms.util;

import com.algorithms.sorts.Sorting;
import org.springframework.stereotype.Component;

@Component
public class SortInvoker {
    public void sortArrayWithTheGivenAlgorithm(Integer[] arrayToSort, Sorting sorting) {
        sorting.sort(arrayToSort);
    }
}
