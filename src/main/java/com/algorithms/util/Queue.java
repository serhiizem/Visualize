package com.algorithms.util;

import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.NoSuchElementException;

@Component
public class Queue<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int queueSize;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        this.first = null;
        this.last = null;
        this.queueSize = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Item peek() {
        if(isEmpty()) throw new NoSuchElementException("Cannot peek on empty queue");
        return first.item;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        queueSize++;
    }

    public Item dequeue() {
        if(isEmpty()) throw new NoSuchElementException("Cannot dequeue on empty queue");
        Item item = first.item;
        first = first.next;
        queueSize--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    public Item getFirst() {
        return first.item;
    }

    public Item getLast() {
        return last.item;
    }

    public int getQueueSize() {
        return queueSize;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> currentNode;

        public ListIterator(Node<Item> current) {
            this.currentNode = current;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if(isEmpty())
                throw new NoSuchElementException("Cannot go to the next element on empty queue");
            Item item = currentNode.item;
            currentNode = currentNode.next;

            return item;
        }

        @Override
        public void remove() {
            throw new IllegalStateException("It is not expected to invoke this method");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item: this) {
            sb.append(item);
        }
        return sb.toString();
    }
}