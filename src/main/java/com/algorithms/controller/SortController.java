package com.algorithms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);
    private SortRepresentation sortRepresentation;

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    private boolean sortStarted;
    private Integer[] partition;

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

    @MessageMapping("/sort")
    public void getArray(Integer[] array) throws Exception {

        partition = array;
        sortStarted = true;
        sortRepresentation = new SortRepresentation(partition);

        for (int i = partition.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (partition[j].compareTo(partition[j + 1]) > 0) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    swap(j, j + 1, partition);
                    sortRepresentation.setIntermediate(partition);
                }
            }
        }
    }

    public void swap(int i, int j, Comparable[] array) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        if(sortStarted) {
            this.brokerMessagingTemplate.convertAndSend("/visualize/sorting", sortRepresentation);
        }
    }
}