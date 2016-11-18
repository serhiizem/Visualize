package com.algorithms.service;

import com.algorithms.controller.GenerationStrategy;
import com.algorithms.util.Range;
import org.springframework.stereotype.Service;

@Service
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
