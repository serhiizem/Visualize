package com.algorithms.generation;

import org.junit.Before;
import org.junit.Test;

public class RandomGenerationTest extends GenerationTest {

    private RandomGeneration randomGeneration;

    @Before
    public void setUp() throws Exception {
        randomGeneration = new RandomGeneration();
    }

    @Test
    public void shouldGenerateValuesFromTheSpecifiedRange() {
        Comparable[] generatedArray = randomGeneration
                .generateArrayFromRange(ARRAY_SIZE, GENERATOR_MIN_VALUE, GENERATOR_MAX_VALUE);

        this.assertArrayHasEveryElementLessThan(generatedArray, GENERATOR_MAX_VALUE);
        this.assertArrayHasEveryElementGreaterThan(generatedArray, GENERATOR_MIN_VALUE);
    }
}