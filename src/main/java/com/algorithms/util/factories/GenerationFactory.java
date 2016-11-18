package com.algorithms.util.factories;

import com.algorithms.generation.GenerationStrategy;
import com.algorithms.entity.GenerationType;

public interface GenerationFactory {
    GenerationStrategy getGenerationAlgorithm(GenerationType type);
}
