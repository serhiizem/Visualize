package com.algorithms.sorts;

import com.algorithms.util.Queue;
import com.algorithms.entity.SortRepresentation;

import javax.swing.text.StyledEditorKit;

/**
 * Base class which is being extended by all the sorting
 * algorithm implementations, which means that an intermediate
 * result of the computation can be placed in a {@link Queue}
 *
 * @author  Zemlianiy
 * @version 1.0
 * @since
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