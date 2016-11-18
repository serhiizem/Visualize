package com.algorithms.util.factories;

import com.algorithms.sorts.Sorting;
import com.algorithms.entity.AlgorithmType;

public interface AlgorithmFactory {
    Sorting  getSortingAlgorithm(AlgorithmType type);
}
