package com.algorithms.controller;

import com.algorithms.entity.SortDetails;
import com.algorithms.entity.SortRepresentation;
import com.algorithms.service.DefaultSendService;
import com.algorithms.sorts.Sorting;
import com.algorithms.util.Queue;
import com.algorithms.util.SortInvoker;
import com.algorithms.util.factories.AlgorithmFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

import static com.algorithms.entity.AlgorithmType.valueOf;

/**
 * Manages requests related to sorting process
 *
 * @author Zemlianiy
 * @version 1.0
 * @since 1.0
 */
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
     * When user accesses corresponding endpoint, performs sorting
     * process upon the array extracted from the request
     *
     * @param sortDetails object that is used to extract information
     *                    about the sort process to be performed
     */
    @MessageMapping("/sort")
    public void startSort(SortDetails sortDetails) {

        Comparable[] arrayToSort = sortDetails.getArray();
        log.info("Array received the html page: {}", Arrays.toString(arrayToSort));

        Sorting algorithm = getSortingAlgorithm(sortDetails);

        sortInvoker.sortArrayWithTheGivenAlgorithm(arrayToSort, algorithm);
    }

    /**
     * Performs a periodic dispatch of the saved intermediate
     * results of the computation.
     *
     * @see SortRepresentation
     */
    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        if (!sortRepresentationQueue.isEmpty()) {
            sendService.sendIntermediateResult();
        }
    }

    @GetMapping(value = "/sortArray")
    public String showMain() {
        return "index";
    }

    /**
     * Helper method used to obtain required implementation of {@link Sorting}
     * interface by its name requested from the view.
     *
     * @param sortDetails object that is used to fetch the name of the
     *                    sorting algorithm
     */
    private Sorting getSortingAlgorithm(SortDetails sortDetails) {
        String sortType = sortDetails.getSortType();
        log.info("Sort type requested: {}", sortType);

        return algorithmFactory.getSortingAlgorithm(valueOf(sortType));
    }
}