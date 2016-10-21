package com.algorithms.controller;

import com.algorithms.sorts.InsertionSort;
import com.algorithms.sorts.SortDetails;
import com.algorithms.sorts.SortInvoker;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

//import com.algorithms.sorts.SelectionSort;
//import com.algorithms.sorts.SortAlgorithm;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    private SortRepresentation sortRepresentation = new SortRepresentation();

    private SortInvoker invoker;
    private SimpMessagingTemplate brokerMessagingTemplate;

    @Autowired
    public SortController(SimpMessagingTemplate brokerMessagingTemplate,
                          SortInvoker invoker) {

        this.brokerMessagingTemplate = brokerMessagingTemplate;
        this.invoker = invoker;
    }

    @MessageMapping("/sort")
    public void getArray(SortDetails sortDetails) throws Exception {

        Integer[] array = sortDetails.getArray();
        log.info("Array from the html page: {}", Arrays.toString(array));
        String sortType = sortDetails.getSortType();
        log.info("Sort type requested: {}", sortType);

        sortRepresentation.setIntermediate(array);

        Sorting insertionSort =
                new InsertionSort(array, sortRepresentation);

        invoker.startSortingAlgorithm(insertionSort);
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