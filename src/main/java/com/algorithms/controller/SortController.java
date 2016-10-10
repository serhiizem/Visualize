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

    private Thread runningSort;
    private Thread se;

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

    @MessageMapping("/array")
    public void getArray(String message) throws Exception {
        SortExecution se = new SortExecution();
        Thread t = new Thread(se);
        t.start();
        saveRunningSort(t);
    }

    private void saveRunningSort(Thread t) {
        runningSort = t;
    }


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        log.info("State in greeting controller: {}", runningSort.getState());

        return "Gotcha!";
    }
}