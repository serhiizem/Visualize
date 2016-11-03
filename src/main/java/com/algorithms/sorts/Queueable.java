package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;

public abstract class Queueable {

    private Queue<SortRepresentation> sortRepresentationQueue;

    public Queueable(Queue<SortRepresentation> sortRepresentationQueue) {
        this.sortRepresentationQueue = sortRepresentationQueue;
    }

    protected void putSortRepresentationInAQueue(Integer[] intermediateResult) {
        sortRepresentationQueue.enqueue(new SortRepresentation(intermediateResult.clone()));
    }
}