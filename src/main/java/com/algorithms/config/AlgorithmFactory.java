package com.algorithms.config;

import com.algorithms.sorts.Sorting;
import com.algorithms.entity.AlgorithmType;

public interface AlgorithmFactory {
    Sorting getAlgorithm(AlgorithmType type);
}
