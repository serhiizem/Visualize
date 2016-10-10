package com.algorithms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SortExecution extends Thread {

    private static final Logger log = LoggerFactory.getLogger(SortExecution.class);

    private int[] partition = new int[10];

    public SortExecution() {
        for(int i = 0; i < partition.length; i++) {
            partition[i] = i + 100;
        }
    }

    public int[] getPartition() {
        return partition;
    }

    @Override
    public void run() {
        while (true) {
            partition[1]++;
            log.info("SortExecution thread: {}", partition[1]);
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
