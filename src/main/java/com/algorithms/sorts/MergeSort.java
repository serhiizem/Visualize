package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mergeSort")
public class MergeSort extends Sortable implements Sorting {

    private Integer[] numbers;
    private Integer[] helper;

    @Autowired
    public MergeSort(SortRepresentation sortRepresentation) {
        super(sortRepresentation);
    }

    @Override
    public void sort() {
        this.numbers = sortRepresentation.getIntermediateResult();
        int arrayLength = numbers.length;
        this.helper = new Integer[arrayLength];
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

//        int i = 0 ??????;
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            //noinspection Duplicates
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
    }
    public Integer[] getNumbers() {
        return numbers;
    }
}
