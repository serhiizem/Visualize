package com.algorithms.generation;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class SortedArrayRandomAppendedGenerationTest {

    private SortedArrayRandomAppendedGeneration appendedGeneration;

    @Before
    public void setUp() throws Exception {
        appendedGeneration = new SortedArrayRandomAppendedGeneration();
    }

    @Test
    public void shouldGenerateValuesFromTheSpecifiedRange() {
        //given
        Comparable[] generatedArray = appendedGeneration
                .generateArrayFromRange(5, 5, 11);

        //then
        Arrays.stream(generatedArray)
                .forEach(a -> assertThat(a.compareTo(5),
                        greaterThanOrEqualTo(0)));

        Arrays.stream(generatedArray)
                .forEach(a -> assertThat(a.compareTo(11),
                        lessThanOrEqualTo(0)));
    }
}