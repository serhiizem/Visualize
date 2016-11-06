package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.swap;

@Component("quickSort")
public class QuickSort extends Queueable implements Sorting {

    @Autowired
    public QuickSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Override
    public void sort(Integer[] array) {
        quickSort(0, array.length - 1, array);
    }

    private void quickSort(int left, int right, Integer[] array) {

        int index = partition(left, right, array);

        if (left < index - 1)
            quickSort(left, index - 1, array);
        if (right > index)
            quickSort(index, right, array);
    }

    private int partition(int left, int right, Integer[] array) {
        int i = left;
        int j = right;

        int pivot = array[(left + right) / 2];

        while (i <= j) {
            long currentTime = System.currentTimeMillis();
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if(i <= j) {
                swap(i, j, array);
                putIntermediateResultInAQueue(array, currentTime - System.currentTimeMillis());
                i++;
                j--;
            }
        }
        return i;
    }
}