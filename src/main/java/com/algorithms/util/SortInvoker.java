package com.algorithms.util;

import com.algorithms.sorts.Sorting;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
@Component
public class SortInvoker {
    /**
     * Responsible for the invocation of the specified sorting
     * algorithm upon the requested array of integers
     *
     * @param arrayToSort requested array to be sorted
     * @param sorting     implementation of the sorting algorithm
     */
    public void sortArrayWithTheGivenAlgorithm(Comparable[] arrayToSort, Sorting sorting) {
        Assert.notNull(arrayToSort, "Array should not be empty");
        sorting.sort(arrayToSort);
    }
}
