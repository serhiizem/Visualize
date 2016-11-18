package com.algorithms.service;

import com.algorithms.controller.GenerationStrategy;
import com.algorithms.exceptions.RequestedArraySizeException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AscendingGeneration extends GenerationStrategy {

    @Override
    public Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue) {

        Integer[] helper = new Integer[maxValue - minValue];
        Integer[] result = new Integer[arraySize];

        int numberOfAvailableNumbers = maxValue - minValue;

        if(isLess(numberOfAvailableNumbers, arraySize)) {
            throw new RequestedArraySizeException("Your array will not contain duplicate " +
                    "values if it has size less or equal to the difference between " +
                    "its max and min values");
        }

        int count = 0;
        for(int i = minValue; i < maxValue; i++) {
            helper[count++] = i;
        }
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);
        Arrays.sort(result);
        return null;

    }

    private boolean isLess(int numberOfAvailableNumbers, int arraySize) {
        return numberOfAvailableNumbers < arraySize;
    }

    private void populateArrayWithNumbersFromRange() {}

    private Integer[] shuffle(Integer[] helper) {
        int n = helper.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [i, n-1]
            int r = i + (int) (Math.random() * (n - i));
            int swap = helper[r];
            helper[r] = helper[i];
            helper[i] = swap;
        }
        return helper;
    }
}
