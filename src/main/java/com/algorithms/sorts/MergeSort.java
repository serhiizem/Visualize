package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.springframework.stereotype.Component;

@Component("mergeSort")
public class MergeSort extends Queueable implements Sorting {

    private Integer[] numbers;
    private Integer[] helper;

    public MergeSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Override
    public void sort(Integer[] array) {
        int arrayLength = array.length;
        this.helper = new Integer[arrayLength];
        this.numbers = array;
        mergeSort(0, arrayLength - 1);
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

        long currentTime = System.currentTimeMillis();

        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            if(helper[i] <= helper[j]) {
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
        putIntermediateResultInAQueue(numbers, System.currentTimeMillis() - currentTime);
    }
}
