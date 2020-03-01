package com.pramodrao.algorithms.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pramod.rao
 */
public class StackOfStrings {

    Node start;
    int size = 0;

    StackOfStrings() {
    }

    void push(String element) {

        Node node = new Node();
        node.item = element;

        if (null == start) {
            start = node;
        } else {
            Node old = start;
            start = node;
            start.next = old;
        }
        size++;
    }

    String pop() {
        if ( null == start ) return "";

        String returnData = start.item;
        if ( null != start.next ) {
            start = start.next;
        } else {
            start = null;
        }
        size--;
        return returnData;
    }

    boolean isEmpty() {
        return (size == 0);
    }

    int size() {
        return size;
    }

    public static void main(String[] args) {

        List<String> a = new ArrayList<String>();
        a.add("B");
        a.add("C");
        a.add("D");
        a.add("E");
        System.out.println(a.toString());

        a.add(0, "A");
        System.out.println(a.toString());

//        StackOfStrings stack = new StackOfStrings();
//        while (!StdIn.isEmpty()) {
//            String s = StdIn.readString();
//            if (s.equals("-")) StdOut.println(stack.pop());
//            else if (s.equals("CMD-SIZE")) StdOut.println(stack.size());
//            else if (s.equals("CMD-EMPTY")) StdOut.println(stack.isEmpty());
//            else stack.push(s);
//        }
    }
}

class Node {
    String item;
    Node next;
}
