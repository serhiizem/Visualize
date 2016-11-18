package com.algorithms.sorts;

import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;

import static com.algorithms.sorts.Sorting.isLess;
import static com.algorithms.sorts.Sorting.swap;

@Component("quickSort")
public class QuickSort extends Queueable implements Sorting {

    @Autowired
    public QuickSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Override
    public void sort(Comparable[] array) {
        quickSort(0, array.length - 1, array);
    }

    private void quickSort(int left, int right, Comparable[] array) {

        int index = partition(left, right, array);

        if (left < index - 1)
            quickSort(left, index - 1, array);
        if (right > index)
            quickSort(index, right, array);
    }

    private int partition(int left, int right, Comparable[] array) {
        int i = left;
        int j = right;

        Comparable pivot = array[(left + right) / 2];

        while (i <= j) {
            while (isLess(array[i], pivot)) {
                i++;
            }
            while (isLess(array[j], pivot)) {
                j--;
            }
            if(i <= j) {
                swap(array, i, j);
                this.putIntermediateResultInAQueue(array);
                i++;
                j--;
            }
        }
        return i;
    }
}
