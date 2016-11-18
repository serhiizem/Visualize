package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.entity.SortRepresentation;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.isLess;

@Component("mergeSort")
public class MergeSort extends Queueable implements Sorting {

    public MergeSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Override
    public void sort(Comparable[] array) {
        Comparable[] helper = new Comparable[array.length];
        sort(array, helper, 0, array.length - 1);
    }

    private void sort(Comparable[] array, Comparable[] helper, int low, int high) {
        if(high <= low) return;
        int mid = low + (high - low) / 2;
        sort(array, helper, low, mid);
        sort(array, helper, mid + 1, high);
        merge(array, helper, low, mid, high);
    }

    private void merge(Comparable[] array, Comparable[] helper, int low, int mid, int high) {
        for (int k = low; k <= high; k++) {
            helper[k] = array[k];
        }

        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; k++) { //int k = low; not k = 0
            if(i > mid) array[k] = helper[j++];
            else if(j > high) array[k] = helper[i++];
            else if(isLess(helper[j], helper[i])) array[k] = helper[j++]; //else if not just else
            else array[k] = helper[i++];
        }
        this.putIntermediateResultInAQueue(array);
    }
}
