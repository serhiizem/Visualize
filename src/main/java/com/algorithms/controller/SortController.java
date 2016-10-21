package com.algorithms.controller;

import com.algorithms.sorts.SelectionSort;
import com.algorithms.sorts.SortAlgorithm;
import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);
    private boolean sortStarted;
    
    private SortRepresentation sortRepresentation = new SortRepresentation();

    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    @MessageMapping("/sort")
    public void getArray(Integer[] array) throws Exception {

        sortStarted = true;
        SortAlgorithm sortAlgorithm = new SelectionSort(array, brokerMessagingTemplate);
        sortAlgorithm.sort();
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        if(sortStarted) {
            this.brokerMessagingTemplate.convertAndSend("/visualize/sorting", sortRepresentation);
        }
    }

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

}