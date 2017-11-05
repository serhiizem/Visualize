package com.algorithms.generation;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
public class AscendingGenerationTest extends GenerationTest {

    private AscendingGeneration ascendingGeneration;

    @Before
    public void setUp() throws Exception {
        ascendingGeneration = new AscendingGeneration();
    }

    @Test
    public void shouldGenerateValuesInArrayOnlyInASpecificRange1() {
        //given
        Comparable[] validItems = new Comparable[]{5, 6, 7, 8, 9};

        //when
        Comparable[] arrayFromRange = ascendingGeneration
                .generateArrayFromRange(ARRAY_SIZE, GENERATOR_MIN_VALUE, GENERATOR_MAX_VALUE);

        //then
        Arrays.stream(arrayFromRange)
                .forEach(a -> assertThat(a, isIn(validItems)));
    }

    @Test
    public void shouldGenerateValuesInArrayOnlyInASpecificRange2() {
        //when
        Comparable[] generatedArray = ascendingGeneration
                .generateArrayFromRange(ARRAY_SIZE, GENERATOR_MIN_VALUE, GENERATOR_MAX_VALUE);

        //then
        /*Arrays.stream(generatedArray)
                .forEach(a -> assertThat(a.compareTo(GENERATOR_MIN_VALUE),
                        greaterThanOrEqualTo(0)));

        Arrays.stream(generatedArray)
                .forEach(a -> assertThat(a.compareTo(GENERATOR_MAX_VALUE),
                        lessThanOrEqualTo(0)));*/

        this.assertArrayHasEveryElementLessThan(generatedArray, GENERATOR_MAX_VALUE);
        this.assertArrayHasEveryElementGreaterThan(generatedArray, GENERATOR_MIN_VALUE);
    }

    @Test
    public void shouldGenerateArrayInAscendingOrder() {
        //when
        Comparable[] arrayFromRange = ascendingGeneration
                .generateArrayFromRange(ARRAY_SIZE, GENERATOR_MIN_VALUE, GENERATOR_MAX_VALUE);

        //then
        for (int i = 1; i < arrayFromRange.length; i++) {
            assertThat(arrayFromRange[i - 1],
                    lessThanOrEqualTo(arrayFromRange[i]));
        }
    }
}