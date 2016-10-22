package com.algorithms;

import com.algorithms.sorts.BubbleSort;
import com.algorithms.util.SortRepresentation;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BubbleSortTest {

    private SortRepresentation sortRepresentation;

    private static Integer[] VALID_INPUT = new Integer[] {20, 30, 70, 10, 80, 60};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};

    @Before
    public void setUp() throws Exception {
        sortRepresentation = new SortRepresentation(VALID_INPUT);
    }

    @Test
    public void shouldReturnSortedArrayOnValidInput() {

        BubbleSort bubbleSort = new BubbleSort(sortRepresentation);
        bubbleSort.sort();
        Integer[] result = bubbleSort.getResult();

        Assert.assertThat(result, Matchers.is(CORRECT_RESULT));
    }
}