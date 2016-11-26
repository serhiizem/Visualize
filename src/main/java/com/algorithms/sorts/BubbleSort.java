package com.algorithms.sorts;

import com.algorithms.annotations.Sorter;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.algorithms.sorts.Sorting.isLess;
import static com.algorithms.sorts.Sorting.swap;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.nanoTime;

/**
 * Default implementation of the bubble sort algorithm
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since
 */
@Component("bubbleSort")
public class BubbleSort extends Queueable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(BubbleSort.class);
//    private Long elapsedTime; //TODO : EVALUATE ELAPSED TIME

    @Autowired
    public BubbleSort(Queue<SortRepresentation> sortRepresentationQueue) {
        super(sortRepresentationQueue);
    }

    @Sorter
    @Override
    public void sort(Comparable[] array) {
        log.info("*********************************************************");
        long startTime = nanoTime();
        for (int i = 0; i < array.length; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (isLess(array[j], (array[j-1]))) {
                    log.info("Swapping items with indices: {{},{}}", j, j-1);
                    this.putIntermediateResultInAQueue(array, nanoTime() - startTime);
                    swap(array, j, j - 1);
                }
            }
        }
        log.info("*********************************************************");

//        elapsedTime = nanoTime() - startTime;
        this.putIntermediateResultInAQueue(array, nanoTime() - startTime);
    }
}
