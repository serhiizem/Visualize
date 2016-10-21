package com.algorithms;

import com.algorithms.sorts.BubbleSort;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTest {

    private static Integer[] VALID_INPUT = new Integer[] {20, 30, 70, 10, 80, 60};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};

    @Test
    public void shouldReturnSortedArrayOnValidInput() {

        BubbleSort bubbleSort = new BubbleSort(VALID_INPUT);
        bubbleSort.sort();
        Integer[] result = bubbleSort.getResult();

        Assert.assertThat(result, Matchers.is(CORRECT_RESULT));
    }
}
