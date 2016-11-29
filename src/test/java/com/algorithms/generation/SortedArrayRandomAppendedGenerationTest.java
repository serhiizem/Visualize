package com.algorithms.generation;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
public class SortedArrayRandomAppendedGenerationTest extends GenerationTest {

    private SortedArrayRandomAppendedGeneration appendedGeneration;

    @Before
    public void setUp() throws Exception {
        appendedGeneration = new SortedArrayRandomAppendedGeneration();
    }

    @Test
    public void shouldGenerateValuesFromTheSpecifiedRange() {
        //when
        Comparable[] generatedArray = appendedGeneration
                .generateArrayFromRange(ARRAY_SIZE, GENERATOR_MIN_VALUE, GENERATOR_MAX_VALUE);

        //then
        this.assertArrayHasEveryElementGreaterThan(generatedArray, GENERATOR_MIN_VALUE);
        this.assertArrayHasEveryElementLessThan(generatedArray, GENERATOR_MAX_VALUE);
    }
    
    @Test
    public void shouldGenerateValuesInAscendingOrderExceptTheLastOne() {
        //when
        Comparable[] generatedArray = appendedGeneration
                .generateArrayFromRange(ARRAY_SIZE, GENERATOR_MIN_VALUE, GENERATOR_MAX_VALUE);

        //then
        for (int i = 1; i < generatedArray.length - 1; i++) {
            assertThat(generatedArray[i - 1], lessThanOrEqualTo(generatedArray[i]));
        }
    }
}