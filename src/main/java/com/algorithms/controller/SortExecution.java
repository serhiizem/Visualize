package com.algorithms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

public class SortExecution extends Thread {

    private static final Logger log = LoggerFactory.getLogger(SortExecution.class);

    private Integer[] partition;
    private Integer[] prevArray;

    private boolean sorted;

    public SortExecution(Integer[] partition) {
        this.partition = partition;
        this.prevArray = partition;
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
                        swap(j, j + 1, partition);
                    }
                    if(asList(prevArray).equals(asList(partition))) {
                        sorted = true;
                    }
                    prevArray = partition;

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
