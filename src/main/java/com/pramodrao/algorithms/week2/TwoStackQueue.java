package com.pramodrao.algorithms.week2;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author pramod.rao
 */
public class TwoStackQueue<T> {

    private Stack<T> input;
    private Stack<T> output;

    TwoStackQueue() {
        input = new Stack<T>();
        output = new Stack<T>();
    }

    public final void enqueue(T t) {
        input.push(t);
    }

    public final T dequeue() {
        if ( output.size() == 0 ) {
            if (input.size() == 0) {
                return null;
            } else {
                while (input.iterator().hasNext()) {
                    output.push(input.pop());
                }

            }
        }

        return output.pop();
    }

    public static void main(String[] args) {
        TwoStackQueue<String> a = new TwoStackQueue<String>();
        a.enqueue("A");
        a.enqueue("B");
        a.enqueue("C");
        StdOut.println(a.dequeue());
        a.enqueue("D");
        a.enqueue("E");
        StdOut.println(a.dequeue());
        a.enqueue("F");
        StdOut.println(a.dequeue());
        StdOut.println(a.dequeue());
        StdOut.println(a.dequeue());
        StdOut.println(a.dequeue());
        StdOut.println(a.dequeue());
    }
}
