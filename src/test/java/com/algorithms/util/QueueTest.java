package com.algorithms.util;

import com.algorithms.entity.SortRepresentation;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QueueTest {

    private Queue<SortRepresentation> queue;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        queue = new Queue<>();
    }

    @Test
    public void shouldEnqueue() {
        //when
        queue.enqueue(new SortRepresentation(new Integer[]{5, 3, 1, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 5, 1, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 1, 5, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 1, 2, 5}));

        //then
        assertThat(queue.getQueueSize(), is(4));
    }

    @Test
    public void shouldDequeue() {
        //when
        queue.enqueue(new SortRepresentation(new Integer[] {5, 3, 1, 2}));
        queue.enqueue(new SortRepresentation(new Integer[] {3, 5, 1, 2}));
        SortRepresentation lastElement = queue.peek();

        //then
        assertThat(queue.dequeue(), is(lastElement));
        lastElement = queue.peek();
        assertThat(queue.dequeue(), is(lastElement));
        assertThat(queue.getQueueSize(), is(0));
    }

    @Test
    public void shouldThrowAnErrorOnDequeuingFromAnEmptyQueue() {
        exception.expect(NoSuchElementException.class);
        exception.expectMessage("Cannot dequeue on empty queue");

        queue.dequeue();
    }

    @Test
    public void shouldThrowAnErrorOnPeekingFromEmptyQueue() {
        exception.expect(NoSuchElementException.class);
        exception.expectMessage("Cannot peek on empty queue");

        queue.peek();
    }

    @Test
    public void shouldGetFirst() {
        //given
        SortRepresentation firstElement = new SortRepresentation(new Integer[]{5, 3, 1, 2});

        //when
        queue.enqueue(firstElement);
        queue.enqueue(new SortRepresentation(new Integer[]{3, 5, 1, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 1, 5, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 1, 2, 5}));

        //then
        assertThat(queue.getFirst(), is(firstElement));
    }

    @Test
    public void shouldGetLast() {
        //given
        SortRepresentation lastElement = new SortRepresentation(new Integer[]{3, 1, 2, 5});

        //when
        queue.enqueue(new SortRepresentation(new Integer[]{5, 3, 1, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 5, 1, 2}));
        queue.enqueue(new SortRepresentation(new Integer[]{3, 1, 5, 2}));
        queue.enqueue(lastElement);

        //then
        assertThat(queue.getLast(), is(lastElement));
    }
}