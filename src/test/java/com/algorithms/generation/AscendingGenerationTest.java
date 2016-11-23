package com.algorithms.generation;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class AscendingGenerationTest {

    private AscendingGeneration ascendingGeneration;

    @Before
    public void setUp() throws Exception {
        ascendingGeneration = new AscendingGeneration();
    }

    @Test
    public void shouldGenerateArrayInAscendingOrder() {
        Comparable[] arrayFromRange = ascendingGeneration
                .generateArrayFromRange(5, 5, 10);

        Comparable[] validItems = new Comparable[] {5, 6, 7, 8, 9};

        assertThat(arrayFromRange, Matchers.arrayContaining(validItems));
    }
}