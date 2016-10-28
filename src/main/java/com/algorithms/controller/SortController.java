package com.algorithms.controller;

import com.algorithms.config.FactoryMock;
import com.algorithms.sorts.SortDetails;
import com.algorithms.sorts.SortInvoker;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.AlgorithmType;
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

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    private SortRepresentation sortRepresentation;
    private SortInvoker invoker;
    private SimpMessagingTemplate brokerMessagingTemplate;
    private FactoryMock factoryMock;

    @Autowired
    public SortController(SimpMessagingTemplate brokerMessagingTemplate,
                          SortInvoker invoker,
                          SortRepresentation sortRepresentation,
                          FactoryMock factoryMock) {

        this.sortRepresentation = sortRepresentation;
        this.brokerMessagingTemplate = brokerMessagingTemplate;
        this.invoker = invoker;
        this.factoryMock = factoryMock;
    }

    @MessageMapping("/sort")
    public void getArray(SortDetails sortDetails) throws Exception {

        Integer[] array = sortDetails.getArray();
        log.info("Array from the html page: {}", Arrays.toString(array));
        String sortType = sortDetails.getSortType();
        log.info("Sort type requested: {}", sortType);

        sortRepresentation.setIntermediateResult(array);

        Sorting algorithm = factoryMock.getAlgorithm(AlgorithmType.valueOf(sortType));

        invoker.startSortingAlgorithm(algorithm);
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