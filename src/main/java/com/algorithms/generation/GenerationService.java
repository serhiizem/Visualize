package com.algorithms.generation;

import com.algorithms.entity.Range;

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
