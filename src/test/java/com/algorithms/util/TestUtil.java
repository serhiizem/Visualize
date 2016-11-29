package com.algorithms.util;

import java.util.Arrays;

public class TestUtil {

    public static Integer[] getCorrectlySortedArrayFromInput(Comparable[] input) {
        Integer[] sortedInput = (Integer[]) input.clone();
        Arrays.sort(sortedInput);
        return sortedInput;
    }
}
