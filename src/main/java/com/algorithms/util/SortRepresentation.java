package com.algorithms.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SortRepresentation {

    private Integer[] intermediate = new Integer[10];

    boolean sortStarted = false;

    public SortRepresentation() {
    }

    public SortRepresentation(Integer[] intermediate) {
        this.intermediate = intermediate;
    }

    public void setIntermediate(Integer[] intermediate) {
        this.intermediate = intermediate;
    }

    public boolean isSortStarted() {
        return sortStarted;
    }

    public void setSortStarted(boolean sortStarted) {
        this.sortStarted = sortStarted;
    }

    public Integer[] getIntermediate() {
        return intermediate;
    }

    @Override
    public String toString() {
        return "SortRepresentation{" +
                "intermediate=" + Arrays.toString(intermediate) +
                '}';
    }
}
