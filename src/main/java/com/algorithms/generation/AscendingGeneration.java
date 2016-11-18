package com.algorithms.generation;

import com.algorithms.exceptions.RequestedArraySizeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
@Component("ascendingGeneration")
public class AscendingGeneration extends GenerationStrategy {

    @Override
    public Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue) {
        int numberOfAvailableNumbers = maxValue - minValue;

        Comparable[] helper = new Comparable[numberOfAvailableNumbers];
        Comparable[] result = new Comparable[arraySize];

        if(isLess(numberOfAvailableNumbers, arraySize)) {
            throw new RequestedArraySizeException("In order not to contain duplicates" +
                    " array must have a size less than or equal to the difference between " +
                    "its max and min values");
        }

        this.populateArrayWithNumbersFromRange(helper, minValue, maxValue);
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);
        Arrays.sort(result);

        return result;
    }
}
