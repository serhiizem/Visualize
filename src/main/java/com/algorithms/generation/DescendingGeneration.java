package com.algorithms.generation;

import com.algorithms.annotations.Filler;
import com.algorithms.exceptions.RequestedArraySizeException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;

@SuppressWarnings("Duplicates")
@Component("descendingGeneration")
public class DescendingGeneration extends GenerationStrategy {

    /**
     * Method that provides an array generation in descending order
     *
     * @param arraySize size of the resulting array
     * @param minValue  minimum value in te resulting array
     * @param maxValue  maximum value in te resulting array
     *
     * @return array of {@Comparable}s rearranged in descending order
     */
    @Filler
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
