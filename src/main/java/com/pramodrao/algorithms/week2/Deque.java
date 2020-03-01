package com.pramodrao.algorithms.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author pramod.rao
 */
public class Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private Node<Item> first;
    private Node<Item> last;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if ( null == item ) throw new IllegalArgumentException("Null items not allowed.");

        Node<Item> node = new Node<Item>();
        node.item = item;
        node.next = null;

        if ( null != first ) {
            node.previous = first;
            first.next = node;
            if ( null == last ) last = first;
        } else {
            if ( null != last ) {
                node.previous = last;
                last.next = node;
            }
        }
        first = node;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {

        if ( null == item ) throw new IllegalArgumentException("Null items not allowed.");

        Node<Item> node = new Node<Item>();
        node.item = item;
        node.previous = null;

        if ( null != last ) {
            node.next = last;
            last.previous = node;
            if ( null == first ) first = last;
        } else {
            if ( null != first ) {
                node.next = first;
                first.previous = node;
            }
        }
        last = node;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if ( size == 0 ) throw new NoSuchElementException("Cannot remove on an empty Deque.");

        Item returnItem;
        if ( first == null ) {
             returnItem = last.item;
            last = null;
        } else {
            returnItem = first.item;
            first = first.previous;
            if (null != first) first.next = null;
            else last = null;
        }
        size--;
        return returnItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if ( size == 0 ) throw new NoSuchElementException("Cannot remove on an empty Deque.");

        Item returnItem;
        if ( null == last ) {
            returnItem = first.item;
            first = null;
        } else {
            returnItem = last.item;
            last = last.next;
            if ( null != last ) last.previous = null;
            else first = null;
        }
        size--;
        return returnItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>((null == first) ? last : first);
    }

    private class Node<T> {
        T item;
        Node<T> previous;
        Node<T> next;
    }

    private class DequeIterator<T> implements Iterator<T> {

        private Node<T> current;

        public DequeIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() {
            if (hasNext()) {
                T item = current.item;
                current = current.previous;
                return item;
            } else
                throw new NoSuchElementException("Cannot iterate an empty deque.");
        }

        public void remove() {
            throw new UnsupportedOperationException("This operation is not supported.");
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        while (true) {
            String[] e = StdIn.readLine().split(",");
            if (e[0].equalsIgnoreCase("af")) {
                deque.addFirst(Integer.parseInt(e[1]));
                StdOut.print("Deque Size: " +deque.size() +" - Elements in deque: ");
                for (Integer integer : deque) {
                    StdOut.print(integer);
                    StdOut.print(", ");
                }
                StdOut.println();
            } else if (e[0].equalsIgnoreCase("al")) {
                deque.addLast(Integer.parseInt(e[1]));
                StdOut.print("Deque Size: " +deque.size() +" - Elements in deque: ");
                for (Integer integer : deque) {
                    StdOut.print(integer);
                    StdOut.print(", ");
                }
                StdOut.println();
            } else if (e[0].equalsIgnoreCase("size")) {
                StdOut.println("Size is: " +deque.size());
            } else if (e[0].equalsIgnoreCase("rf")) {
                try{
                    StdOut.println("First Element: " +deque.removeFirst());
                } catch (NoSuchElementException nse){nse.printStackTrace();}

                StdOut.print("Deque Size: " +deque.size() +" - Elements in deque: ");
                for (Integer integer : deque) {
                    StdOut.print(integer);
                    StdOut.print(", ");
                }
                StdOut.println();

            } else if (e[0].equalsIgnoreCase("rl")) {
                try{
                    StdOut.println("Last Element: " +deque.removeLast());
                } catch (NoSuchElementException nse){nse.printStackTrace();}

                StdOut.print("Deque Size: " +deque.size() +" - Elements in deque: ");
                for (Integer integer : deque) {
                    StdOut.print(integer);
                    StdOut.print(", ");
                }
                StdOut.println();

            } else if (e[0].equalsIgnoreCase("iterate")) {

                Iterator<Integer> iter = deque.iterator();
                try {
                    iter.remove();
                } catch (UnsupportedOperationException nse){StdOut.println("Exception thrown on calling remove!!");}

                StdOut.print("Elements in deque: ");
                while (iter.hasNext()) {
                    StdOut.print(iter.next());
                    StdOut.print(", ");
                }
                StdOut.println("\nLast element reached. Now throw an exception");
                try {
                    iter.next();
                } catch (NoSuchElementException nse){StdOut.println("Exception thrown!!");}
            } else {
                StdOut.println("Bye Bye!!");
                return;
            }
            StdOut.println("Next Command");
        }
    }

}