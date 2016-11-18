package com.algorithms.sorts;


import com.algorithms.util.Queue;
import com.algorithms.entity.SortRepresentation;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SelectionSortTest {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private SelectionSort selectionSort;

    private static Integer[] VALID_INPUT = new Integer[] {20, 30, 70, 10, 80, 60};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
        selectionSort = new SelectionSort(sortRepresentationQueue);
    }

    @Test
    public void lastElementPutInTheQueueShouldBeFullySorted() {

        selectionSort.sort(VALID_INPUT);

        Comparable[] lastElementInTheQueue = sortRepresentationQueue.getLast().getIntermediateResult();

        assertThat(lastElementInTheQueue, is(CORRECT_RESULT));
    }
}
