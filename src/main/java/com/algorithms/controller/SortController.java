package com.algorithms.controller;

import com.algorithms.config.AlgorithmFactory;
import com.algorithms.util.SortDetails;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
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

import static com.algorithms.util.AlgorithmType.valueOf;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    private SimpMessagingTemplate brokerMessagingTemplate;
    private AlgorithmFactory algorithmFactory;
    private Queue<SortRepresentation> sortRepresentationQueue;

    @Autowired
    public SortController(SimpMessagingTemplate brokerMessagingTemplate,
                          AlgorithmFactory algorithmFactory,
                          Queue<SortRepresentation> sortRepresentationQueue) {

        this.brokerMessagingTemplate = brokerMessagingTemplate;
        this.algorithmFactory = algorithmFactory;
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    @MessageMapping("/sort")
    public void getArray(SortDetails sortDetails) throws Exception {

        Integer[] array = sortDetails.getArray();
        log.info("Array from the html page: {}", Arrays.toString(array));
        String sortType = sortDetails.getSortType();
        log.info("Sort type requested: {}", sortType);

        Sorting algorithm = algorithmFactory.getAlgorithm(valueOf(sortType));

        algorithm.sort(array);
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        if(!sortRepresentationQueue.isEmpty()) {
            sendIntermediateResultToAView();
        }
    }

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

    private void sendIntermediateResultToAView() {

        SortRepresentation intermediateResult = sortRepresentationQueue.dequeue();
        this.brokerMessagingTemplate.convertAndSend("/visualize/sorting", intermediateResult);
    }
}