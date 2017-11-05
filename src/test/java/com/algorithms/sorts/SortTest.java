package com.algorithms.sorts;

import com.algorithms.entity.SortRepresentation;
import com.algorithms.util.Queue;

public class SortTest {

    protected Comparable[] getSortedArrayFromQueue(Queue<SortRepresentation> sortRepresentations) {
        return sortRepresentations.getLast().getIntermediateResult();
    }
}
