package com.algorithms.util;

public class GenerationRequest {

    private int arraySize;
    private int minValue;
    private int maxValue;
    private GenerationType generationType;

    public GenerationRequest() {
    }

    public GenerationRequest(final int arraySize,
                             final int minValue,
                             final int maxValue,
                             final GenerationType generationType) {
        this.arraySize = arraySize;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.generationType = generationType;
    }

    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
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
                "arraySize=" + arraySize +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", generationType=" + generationType +
                '}';
    }
}
