package com.pramodrao.algorithms.week2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author pramod.rao
 */
public class StackWithMax<T extends Comparable> {

    class Node<T> {
        T item;
        Node prev;
    }

    private Node<T> last = null;
    private Node<T> max = null;
    private int size = 0;

    public final void push(T t) {

        Node node = new Node();
        node.item = t;
        node.prev = last;
        last = node;
        if ( max == null || t.compareTo(max.item) >= 0  ) {
            Node maxNode = new Node();
            maxNode.item = t;
            maxNode.prev = max;
            max = maxNode;
        }
        size++;
    }

    public final T pop() {

        if ( null == last ) return null;
        else {
            T returnItem = last.item;
            last = last.prev;

            if (max.item.compareTo(returnItem) == 0) {
                max = max.prev;
            }
            return returnItem;
        }
    }

    public final T getMax() {
        return max.item;
    }

    public static void main(String[] args) {
        StackWithMax<Integer> s = new StackWithMax<Integer>();
        s.push(1);
        s.push(2);
        s.push(5);
        s.push(0);
        StdOut.println(s.getMax());
        s.pop();
        StdOut.println(s.getMax());
        s.push(5);
        StdOut.println(s.getMax());
        s.pop();
        StdOut.println(s.getMax());
        s.pop();
        StdOut.println(s.getMax());
    }
}