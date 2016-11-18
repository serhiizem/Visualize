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

    private Comparator<Comparable> comparator; // in order to use QuickSort algorithm in generation classes

    @Autowired
    public QuickSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
        comparator = (a, b) -> a.compareTo(b);
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
            while (comparator.compare(array[i], pivot) < 0) {
                i++;
            }
            while (comparator.compare(pivot, array[j]) < 0) {
                j--;
            }
            if(i <= j) {
                swap(array, i, j);
                putIntermediateResultInAQueue(array);
                i++;
                j--;
            }
        }
        return i;
    }
}
