package com.algorithms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class SortExecution implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(SortExecution.class);

    private int state;

    public int getState() {
        return state;
    }

    @Override
    public void run() {
        while (true) {
            log.info("SortExecution thread: {}", state);
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state++;
        }
    }
}
