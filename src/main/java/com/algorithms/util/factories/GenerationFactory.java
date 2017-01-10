package com.algorithms.util.factories;

import com.algorithms.entity.GenerationType;
import com.algorithms.generation.GenerationStrategy;

public interface GenerationFactory {
    GenerationStrategy getGenerationAlgorithm(GenerationType type);
}
