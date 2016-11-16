package com.algorithms.util;

import java.util.Arrays;

public class SortRepresentation {

    private Comparable[] intermediateResult;
    private Long elapsedTime;

    public SortRepresentation() {
    }

    public SortRepresentation(Comparable[] intermediate) {
        this.intermediateResult = intermediate;
    }

    public SortRepresentation(Comparable[] intermediate, Long elapsedTime) {
        this.intermediateResult = intermediate;
        this.elapsedTime = elapsedTime;
    }

    public void setIntermediateResult(Comparable[] intermediateResult) {
        this.intermediateResult = intermediateResult;
    }

    public Comparable[] getIntermediateResult() {
        return intermediateResult;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Override
    public String toString() {
        return "SortRepresentation{" +
                ", intermediateResult=" + Arrays.toString(intermediateResult) +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
