package com.algorithms.generators;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class GenerateTest {

    @Test
    public void shouldGenerateRandomSortedArray() {
        int[] result = new int[20];

        HashSet<Integer> usedIntegers = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int generatedValue = random.nextInt(20);
            while (usedIntegers.contains(generatedValue)) { //while we have already used the number
                generatedValue = (int) (Math.random() * 30); //generate a new one because it's already used
            }
            //by this time, generatedValue will be unique
            usedIntegers.add(generatedValue);
            result[i] = generatedValue;
        }
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void someWeirdAlgo() {
        int count = 20;
        int end = 20;
        int start = 4;

        int[] result = new int[count];
        int cur = 0;
        int remaining = end - start;
        for (int i = start; i < end; i++) {
            result[i] = i;
        }
        System.out.println(Arrays.toString(result));
    }
    
    @Test
    public void randomArrayIfMaxValueDiffersMuchFromElementCount() {
        int min = 40;
        int max = 100;
        int numbersNeeded = 10;
        Random random = new Random();
        Set<Integer> generated = new TreeSet<>();

        while (generated.size() < numbersNeeded) {
            int i = random.nextInt(max - min) + min;
            generated.add(i);
        }
    }
}
