package com.algorithms.util;

import java.util.Arrays;

public class SortRepresentation {

    private Integer[] intermediateResult;
    private Long elapsedTime;

    public SortRepresentation() {
    }

    public SortRepresentation(Integer[] intermediate) {
        this.intermediateResult = intermediate;
    }

    public SortRepresentation(Integer[] intermediate, Long elapsedTime) {
        this.intermediateResult = intermediate;
        this.elapsedTime = elapsedTime;
    }

    public void setIntermediateResult(Integer[] intermediateResult) {
        this.intermediateResult = intermediateResult;
    }

    public Integer[] getIntermediateResult() {
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
