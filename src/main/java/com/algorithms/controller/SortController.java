package com.algorithms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    private SortExecution runningSort;

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

    @MessageMapping("/array")
    public void getArray(String message) throws Exception {
        runningSort = new SortExecution();
        runningSort.start();
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public SortRepresentation getCurrentPartition(String message) throws Exception {
        int[] ints = runningSort.getPartition();
        log.info("State in greeting controller: {}, {}", ints[1], ints[2]);

        return new SortRepresentation(ints);
    }
}