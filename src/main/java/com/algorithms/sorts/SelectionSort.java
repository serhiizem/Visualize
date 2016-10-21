package com.algorithms.sorts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

public class SelectionSort extends SortAlgorithm {

    private static final Logger log = LoggerFactory.getLogger(SelectionSort.class);

    private SimpMessagingTemplate brokerMessagingTemplate;

    public SelectionSort(Integer[] array,
                         SimpMessagingTemplate brokerMessagingTemplate) {
        super(array);
        this.brokerMessagingTemplate = brokerMessagingTemplate;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public Integer[] sort() {

        sortRepresentation.setIntermediate(array);

        for (int x = 0; x < array.length; x++) {

            int minimum = x;

            for(int y = x; y < array.length; y++) {
                if(array[minimum] > array[y]) minimum = y;
            }

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("Representation is being renewed");
            sortRepresentation.setIntermediate(array);
            swap(x, minimum, array);
            log.info("Swap has performed");
        }

        return array;
    }
}