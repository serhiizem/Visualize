package com.algorithms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class SortRepresentation {

    private static final Logger log = LoggerFactory.getLogger(SortRepresentation.class);

    private Integer[] intermediateResult;
    private Long elapsedTime;

    boolean sortStarted = false;

    public SortRepresentation() {
    }

    public SortRepresentation(Integer[] intermediate) {
        this.intermediateResult = intermediate;
    }

    public boolean isSortStarted() {
        return sortStarted;
    }

    public void setSortStarted(boolean sortStarted) {
        this.sortStarted = sortStarted;
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
                "intermediateResult=" + Arrays.toString(intermediateResult) +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
