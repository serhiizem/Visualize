package com.algorithms.controller;

import com.algorithms.util.factories.AlgorithmFactory;
import com.algorithms.service.DefaultSendService;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import com.algorithms.entity.SortDetails;
import com.algorithms.util.SortInvoker;
import com.algorithms.entity.SortRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

import static com.algorithms.entity.AlgorithmType.valueOf;

@Controller
public class SortController {

    private static final Logger log = LoggerFactory.getLogger(SortController.class);

    private DefaultSendService sendService;
    private AlgorithmFactory algorithmFactory;
    private Queue<SortRepresentation> sortRepresentationQueue;
    private SortInvoker sortInvoker;

    @Autowired
    public SortController(DefaultSendService sendService,
                          AlgorithmFactory algorithmFactory,
                          Queue<SortRepresentation> sortRepresentationQueue,
                          SortInvoker sortInvoker) {
        this.sortInvoker = sortInvoker;
        this.sendService = sendService;
        this.algorithmFactory = algorithmFactory;
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    /**
     * @param sortDetails object that is used to extract information
     *                    about the sort process to be performed
     */
    @MessageMapping("/sort")
    public void startSort(SortDetails sortDetails) {

        Integer[] arrayToSort = sortDetails.getArray();
        log.info("Array received the html page: {}", Arrays.toString(arrayToSort));

        Sorting algorithm = getSortingAlgorithm(sortDetails);

        sortInvoker.sortArrayWithTheGivenAlgorithm(arrayToSort, algorithm);
    }

    @Scheduled(fixedRate = 2000)
    public void sendMessage() {
        if(!sortRepresentationQueue.isEmpty()) {
            sendService.sendIntermediateResult();
        }
    }

    @GetMapping(value = "/sortArray")
    public String showMain() {
        return "index";
    }

    private Sorting getSortingAlgorithm(SortDetails sortDetails) {
        String sortType = sortDetails.getSortType();
        log.info("Sort type requested: {}", sortType);

        return algorithmFactory.getSortingAlgorithm(valueOf(sortType));
    }
}