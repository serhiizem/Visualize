package com.algorithms.util.factories;

import com.algorithms.entity.GenerationType;
import com.algorithms.service.Generating;

public interface GenerationFactory {
    Generating getGenerationAlgorithm(GenerationType type);
}
