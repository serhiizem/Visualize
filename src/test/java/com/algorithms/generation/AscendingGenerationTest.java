package com.algorithms.generation;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
public class AscendingGenerationTest {

    private AscendingGeneration ascendingGeneration;

    @Before
    public void setUp() throws Exception {
        ascendingGeneration = new AscendingGeneration();
    }

    @Test
    public void shouldGenerateValuesInArrayOnlyInASpecificRange() {
        //when
        Comparable[] arrayFromRange = ascendingGeneration
                .generateArrayFromRange(5, 5, 10);

        //given
        Comparable[] validItems = new Comparable[] {5, 6, 7, 8, 9};

        //then
        Arrays.stream(arrayFromRange)
                .forEach(a -> assertThat(a, isIn(validItems)));
    }

    /*@Test
    public void shouldGenerateArrayInAscendingOrder() {
        //when
        Comparable[] arrayFromRange = ascendingGeneration
                .generateArrayFromRange(5, 5, 10);

        //then
        Arrays.stream(arrayFromRange)
                .forEach(a -> assertThat(a.compareTo(5),
                        greaterThanOrEqualTo(0)));

        Arrays.stream(arrayFromRange)
                .forEach(a -> assertThat(a.compareTo(5),
                        lessThanOrEqualTo(10)));
    }*/

    @Test
    public void shouldGenerateArrayInAscendingOrder() {
        //when
        Comparable[] arrayFromRange = ascendingGeneration
                .generateArrayFromRange(5, 5, 10);

        //then
        for (int i = 1; i < arrayFromRange.length; i++) {
            assertThat(arrayFromRange[i - 1],
                    lessThanOrEqualTo(arrayFromRange[i]));
        }
    }
}