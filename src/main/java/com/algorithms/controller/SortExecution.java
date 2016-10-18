package com.algorithms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@Component
public class SortExecution {

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

    public void swap(int i,int j , Comparable[] partition) {
        Comparable temp = partition[i];
        partition[i] = partition[j];
        partition[j] = temp;
    }
}
