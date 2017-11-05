package com.algorithms.sorts;

import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SelectionSortTest extends SortTest {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private SelectionSort selectionSort;

    private static Integer[] VALID_INPUT = new Integer[]{20, 30, 70, 10, 80, 60};
    private static Integer[] CORRECT_RESULT = new Integer[]{10, 20, 30, 60, 70, 80};
//    private static Integer[] CORRECT_RESULT = getCorrectlySortedArrayFromInput(VALID_INPUT);

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
        selectionSort = new SelectionSort(sortRepresentationQueue);
    }

    @Test
    public void lastElementPutInTheQueueShouldBeFullySorted() {
        //when
        selectionSort.sort(VALID_INPUT);
        Comparable[] lastElementInTheQueue = this.getSortedArrayFromQueue(sortRepresentationQueue);

        //then
        assertThat(lastElementInTheQueue, is(CORRECT_RESULT));
    }
}
