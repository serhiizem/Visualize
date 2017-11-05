package com.algorithms.generation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
public class DescendingGenerationTest extends GenerationTest {

    private DescendingGeneration descendingGeneration;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        descendingGeneration = new DescendingGeneration();
    }

    @Test
    public void shouldGenerateValuesInArrayOnlyInASpecificRange() {
        //when
        Comparable[] generatedArray = descendingGeneration
                .generateArrayFromRange(ARRAY_SIZE, GENERATOR_MIN_VALUE, GENERATOR_MAX_VALUE);

        //then
        this.assertArrayHasEveryElementLessThan(generatedArray, GENERATOR_MAX_VALUE);
        this.assertArrayHasEveryElementGreaterThan(generatedArray, GENERATOR_MIN_VALUE);
    }

    @Test
    public void shouldGenerateArrayInDescendingOrder() {
        //when
        Comparable[] arrayFromRange = descendingGeneration
                .generateArrayFromRange(5, 5, 10);

        //then
        for (int i = 1; i < arrayFromRange.length; i++) {
            assertThat(arrayFromRange[i - 1],
                    greaterThanOrEqualTo(arrayFromRange[i]));
        }
    }
}