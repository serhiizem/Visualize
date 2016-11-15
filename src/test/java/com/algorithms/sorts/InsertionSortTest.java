package com.algorithms;

import com.algorithms.sorts.InsertionSort;
import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InsertionSortTest {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private InsertionSort insertionSort;

    private static Integer[] VALID_INPUT = new Integer[] {80, 70, 60, 30, 20, 10};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
        insertionSort = new InsertionSort(sortRepresentationQueue);
    }

    @Test
    public void shouldReturnSortedArrayOnValidInput() {
        insertionSort.sort(VALID_INPUT);

        Integer[] lastElementInQueue = sortRepresentationQueue.getLast().getIntermediateResult();

        assertThat(lastElementInQueue, is(CORRECT_RESULT));
    }
}