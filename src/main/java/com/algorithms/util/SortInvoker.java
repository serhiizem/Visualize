package com.algorithms.util;

import com.algorithms.exceptions.NonExistingArrayException;
import com.algorithms.sorts.Sorting;
import org.springframework.stereotype.Component;

/**
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@Component
public class SortInvoker {
    /**
     *
     * Responsible for the invocation of the specified sorting
     * algorithm upon the requested array of integers
     *
     * @param arrayToSort requested array to be sorted
     * @param sorting     implementation of the sorting algorithm
     */
    public void sortArrayWithTheGivenAlgorithm(Integer[] arrayToSort, Sorting sorting) {
        if(arrayToSort.length == 0) {
            throw new NonExistingArrayException("Your array should have a length");
        }
        sorting.sort(arrayToSort);
    }
}
