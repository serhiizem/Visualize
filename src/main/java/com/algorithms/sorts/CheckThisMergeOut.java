package com.algorithms.sorts;

import java.util.Arrays;

public class CheckThisMergeOut {

    Integer[] numbers;
    Integer[] helper;

    int arrayLength;

    public void sort(Integer[] numbers) {
        this.numbers = numbers;
        arrayLength = numbers.length;
        this.helper = new Integer[arrayLength];
        mergeSort(0, arrayLength - 1);
    }

    private void mergeSort(int low, int high) {
        if(low < high) {

            int middle = low + (high - low) / 2;

            mergeSort(low, middle);
            mergeSort(middle + 1, high);

            merge(low, middle, high);
            System.out.println("Big O: " + arrayLength * Math.log10(arrayLength));
        }
    }

    private void merge(int low, int middle, int high) {

        for(int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }
        System.out.println("Merging low: " + low + " middle: " + middle + " and high: " + high);
        System.out.println("Helper array content: " + Arrays.toString(helper));
        System.out.println("Result array content: " + Arrays.toString(numbers));

        // represents the first index of the first half of the array
        int i = low;
        //represents the first index of the second half of the array
        int j = middle + 1;
        // represents an index in the resulting array
        // this index corresponds to the first index of the given partition
        int k = low;

        while (i <= middle && j <= high) {
            if(helper[i] < helper[j]) {
                numbers[k] = helper[i];
                i++;
                System.out.println("In merging cycle result array content: " + Arrays.toString(numbers));
            } else {
                numbers[k] = helper[j];
                System.out.println("In merging cycle result array content: " + Arrays.toString(numbers));
                j++;
            }
            k++;
        }
        System.out.println("Before the last while: " + Arrays.toString(numbers));
        System.out.println("k: " + k);
        System.out.println("middle: " + middle);
        System.out.println("i: " + i);


        // if i variable was already used to insert value from helper array to the resulting array
        // then it will already be greater than middle variable
        while (i <= middle) {
            numbers[k] = helper[i];
            i++;
            k++;
        }
        System.out.println("After the last while: " + Arrays.toString(numbers));
        System.out.println();
    }

    public Integer[] getNumbers() {
        return numbers;
    }
}