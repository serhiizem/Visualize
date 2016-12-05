package com.algorithms.generation;

import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class GenerationTest {

    public static final int ARRAY_SIZE = 4;
    public static final int GENERATOR_MIN_VALUE = 5;
    public static final int GENERATOR_MAX_VALUE = 10;

    protected void assertArrayHasEveryElementLessThan(Comparable[] arrayToCheck,
                                                    Integer value) {
        Arrays.stream(arrayToCheck)
                .forEach(a -> assertThat(a.compareTo(value),
                        lessThanOrEqualTo(0)));
    }

    protected void assertArrayHasEveryElementGreaterThan(Comparable[] arrayToCheck,
                                                       Integer value) {
        Arrays.stream(arrayToCheck)
                .forEach(a -> assertThat(a.compareTo(value),
                        greaterThanOrEqualTo(0)));
    }
}
