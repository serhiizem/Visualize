package com.algorithms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlgorithmFactory {

    @Autowired
    private AlgorithmFactory factoryMock;

    private static SortRepresentation sortRepresentation;

    @Autowired
    public AlgorithmFactory(SortRepresentation sortRepresentation) {
        this.sortRepresentation = sortRepresentation;
    }


}
