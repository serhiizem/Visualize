package com.algorithms.sorts;

public class MergeSortImproved {

    private Integer[] numbers;
    private Integer[] helper;

    public MergeSortImproved(Integer[] values) {
        this.numbers = values;
        int number = values.length;
        this.helper = new Integer[number];
        mergeSort(0, number - 1);
    }

    private void mergeSort(int low, int high) {

        if(low < high) {

            int middle = low + (high - low) / 2;

            mergeSort(low, middle);
            mergeSort(middle + 1, high);

            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

//        int i = 0 ??????;
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            //noinspection Duplicates
            if(numbers[i] <= numbers[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            numbers[k] = helper[i];
            i++;
            k++;
        }

        /*while (j <= high) {
            numbers[k] = helper[j];
            j++;
            k++;
        }*/
    }

    public Integer[] getNumbers() {
        return numbers;
    }
}
