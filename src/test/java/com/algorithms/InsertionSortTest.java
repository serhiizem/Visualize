package com.algorithms;

import com.algorithms.sorts.InsertionSort;
import com.algorithms.util.SortRepresentation;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InsertionSortTest {

    private SortRepresentation sortRepresentation;

    private static Integer[] VALID_INPUT = new Integer[] {80, 70, 60, 30, 20, 10};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};

    @Before
    public void setUp() throws Exception {
        sortRepresentation = new SortRepresentation(VALID_INPUT);
    }

    @Test
    public void shouldReturnSortedArrayOnValidInput() {

        InsertionSort insertionSort = new InsertionSort(sortRepresentation);
        insertionSort.sort();
        Integer[] result = insertionSort.getResult();

        Assert.assertThat(result, Matchers.is(CORRECT_RESULT));
    }
}
