package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.swap;
import static java.lang.System.nanoTime;

//TODO: NANOTIMES ARE NOT BEING ADDED TO EACH OTHER
@Component("bubbleSortReverse")
public class BubbleSortReverse extends Queueable implements Sorting {

    @Autowired
    public BubbleSortReverse(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    public void sort(Integer[] array) {

        long nanoTime = System.nanoTime();
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(array[j] > array[j + 1]) {
                    putIntermediateResultInAQueue(array, System.nanoTime() - nanoTime);
                    swap(j, j + 1, array);
                }
            }
        }
        putIntermediateResultInAQueue(array, System.nanoTime() - nanoTime);
    }
}