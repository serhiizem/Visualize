package com.algorithms.controller;

import java.util.Arrays;

public class SortRepresentation {

    private int[] intermediate = new int[10];

    public SortRepresentation(int[] intermediate) {
        this.intermediate = intermediate;
    }

    public void setIntermediate(int[] intermediate) {
        this.intermediate = intermediate;
    }

    public int[] getIntermediate() {
        return intermediate;
    }

    @Override
    public String toString() {
        return "SortRepresentation{" +
                "intermediate=" + Arrays.toString(intermediate) +
                '}';
    }
}
