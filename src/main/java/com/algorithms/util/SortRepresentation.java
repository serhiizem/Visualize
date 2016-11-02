package com.algorithms.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SortRepresentation {

    // Throws an NPE if not initialized explicitly
    private Boolean sortStarted = false;
    private Integer[] intermediateResult;
    private Long startTime;
    private Long elapsedTime;

    public SortRepresentation() {
    }

    public SortRepresentation(Integer[] intermediate) {
        this.intermediateResult = intermediate;
    }

    public Boolean isSortStarted() {
        return sortStarted;
    }

    public void setSortStarted(Boolean sortStarted) {
        this.sortStarted = sortStarted;
    }

    public void setIntermediateResult(Integer[] intermediateResult) {
        this.intermediateResult = intermediateResult;
    }

    public Integer[] getIntermediateResult() {
        return intermediateResult;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
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
                "sortStarted=" + sortStarted +
                ", intermediateResult=" + Arrays.toString(intermediateResult) +
                ", startTime=" + startTime +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
