package com.algorithms.generation;

import com.algorithms.annotations.Filler;
import com.algorithms.entity.GenerationType;
import org.springframework.stereotype.Component;

/**
 * One of the classes used to produce sample data range for its further processing by various
 * {@link com.algorithms.sorts.Sorting} interface implementations.
 *
 * <p>Instance of the following class is created by invoking an implementation of the factory method
 * {@link com.algorithms.util.factories.GenerationFactory#getGenerationAlgorithm(GenerationType)}
 * with a {@code GenerationType.RANDOM_ORDER} enum element passed as a parameter</p>
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
@Component("randomGeneration")
public class RandomGeneration extends GenerationStrategy {

    /**
     * Method that provides an array arranged in random order
     *
     * @param arraySize size of the resulting array
     * @param minValue  minimum value in the resulting array
     * @param maxValue  maximum value in the resulting array
     * @return array of {@Comparable}s rearranged in random order
     */
    @Filler
    @Override
    public Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue) {
        int numberOfAvailableValues = maxValue - minValue;

        Comparable[] helper = new Comparable[numberOfAvailableValues];
        Comparable[] result = new Comparable[arraySize];

        this.populateArrayWithNumbersFromRange(helper, minValue, maxValue);
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);

        return result;
    }
}
