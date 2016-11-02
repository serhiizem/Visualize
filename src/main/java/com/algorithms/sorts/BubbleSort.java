package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.algorithms.sorts.Sorting.swap;

@Component("bubbleSort")
public class BubbleSort extends Sortable implements Sorting {

    private static final Logger log = LoggerFactory.getLogger(BubbleSort.class);

    public BubbleSort() {
    }

    //TODO: Implement BPP to inject SortRepresentationObject to all sortAlgorithm components
    @Autowired
    public BubbleSort(SortRepresentation sortRepresentation) {
        super(sortRepresentation);
    }

    public void sort() {

        Integer[] array = sortRepresentation.getIntermediateResult();

        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if(array[j] > array[j + 1]) {
                    pauseExecutionForNumberOfSeconds(2);
                    sortRepresentation.setIntermediateResult(array);
                    swap(j, j + 1, array);
                }
            }
        }
    }

    private void pauseExecutionForNumberOfSeconds(int secondsToPause) {
        try {
            TimeUnit.SECONDS.sleep(secondsToPause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer[] getResult() {
        return sortRepresentation.getIntermediateResult();
    }
}
