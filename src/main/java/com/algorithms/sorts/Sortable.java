package com.algorithms.sorts;

import com.algorithms.util.SortRepresentation;

public abstract class Sortable {

    protected SortRepresentation sortRepresentation;

    public Sortable() {
    }

    public Sortable(SortRepresentation sortRepresentation) {
        this.sortRepresentation = sortRepresentation;
    }

    public void invokeAspectFromParent() {
        System.out.println("**********88invokeAspectFromParent8**************");
    }
}
