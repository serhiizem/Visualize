package com.algorithms.sorts;

import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;

/**
 * <p>Base class which is being extended by all the sorting algorithm implementations,
 * which means that its intermediate result of the computation can be placed in
 * a {@link Queue}. Contents of the queue are used for their further periodic dispatch
 * by a Spring scheduler as follows:
 * <pre>
 *     {@literal @}Scheduled(fixedRate = 1000)
 *     public void sendMessage() {
 *          if(!sortRepresentationQueue.isEmpty()) {
 *              sendService.sendIntermediateResult();
 *       }
 *     }
 * </pre>
 *
 * If you don't want intermediate results of the computation to be placed in a
 * queue, you can invoke method {@link #setAnalysed(Boolean)} with a true value
 * passed as its parameter</p>

 * @author  Zemlianiy
 * @version 1.0
 * @since   1.0
 */
public abstract class Queueable {

    private boolean isAnalysed;
    private Queue<SortRepresentation> sortRepresentationQueue;
    protected Long elapsedTime;

    public Queueable(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    public void setAnalysed(Boolean analysed) {
        isAnalysed = analysed;
    }

    public boolean isAnalysed() {
        return isAnalysed;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    protected void putIntermediateResultInAQueue(Comparable[] intermediateResult) {
        if(!isAnalysed) {
            sortRepresentationQueue
                    .enqueue(new SortRepresentation(intermediateResult.clone()));
        }
    }
}