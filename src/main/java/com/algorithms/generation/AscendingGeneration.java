package com.algorithms.generation;

import com.algorithms.annotations.Filler;
import com.algorithms.entity.GenerationType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * One of the classes used to produce sample data range for its further processing by various
 * {@link com.algorithms.sorts.Sorting} interface implementations.
 *
 * <p>Instance of the following class is created by invoking an implementation of the factory method
 * {@link com.algorithms.util.factories.GenerationFactory#getGenerationAlgorithm(GenerationType)}
 * with an {@code GenerationType.ASC_ORDER} enum element passed as its parameter</p>
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
@Component("ascendingGeneration")
public class AscendingGeneration extends GenerationStrategy {

    /**
     * Method that provides an ascending array generation.
     *
     * @param  arraySize size of the resulting array
     * @param  minValue minimum value in the resulting array
     * @param  maxValue maximum value in the resulting array
     * @return array of {@code Comparable}s rearranged in ascending order
     */
    @Filler
    @Override
    public Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue) {

        int numberOfAvailableNumbers = maxValue - minValue;

        Comparable[] helper = new Comparable[numberOfAvailableNumbers];
        Comparable[] result = new Comparable[arraySize];

        this.populateArrayWithNumbersFromRange(helper, minValue, maxValue);
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);
        Arrays.sort(result);

        return result;
    }
}
