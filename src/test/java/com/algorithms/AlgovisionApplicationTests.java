package com.algorithms;

import com.algorithms.controller.BubbleSort;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class AlgovisionApplicationTests {

	@Test
	public void contextLoads() {

		Integer[] integers = new Integer[] {7, 4, 2, 6, 5, 1, 9, 8, 3, 5};

		BubbleSort bubbleSort = new BubbleSort();

		Comparable[] sortedArray = bubbleSort.sort(integers);

		System.out.println(Arrays.toString(sortedArray));

		assertArrayEquals(sortedArray, new Integer[]{1, 2, 3, 4, 5, 5, 6, 7, 8, 9});
	}
}
