package com.algorithms.service;

import com.algorithms.entity.Range;
import com.algorithms.generation.GenerationStrategy;

/**
 * A data structure used to invoke an array generation
 * via defined algorithm
 *
 * @see Range
 * @see GenerationStrategy
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
public class GenerationService {
    private Range range;
    private GenerationStrategy generationStrategy;

    public GenerationService(Range range, GenerationStrategy generationStrategy) {
        this.range = range;
        this.generationStrategy = generationStrategy;
    }

    public Comparable[] generateArray() {
        return this.getArrayOfSpecificSizeWithBounds();
    }

    private Comparable[] getArrayOfSpecificSizeWithBounds() {
        return generationStrategy.generateArrayFromRange(range.getArraySize(),
                range.getMinValue(), range.getMaxValue());
    }
}
