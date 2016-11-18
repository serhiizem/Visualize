package com.algorithms.generation;

import com.algorithms.exceptions.RequestedArraySizeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("Duplicates")
@Component("descendingGeneration")
public class DescendingGeneration extends GenerationStrategy {

    @Override
    public Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue) {
        int numberOfAvailableValues = maxValue - minValue;

        Comparable[] helper = new Comparable[numberOfAvailableValues];
        Comparable[] result = new Comparable[arraySize];

        if(isLess(numberOfAvailableValues, arraySize)) {
            throw new RequestedArraySizeException("Your array will not contain duplicate " +
                    "values if it has size less or equal to the difference between " +
                    "its max and min values");
        }

        this.populateArrayWithNumbersFromRange(helper, minValue, maxValue);
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);

        Arrays.sort(result, Comparator.reverseOrder());

        return result;
    }
}
