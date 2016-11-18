package com.algorithms.util;

public class Range {
    private int arraySize;
    private int minValue;
    private int maxValue;

    public Range() {
    }

    public Range(int arraySize, int minValue, int maxValue) {
        this.arraySize = arraySize;
        this.minValue = minValue;
        this.maxValue = maxValue;
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

    @Override
    public String toString() {
        return "Range{" +
                "arraySize=" + arraySize +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                '}';
    }
}
