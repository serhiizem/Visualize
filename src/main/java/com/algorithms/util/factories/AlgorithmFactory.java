package com.algorithms.util.factories;

import com.algorithms.entity.AlgorithmType;
import com.algorithms.sorts.Sorting;

public interface AlgorithmFactory {
    Sorting  getSortingAlgorithm(AlgorithmType type);
}
