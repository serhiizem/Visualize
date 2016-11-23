package com.algorithms.sorts;

import com.algorithms.annotations.Sorter;
import com.algorithms.util.Queue;
import com.algorithms.entity.SortRepresentation;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.isLess;

@Component("mergeSort")
public class MergeSort extends Queueable implements Sorting {

    public MergeSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Sorter
    @Override
    public void sort(Comparable[] array) {
        Comparable[] helper = new Comparable[array.length];
        sort(array, helper, 0, array.length - 1);
    }

    private void sort(Comparable[] array, Comparable[] helper, int low, int high) {
        if(high <= low) return;
        int mid = this.calculateMidIndex(low, high);
        sort(array, helper, low, mid);
        sort(array, helper, mid + 1, high);
        merge(array, helper, low, mid, high);
    }

    private int calculateMidIndex(int low, int high) {
        return low + (high - low) / 2;
    }

    private void merge(Comparable[] array, Comparable[] helper, int low, int mid, int high) {
        this.populateArrayFromLowTOHigh(array, helper, low, high);

        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) {
            if(isLess(mid, i)) array[k] = helper[j++];
            else if(isLess(high, j)) array[k] = helper[i++];
            else if(isLess(helper[j], helper[i])) array[k] = helper[j++];
            else array[k] = helper[i++];
        }
        this.putIntermediateResultInAQueue(array);
    }

    private void populateArrayFromLowTOHigh(Comparable[] array,
                                            Comparable[] helper,
                                            int low, int high) {
        for (int k = low; k <= high; k++) {
            helper[k] = array[k];
        }
    }
}
