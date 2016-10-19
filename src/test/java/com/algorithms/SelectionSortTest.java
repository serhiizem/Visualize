package com.algorithms;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class SelectionSortTest {

    private static Integer[] VALID_INPUT = new Integer[] {20, 30, 70, 10, 80, 60};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};

    @Test
    public void shouldSortOnValidInput() {

        SelectionSort selectionSort = new SelectionSort(VALID_INPUT);
        Integer[] sorted = selectionSort.sort();

        Assert.assertThat(sorted, Matchers.is(CORRECT_RESULT));
    }
}
