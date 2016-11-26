package com.algorithms.service;

import com.algorithms.util.Queue;
import com.algorithms.entity.SortRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Responsible for sending intermediate results
 * of the sorting process to the user subscribed
 * to the required channel
 *
 * @author Zemlianiy
 * @version 1.0
 * @since
 */
@Service
public class DefaultSendService implements SendService {

    private SimpMessagingTemplate brokerMessagingTemplate;
    private Queue<SortRepresentation> sortRepresentationQueue;

    @Autowired
    public DefaultSendService(SimpMessagingTemplate brokerMessagingTemplate,
                              Queue<SortRepresentation> sortRepresentationQueue) {
        this.brokerMessagingTemplate = brokerMessagingTemplate;
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    /**
     * Sends an intermediate result of sorting computation to all the
     * subscribers of the predefined channel
     */
    public void sendIntermediateResult() {
        SortRepresentation intermediateResult = sortRepresentationQueue.dequeue();
        this.brokerMessagingTemplate.convertAndSend("/visualize/sorting", intermediateResult);
    }
}