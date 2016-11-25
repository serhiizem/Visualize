package com.algorithms.generation;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomGenerationTest {

    private DescendingGeneration descendingGeneration;

    @Before
    public void setUp() throws Exception {
        descendingGeneration = new DescendingGeneration();
    }

    @Test
    public void shouldGenerateRandomlyGeneratedArray() {
        Comparable[] generatedArray = descendingGeneration
                .generateArrayFromRange(5, 5, 10);

        Comparable[] validItems = new Comparable[]{9, 8, 7, 6, 5};

        assertThat(generatedArray, Matchers.arrayContaining(validItems));
    }
}