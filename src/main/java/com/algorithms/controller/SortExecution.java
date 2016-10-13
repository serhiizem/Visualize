package com.algorithms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@Component
public class SortExecution implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SortExecution.class);

    private Integer[] partition;
    private Integer[] prevArray;

    private boolean sorted;

    public SortExecution() {
    }

    public SortExecution(Integer[] partition) {
        this.partition = partition;
        this.prevArray = partition;
    }

    public void setPartition(Integer[] partition) {
        this.partition = partition;
    }

    public void setPrevArray(Integer[] prevArray) {
        this.prevArray = prevArray;
    }

    public boolean isSorted() {
        return sorted;
    }

    public Integer[] getPartition() {
        return partition;
    }

    @Override
    public void run() {

        while (!sorted) {
            for (int i = partition.length - 1; i > 1; i--) {
                for (int j = 0; j < i; j++) {
                    if(partition[j].compareTo(partition[j + 1])  > 0) {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("Method run works");
                        log.info("Sorted: {}", sorted);
                        swap(j, j + 1, partition);
                    }
                    prevArray = partition;
                    if(asList(prevArray).equals(asList(partition))) {
                        sorted = true;
                    }

                }
            }
        }
    }

    public void swap(int i,int j , Comparable[] partition) {
        Comparable temp = partition[i];
        partition[i] = partition[j];
        partition[j] = temp;
    }
}
