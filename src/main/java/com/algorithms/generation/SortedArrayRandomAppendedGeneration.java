package com.algorithms.generation;

import com.algorithms.annotations.Filler;
import com.algorithms.exceptions.RequestedArraySizeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

/**
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@SuppressWarnings("Duplicates")
@Component("randomAppendedGeneration")
public class SortedArrayRandomAppendedGeneration extends GenerationStrategy {

    private Random random = new Random();

    /**
     * Method that provides an array generation in ascending order
     * with additional random element inserted at the end of the array
     *
     * @param arraySize size of the resulting array
     * @param minValue  minimum value in the resulting array
     * @param maxValue  maximum value in the resulting array
     */
    @Filler
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
}
