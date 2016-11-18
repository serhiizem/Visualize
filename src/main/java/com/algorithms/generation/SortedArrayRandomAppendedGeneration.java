package com.algorithms.generation;

import com.algorithms.exceptions.RequestedArraySizeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

@SuppressWarnings("Duplicates")
@Component("randomAppendedGeneration")
public class SortedArrayRandomAppendedGeneration extends GenerationStrategy {

    private Random random = new Random();

    @Override
    public Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue) {
        int numberOfAvailableValues = maxValue - minValue;

        Comparable[] helper = new Integer[numberOfAvailableValues];
        Comparable[] result = new Integer[arraySize + 1];

        if(isLess(numberOfAvailableValues, arraySize)) {
            throw new RequestedArraySizeException("In order not to contain duplicates" +
                    " array must have a size less than or equal to the difference between " +
                    "its max and min values");
        }

        this.populateArrayWithNumbersFromRange(helper, minValue, maxValue);
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);
        Arrays.sort(result);
        int lastIndexOfTheResultArray = result.length - 1;
        result[lastIndexOfTheResultArray] = this.generateRandomValueFromMinToMax(minValue, maxValue);

        return result;
    }

    private Comparable generateRandomValueFromMinToMax(int minValue, int maxValue) {
        return random.nextInt(maxValue - minValue) + minValue;
    }

    private boolean isLess(int numberOfAvailableNumbers, int arraySize) {
        return numberOfAvailableNumbers < arraySize;
    }

    private Comparable[] populateArrayWithNumbersFromRange(Comparable[] helper, int minValue, int maxValue) {
        int count = 0;
        for(int i = minValue; i < maxValue; i++) {
            helper[count++] = i;
        }
        return helper;
    }

    private Comparable[] shuffle(Comparable[] helper) {
        int n = helper.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [i, n-1]
            int r = i + (int) (Math.random() * (n - i));
            Comparable swap = helper[r];
            helper[r] = helper[i];
            helper[i] = swap;
        }
        return helper;
    }
}
