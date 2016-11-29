package com.algorithms.sorts;

import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class InsertionSortTest extends SortTest {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private InsertionSort insertionSort;

    private static Integer[] VALID_INPUT = new Integer[] {80, 70, 60, 30, 20, 10};
        private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};
//    private static Integer[] CORRECT_RESULT = getCorrectlySortedArrayFromInput(VALID_INPUT);

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
        insertionSort = new InsertionSort(sortRepresentationQueue);
    }

    @Test
    public void shouldReturnSortedArrayOnValidInput() {
        //when
        insertionSort.sort(VALID_INPUT);
        Comparable[] lastElementInQueue = this.getSortedArrayFromQueue(sortRepresentationQueue);

        //then
        assertThat(lastElementInQueue, is(CORRECT_RESULT));
    }
}