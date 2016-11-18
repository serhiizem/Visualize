package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.setOut;

public abstract class Queueable {

    private Queue<SortRepresentation> sortRepresentationQueue;

    public Queueable(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    protected void putIntermediateResultInAQueue(Comparable[] intermediateResult) {
        sortRepresentationQueue
                .enqueue(new SortRepresentation(intermediateResult.clone()));
    }
}