package com.algorithms.sorts;

import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.algorithms.util.TestUtil.getCorrectlySortedArrayFromInput;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest extends SortTest {

    private Queue<SortRepresentation> sortRepresentationQueue;
    private BubbleSortReverse bubbleSort;

    private static Integer[] VALID_INPUT = new Integer[] {20, 30, 70, 10, 80, 60};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};
//    private static Integer[] CORRECT_RESULT = getCorrectlySortedArrayFromInput(VALID_INPUT);

    @Before
    public void setUp() throws Exception {
        sortRepresentationQueue = new Queue<>();
        bubbleSort = new BubbleSortReverse(sortRepresentationQueue);
    }

    @Test
    public void lastElementPutInTheQueueShouldBeFullySorted() {
        //when
        bubbleSort.sort(VALID_INPUT);
        Comparable[] sortedArray = this.getSortedArrayFromQueue(sortRepresentationQueue);

        //then
        assertThat(sortedArray, is(CORRECT_RESULT));
    }

    @Test
    public void shouldRetainAllTheElementsFromTheOriginalArray() {
        //given
        bubbleSort.sort(VALID_INPUT);
        Comparable[] sortedArray = this.getSortedArrayFromQueue(sortRepresentationQueue);

        //then
        assertThat(Arrays.stream(sortedArray).collect(Collectors.toList()), hasItems(VALID_INPUT));
    }
}
