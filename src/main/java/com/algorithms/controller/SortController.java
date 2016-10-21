package com.algorithms.controller;

import com.algorithms.sorts.SelectionSort;
import com.algorithms.sorts.SortInvoker;
//import com.algorithms.sorts.SelectionSort;
//import com.algorithms.sorts.SortAlgorithm;
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

    private SortRepresentation sortRepresentation;
    private SortInvoker invoker;
    private SimpMessagingTemplate brokerMessagingTemplate;

    @Autowired
    public SortController(SimpMessagingTemplate brokerMessagingTemplate,
                          SortRepresentation sortRepresentation,
                          SortInvoker invoker) {

        this.brokerMessagingTemplate = brokerMessagingTemplate;
        this.sortRepresentation = new SortRepresentation();
        this.invoker = new SortInvoker();
    }

    @MessageMapping("/sort")
    public void getArray(Integer[] array) throws Exception {

        SelectionSort selectionSort =
                new SelectionSort(array, sortRepresentation);

        invoker.startSortingAlgorithm(selectionSort);
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        if(sortRepresentation.isSortStarted()) {
            this.brokerMessagingTemplate.convertAndSend("/visualize/sorting", sortRepresentation);
        }
    }

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

}