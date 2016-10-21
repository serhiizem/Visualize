package com.algorithms.util;

import java.util.Arrays;

public class SortRepresentation {

    private Integer[] intermediate = new Integer[10];

    public SortRepresentation() {
    }

    public SortRepresentation(Integer[] intermediate) {
        this.intermediate = intermediate;
    }

    public void setIntermediate(Integer[] intermediate) {
        this.intermediate = intermediate;
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
