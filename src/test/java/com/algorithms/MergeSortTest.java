package com.algorithms;

import com.algorithms.sorts.MergeSort;
import com.algorithms.sorts.MergeSortImproved;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class MergeSortTest {

    private static Integer[] VALID_INPUT = new Integer[] {20, 30, 70, 10, 80, 60};
    private static Integer[] CORRECT_RESULT = new Integer[] {10, 20, 30, 60, 70, 80};

    @Test
    public void shouldReturnSortedArrayOnValidInput() {

//        MergeSort mergeSort = new MergeSort();
//
//        Integer[] sortedArray = mergeSort.sort(VALID_INPUT);
//
//        Assert.assertThat(sortedArray, is(CORRECT_RESULT));
    }

    @Test
    public void improvedMergeSortShouldReturnSortedArrayOnValidInput() {

        MergeSortImproved mergeSort = new MergeSortImproved(VALID_INPUT);

        Integer[] numbers = mergeSort.getNumbers();

        Assert.assertThat(numbers, is(CORRECT_RESULT));
    }
}