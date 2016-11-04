package com.algorithms.controller;

import com.algorithms.config.AlgorithmFactory;
import com.algorithms.service.SendService;
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
import java.util.List;

import static com.algorithms.util.AlgorithmType.valueOf;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    private SendService sendService;
    private AlgorithmFactory algorithmFactory;
    private Queue<SortRepresentation> sortRepresentationQueue;

    @Autowired
    public SortController(SendService sendService,
                          AlgorithmFactory algorithmFactory,
                          Queue<SortRepresentation> sortRepresentationQueue) {

        this.sendService = sendService;
        this.algorithmFactory = algorithmFactory;
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    @MessageMapping("/sort")
    public void getArray(SortDetails sortDetails) throws Exception {

        Integer[] arrayToSort = sortDetails.getArray();
        SortControllerLogger.logArrayReceivedFromView(arrayToSort);
        String sortType = sortDetails.getSortType();
        SortControllerLogger.logRequestedSortType(sortType);
        Sorting algorithm = algorithmFactory.getAlgorithm(valueOf(sortType));

        algorithm.sort(arrayToSort);
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        if(!sortRepresentationQueue.isEmpty()) {
            sendService.sendIntermediateResultToAView();
        }
    }

    @GetMapping(value = "/")
    public String showMain() {
        return "index";
    }

    private static class SortControllerLogger {

        private static final Logger log = LoggerFactory.getLogger(SortController.class);

        private static void logArrayReceivedFromView(Integer[] arrayToSort) {
            log.info("Array received the html page: {}", Arrays.toString(arrayToSort));
        }

        private static void logRequestedSortType(String sortType) {
            log.info("Sort type requested: {}", sortType);
        }
    }
}