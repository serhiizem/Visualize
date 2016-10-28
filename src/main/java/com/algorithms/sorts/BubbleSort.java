package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component("bubbleSort")
public class BubbleSort extends Sortable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(BubbleSort.class);

    private Long startTime;
    private Long elapsedTime;

    public BubbleSort() {
    }

    //TODO: Implement BPP to inject SortRepresentationObject to all sortAlgorithm components
    @Autowired
    public BubbleSort(SortRepresentation sortRepresentation) {
        super(sortRepresentation);
    }

    @PostConstruct
    public void checkSortRepresentationStatus() {
        log.info("SortRepresentation in bubble sort: {}", sortRepresentation);
    }

    public void sort() {

        sortRepresentation.setSortStarted(true);
        startTime = System.currentTimeMillis();

        Integer[] array = sortRepresentation.getIntermediateResult();

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(array[j] > array[j + 1]) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sortRepresentation.setIntermediateResult(array);
                    swap(j, j + 1, array);
                }
            }
        }
        elapsedTime = System.currentTimeMillis() - startTime;
        sortRepresentation.setElapsedTime(elapsedTime);
        log.info("Sorting finished. Elapsed time is: {}", elapsedTime);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sortRepresentation.setSortStarted(false);
    }

    public void swap(int i, int j, Integer[] array) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public Integer[] getResult() {
        return sortRepresentation.getIntermediateResult();
    }
}
