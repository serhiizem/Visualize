package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.setOut;

public abstract class Queueable {

    protected Long startTime;

    private Queue<SortRepresentation> sortRepresentationQueue;

    public Queueable(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    protected void putIntermediateResultInAQueue(Comparable[] intermediateResult, Long elapsedTime) {
        System.out.println("currentTimeMillis() - startTime: " + (currentTimeMillis() - startTime));
        sortRepresentationQueue
                .enqueue(new SortRepresentation(intermediateResult.clone(), elapsedTime));
    }
}