package com.pramodrao.algorithms.week2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author pramod.rao
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size = 0;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[])new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {

        if ( null == item ) throw new IllegalArgumentException("Null items not allowed.");
        if (queue.length == size) {
            resizeQueue(size*2);
        }
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if ( size == 0 ) throw new NoSuchElementException("Cannot remove on an empty queue.");
        int index = StdRandom.uniform(size);
        Item item = queue[index];

        int lastIndex = size - 1;
        queue[index] = queue[lastIndex];
        queue[lastIndex] = null;
        size--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if ( size == 0 ) throw new NoSuchElementException("Cannot remove on an empty queue.");
        int index = StdRandom.uniform(size);
        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
    }

    private void resizeQueue(int capacity) {
        Item[] swap = (Item[]) new Object[capacity];
        for ( int i = 0; i < size; i++ ) {
            swap[i] = queue[i];
        }
        queue = swap;
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        int[] indexes;
        int currIndex;

        public RandomizedQueueIterator() {
            indexes = new int[size];
            for (int i = 0; i < size; i++ ) {
                indexes[i] = i;
            }
            StdRandom.shuffle(indexes);
            int currIndex = 0;
        }

        public boolean hasNext() {
            return (currIndex < size);
        }

        public Item next() {
            if (hasNext()) {
                int index = indexes[currIndex];
                Item item = (Item) queue[index];
                currIndex++;
                return item;
            } else
                throw new NoSuchElementException("Cannot iterate an empty queue.");
        }

        public void remove() {
            throw new UnsupportedOperationException("This operation is not supported.");
        }
    }
    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");
        rq.enqueue("E");
        rq.enqueue("F");
        rq.enqueue("G");

        Iterator<String> iter = rq.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
        }

        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());

        StdOut.println(rq.size());
        StdOut.println(rq.isEmpty());

        iter = rq.iterator();
        while (iter.hasNext()) {
            StdOut.println(iter.next());
        }

    }

}