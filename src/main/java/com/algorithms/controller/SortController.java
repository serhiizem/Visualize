package com.algorithms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.util.Arrays;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    @Autowired
    private SortExecution runningSort;

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    private Thread t;

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

    @MessageMapping("/array")
    public void getArray(Integer[] array) throws Exception {
        log.info("Array to sort: {}", Arrays.toString(array));
        runningSort.setPartition(array);
        runningSort.setPrevArray(array);

        t = new Thread(runningSort);
        t.start();
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
        if(!runningSort.isSorted() && t != null){
            Comparable[] ints = runningSort.getPartition();
            log.info("Array in scheduled: {}", Arrays.toString(ints));
            log.info("Sorted variable: {}", runningSort.isSorted());
//            SortRepresentation sortRepresentation = new SortRepresentation(ints);
//            log.info("State in scheduled sendMessage: {}, {}", ints[1], ints[2]);
//            this.brokerMessagingTemplate.convertAndSend("/topic/greetings", sortRepresentation);
        }
    }
}