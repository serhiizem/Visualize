package com.algorithms;

import com.algorithms.util.Queue;
import com.algorithms.util.SortRepresentation;
import org.junit.Before;
import org.junit.Test;

public class QueueTest {

    private Queue<SortRepresentation> queue;

    @Before
    public void setUp() throws Exception {
        queue = new Queue<>();

        queue.enqueue(new SortRepresentation(new Integer[]{5, 3, 1, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 5, 1, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 1, 5, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 1, 2, 5}));
    }

    @Test
    public void shouldEnqueue() {

        for(SortRepresentation sr: queue) {
            System.out.println(sr.toString());
        }
        /*
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        */
    }
}