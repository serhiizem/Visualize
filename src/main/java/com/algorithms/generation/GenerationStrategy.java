package com.algorithms.generation;

/**
 * Class declares an abstract {@code generateArrayFromRange} method which is to be
 * implemented in order to obtain a unique generation algorithm
 *
 * @see AscendingGeneration
 * @see DescendingGeneration
 * @see RandomGeneration
 * @see SortedArrayRandomAppendedGeneration
 */
public abstract class GenerationStrategy {
    public abstract Comparable[] generateArrayFromRange(int arraySize, int minValue, int maxValue);

    protected Comparable[] populateArrayWithNumbersFromRange(Comparable[] helper, int minValue, int maxValue) {
        int count = 0;
        for(int i = minValue; i < maxValue; i++) {
            helper[count++] = i;
        }
        return helper;
    }

    protected Comparable[] shuffle(Comparable[] helper) {
        int n = helper.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [i, n-1]
            int r = i + (int) (Math.random() * (n - i));
            Comparable swap = helper[r];
            helper[r] = helper[i];
            helper[i] = swap;
        }
        return helper;
    }
}
