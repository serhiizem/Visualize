package com.algorithms.entity;

import com.algorithms.util.Range;

public class GenerationRequest {

    private Range range;
    private GenerationType generationType;

    public GenerationRequest() {
    }

    public GenerationRequest(final Range range,
                             final GenerationType generationType) {
        this.range = range;
        this.generationType = generationType;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }

    public GenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(GenerationType generationType) {
        this.generationType = generationType;
    }

    @Override
    public String toString() {
        return "GenerationRequest{" +
                "range=" + range +
                ", generationType=" + generationType +
                '}';
    }
}
