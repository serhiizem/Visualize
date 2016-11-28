package com.algorithms.generation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unchecked")
public class DescendingGenerationTest {

    private DescendingGeneration descendingGeneration;

    @Before
    public void setUp() throws Exception {
        descendingGeneration = new DescendingGeneration();
    }

    @Test
    public void shouldGenerateValuesInArrayOnlyInASpecificRange() {
        //when
        Comparable[] arrayFromRange = descendingGeneration
                .generateArrayFromRange(5, 5, 10);

        //then
        Arrays.stream(arrayFromRange)
                .forEach(a -> assertThat(a.compareTo(5),
                        greaterThanOrEqualTo(0)));

        Arrays.stream(arrayFromRange)
                .forEach(a -> assertThat(a.compareTo(5),
                        lessThanOrEqualTo(10)));
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