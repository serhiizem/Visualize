package com.algorithms.generation;

import com.algorithms.annotations.Filler;
import com.algorithms.exceptions.RequestedArraySizeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@SuppressWarnings("Duplicates")
@Component("ascendingGeneration")
public class AscendingGeneration extends GenerationStrategy {

    /**
     * Method that provides an ascending array generation
     *
     * @param arraySize size of the resulting array
     * @param minValue  minimum value in the resulting array
     * @param maxValue  maximum value in the resulting array
     *
     * @return array of {@Comparable}s rearranged in ascending order
     */
    @Filler
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
