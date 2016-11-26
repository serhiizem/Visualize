package com.algorithms.service;

import com.algorithms.entity.Range;
import com.algorithms.generation.GenerationStrategy;

/**
 * A data structure used to invoke an array generation
 * via defined algorithm
 *
 * @see GenerationStrategy
 *
 * @author Zemlianiy
 * @version 1.0
 * @since
 */
public class GenerationService {

    private Range range;
    private GenerationStrategy generationStrategy;

    public GenerationService(Range range, GenerationStrategy generationStrategy) {
        this.range = range;
        this.generationStrategy = generationStrategy;
    }

    public Comparable[] generateArray() {
        int arraySize = range.getArraySize();
        int minValue = range.getMinValue();
        int maxValue = range.getMaxValue();

        return generationStrategy
                .generateArrayFromRange(arraySize, minValue, maxValue);
    }
}
