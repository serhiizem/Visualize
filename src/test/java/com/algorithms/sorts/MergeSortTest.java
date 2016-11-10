package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MergeSortTest {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private MergeSort mergeSort;

    private static Integer[] VALID_INPUT = new Integer[] {80, 70, 60, 30, 20, 10};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
        mergeSort = new MergeSort(sortRepresentationQueue);
    }

    @Test
    public void shouldContainSortedArrayInLastQueueItem() {
        mergeSort.sort(VALID_INPUT);

        Integer[] lastElementInQueue = sortRepresentationQueue.getLast().getIntermediateResult();

        assertThat(lastElementInQueue, is(CORRECT_RESULT));
    }
}