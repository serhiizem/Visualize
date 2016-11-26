package com.algorithms.generation;

import com.algorithms.annotations.Filler;
import com.algorithms.exceptions.RequestedArraySizeException;
import org.springframework.stereotype.Component;

/**
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@SuppressWarnings("Duplicates")
@Component("randomGeneration")
public class RandomGeneration extends GenerationStrategy {

    /**
     * Method that provides an array arranged in random order
     *
     * @param arraySize size of the resulting array
     * @param minValue  minimum value in te resulting array
     * @param maxValue  maximum value in te resulting array
     *
     * @return array of {@Comparable}s rearranged in random order
     */
    @Filler
    @Override
    public Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue) {
        int numberOfAvailableValues = maxValue - minValue;

        Comparable[] helper = new Comparable[numberOfAvailableValues];
        Comparable[] result = new Comparable[arraySize];

        if(isLess(numberOfAvailableValues, arraySize)) {
            throw new RequestedArraySizeException("In order not to contain duplicates" +
                    " array must have a size less than or equal to the difference between " +
                    "its max and min values");
        }

        this.populateArrayWithNumbersFromRange(helper, minValue, maxValue);
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);

        return result;
    }
}
