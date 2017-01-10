package com.algorithms.generation;

import com.algorithms.annotations.Filler;
import com.algorithms.entity.GenerationType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;

/**
 * One of the classes used to produce sample data range for its further processing by various
 * {@link com.algorithms.sorts.Sorting} interface implementations.
 *
 * <p>Instance of the following class is created by invoking an implementation of the factory method
 * {@link com.algorithms.util.factories.GenerationFactory#getGenerationAlgorithm(GenerationType)}
 * with a {@code GenerationType.ASC_ORDER_PLUS_RAND} enum element passed as a parameter</p>
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
@Component("randomAppendedGeneration")
public class SortedArrayRandomAppendedGeneration extends GenerationStrategy {

    private Random random = new Random();

    /**
     * Method that provides an array generation in ascending order
     * with additional random element inserted at the end of the array
     *
     * @param  arraySize size of the resulting array
     * @param  minValue  minimum value in the resulting array
     * @param  maxValue  maximum value in the resulting array
     * @return array rearranged in ascending order except one random value appended
     *         to the end of the array
     */
    @Filler
    @Override
    public Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue) {

        int numberOfAvailableValues = maxValue - minValue;

        Comparable[] helper = new Integer[numberOfAvailableValues];
        Comparable[] result = new Integer[arraySize + 1];

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
