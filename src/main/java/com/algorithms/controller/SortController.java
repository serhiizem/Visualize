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

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    private boolean sortStarted;
    private Integer[] partition;

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

    @MessageMapping("/array")
    public void getArray(Integer[] array) throws Exception {
        log.info("Array to sort: {}", Arrays.toString(array));
        partition = array;
        sortStarted = true;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < partition.length; j++) {
                TimeUnit.SECONDS.sleep(2);
                partition[j] += 30;
            }
        }



        /*for (int i = partition.length - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (partition[j].compareTo(partition[j + 1]) > 0) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        log.info("Array in scheduled: {}", Arrays.toString(partition));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    swap(j, j + 1, partition);
                }
            }
        }*/
    }

    public void swap(int i, int j, Comparable[] array) {
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        if(sortStarted) {
            log.info("Current partition: {}", Arrays.asList(partition));
//            SortRepresentation sortRepresentation = new SortRepresentation(ints);

            /*int[] ints = new int[10];
            for (int i = 0; i < 10; i++) {
                ints[i] = i;
            }*/

            log.info("State in scheduled sendMessage: {}, {}", partition);
            this.brokerMessagingTemplate.convertAndSend("/topic/greetings", partition);
        }
    }

    public Comparable[] getPartition() {
        return partition;
    }
}