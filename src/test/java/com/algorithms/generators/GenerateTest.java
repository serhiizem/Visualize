package com.algorithms.generators;

import com.algorithms.controller.RequestedArraySizeException;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
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
                generatedValue = (int) (Math.random() * 30); //generateArrayFromRequest a new one because it's already used
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

    @Test
    public void randomArrayIfMaxValueIsCloseToTheElementCount() {
        int minValue = 7;
        int maxValue = 50;
        int arraySize = 30;

        Integer[] helper = new Integer[maxValue - minValue];
        Integer[] result = new Integer[arraySize];

        if(maxValue - minValue < arraySize) {
            throw new RequestedArraySizeException("Your array will not contain duplicate " +
                    "values if it has size less or equal to the difference between " +
                    "its max and min values");
        }

        int count = 0;
        for(int i = minValue; i < maxValue; i++) {
            helper[count++] = i;
        }
        helper = this.shuffle(helper);

        System.arraycopy(helper, 0, result, 0, result.length);
        Arrays.sort(result);
        System.out.println(Arrays.toString(result));
    }

    private Integer[] shuffle(Integer[] helper) {
        int n = helper.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [i, n-1]
            int r = i + (int) (Math.random() * (n - i));
            int swap = helper[r];
            helper[r] = helper[i];
            helper[i] = swap;
        }
        return helper;
    }

    @Test
    public void weirdAlgorithm() {
        int N = 20;
        //array to store N random integers (0 - N-1)
        int[] nums = new int[N];

        // initialize each value at index i to the value i
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = i;
        }

        Random randomGenerator = new Random();
        int randomIndex; // the randomly selected index each time through the loop
        int randomValue; // the value at nums[randomIndex] each time through the loop

        // randomize order of values
        for (int i = 0; i < nums.length; ++i) {
            // select a random index
            randomIndex = randomGenerator.nextInt(nums.length);

            // swap values
            randomValue = nums[randomIndex];
            nums[randomIndex] = nums[i];
            nums[i] = randomValue;
        }
        System.out.println(Arrays.toString(nums));
    }
}
