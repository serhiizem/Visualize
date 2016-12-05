package com.algorithms.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class Scatter implements Iterable<Range>{
    private static final int BASE = 5;
    private static final int STEP = 5;
    private static final int MAX = 25;

    private int current;

    public Scatter() {
        current = BASE;
    }

    @Override
    public Iterator<Range> iterator() {
        return new Iterator<Range>() {

            @Override
            public boolean hasNext() {
                return current < MAX;
            }

            @Override
            public Range next() {
                Range range = getSampleDataRange(current);
                current += STEP;
                return range;
            }
        };
    }

    private Range getSampleDataRange(int rangeSize) {
        Random random = new Random(47);
        int minValue = random.nextInt(rangeSize);
        // max value should be four times larger than min value
        int maxValue = random.nextInt(rangeSize) + 4 * rangeSize;

        return new Range(rangeSize, minValue, maxValue);
    }
}
