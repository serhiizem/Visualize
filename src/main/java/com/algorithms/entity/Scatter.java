package com.algorithms.entity;

import com.algorithms.service.StatisticsService;

import java.util.Iterator;
import java.util.Random;

/**
 * This class is used to generate objects of {@link Range} class instantiated
 * with different values passed as constructor parameters. This range scatter
 * is required in order to produce enough data to be able to create statistics
 * on any implementation of {@link com.algorithms.sorts.Sorting} interface
 *
 * @see com.algorithms.sorts.Sorting
 * @see StatisticsService#writeReport()
 */
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
                current += STEP;

                return getSampleDataRange(current);
            }
        };
    }

    private Range getSampleDataRange(int rangeSize) {
        Random random = new Random(47);
        int minValue = random.nextInt(rangeSize);
        // let's suppose that max value should be four times larger than min value
        int maxValue = random.nextInt(rangeSize) + 4 * rangeSize;

        return new Range(rangeSize, minValue, maxValue);
    }
}
