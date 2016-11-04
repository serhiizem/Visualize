package com.algorithms.service;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendService {

    private SimpMessagingTemplate brokerMessagingTemplate;
    private Queue<SortRepresentation> sortRepresentationQueue;

    @Autowired
    public SendService(SimpMessagingTemplate brokerMessagingTemplate,
                       Queue<SortRepresentation> sortRepresentationQueue) {
        this.brokerMessagingTemplate = brokerMessagingTemplate;
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    public void sendIntermediateResultToAView() {
        SortRepresentation intermediateResult = sortRepresentationQueue.dequeue();
        this.brokerMessagingTemplate.convertAndSend("/visualize/sorting", intermediateResult);
    }
}