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

import java.util.Arrays;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    private SortExecution runningSort;

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

    @MessageMapping("/array")
    public void getArray() throws Exception {
        runningSort = new SortExecution();
        runningSort.start();
    }

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
        if(runningSort != null){
            int[] ints = runningSort.getPartition();
            SortRepresentation sortRepresentation = new SortRepresentation(ints);
            log.info("State in scheduled sendMessage: {}, {}", ints[1], ints[2]);
            this.brokerMessagingTemplate.convertAndSend("/topic/greetings", sortRepresentation);
        }
    }
}