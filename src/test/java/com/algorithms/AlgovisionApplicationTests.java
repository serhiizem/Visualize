package com.algorithms;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class AlgovisionApplicationTests {

	@Test
	public void contextLoads() throws Exception {

		Integer[] integers = new Integer[] {7, 4, 2, 6, 5, 1, 9, 8, 3, 5};

		BubbleSort bubbleSort = new BubbleSort(integers);

		bubbleSort.sort(integers);

		System.out.println(Arrays.toString(bubbleSort.getCurrentState()));

		assertArrayEquals(bubbleSort.getCurrentState(), new Integer[]{1, 2, 3, 4, 5, 5, 6, 7, 8, 9});
	}
}
