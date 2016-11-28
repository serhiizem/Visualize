package com.algorithms.generation;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class RandomGenerationTest {

    private RandomGeneration randomGeneration;

    @Before
    public void setUp() throws Exception {
        randomGeneration = new RandomGeneration();
    }

    @Test
    public void shouldGenerateValuesFromTheSpecifiedRange() {
        Comparable[] generatedArray = randomGeneration
                .generateArrayFromRange(5, 5, 10);

        Arrays.stream(generatedArray)
                .forEach(a -> assertThat(a.compareTo(5),
                        greaterThanOrEqualTo(0)));

        Arrays.stream(generatedArray)
                .forEach(a -> assertThat(a.compareTo(10),
                        lessThanOrEqualTo(0)));
    }
}