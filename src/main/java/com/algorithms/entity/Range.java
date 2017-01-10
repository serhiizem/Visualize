package com.algorithms.entity;

/**
 * This class is used to produce objects that encapsulate information
 * regarding boundaries and the size of the array which is to be generated
 * by any implementation of {@link com.algorithms.generation.GenerationStrategy}
 * class
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
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
