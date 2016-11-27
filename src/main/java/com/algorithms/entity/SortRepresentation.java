package com.algorithms.entity;

import java.util.Arrays;

/**
 *  Class responsible for the representation of intermediate results
 *  of the sorting process
 *
 *  @author Zemlianiy
 *  @version 1.0
 */
public class SortRepresentation {

    private Comparable[] intermediateResult;

    public SortRepresentation() {
    }

    public SortRepresentation(Comparable[] intermediate) {
        this.intermediateResult = intermediate;
    }

    public Comparable[] getIntermediateResult() {
        return intermediateResult;
    }

    @Override
    public String toString() {
        return "SortRepresentation{" +
                ", intermediateResult=" + Arrays.toString(intermediateResult) +
                '}';
    }
}
