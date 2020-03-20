package com.pramodrao.algorithms.week4;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author pramod.rao
 */
public class BinaryHeap {

    private Comparable[] tree;
    private int N = 0;

    public boolean isEmpty() {
        if (null == tree || tree.length == 0) return true;
        return false;
    }

    public void insert(Comparable element) {
        checkAndResize();
        tree[N] = element;
        swim(N+1);
        N++;
    }

    public Comparable getMax() {
        return tree[0];
    }

    public void deleteMax() {
        swap(0, N-1);
        tree[N-1] = null;
        N--;
        sink(1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(i+1).append(':').append(tree[i]).append(',');
        }
        return sb.toString().substring(0, sb.length()-1);
    }

    private void sink(int k) {
        while ( 2*k < N ) {
            int child1 = 2*k;
            int child2 = 2*k + 1;

            if (tree[k-1].compareTo(tree[child1-1]) < 0 && tree[child1-1].compareTo(tree[child2-1]) > 0) {
                swap(k-1, child1-1);
                k = child1;
            } else if (tree[k-1].compareTo(tree[child2-1]) < 0 && tree[child2-1].compareTo(tree[child1-1]) > 0) {
                swap(k-1, child2-1);
                k = child2;
            } else break;
        }
    }

    private void swim(int k) {

        while ( k > 1 ) {
            int parent = k/2;
            if (tree[k-1].compareTo(tree[parent-1]) > 0) {
                swap(k-1, parent-1);
                k = parent;
            } else break;
        }
    }

    private void swap(int i, int j) {
        Comparable temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }

    private void checkAndResize() {
        if (null == tree) tree = new Comparable[1];
        else {
            int treeLen = tree.length;
            if (null != tree[treeLen-1]) {
                int newLen = treeLen * 2;
                Comparable[] temp = new Comparable[newLen];
                System.arraycopy(tree, 0, temp, 0, treeLen);
                tree = temp;
            }
        }
    }

    public static void main(String[] args) {
//        BinaryHeap heap = new BinaryHeap();
//        heap.insert(6);
//        heap.insert(8);
//        heap.insert(1);
//        heap.insert(9);
//        heap.insert(5);
//        heap.insert(2);
//        heap.insert(7);
//        heap.insert(8);
//        heap.insert(3);
//        heap.insert(11);
//        heap.insert(10);
//        heap.insert(4);
//        StdOut.println("Is the heap empty: " +heap.isEmpty());
//        StdOut.println("Current Max Element Before Delete: " +heap.getMax());
////        StdOut.println(heap.toString());
//        heap.deleteMax();
//        StdOut.println();
////        StdOut.println(heap.toString());
//        StdOut.println("Current Max Element After Delete: " +heap.getMax());
//        heap.deleteMax();
//        StdOut.println();
////        StdOut.println(heap.toString());
//        StdOut.println("Current Max Element After Delete: " +heap.getMax());
//        heap.deleteMax();
//        StdOut.println();
////        StdOut.println(heap.toString());
//        StdOut.println("Current Max Element After Delete: " +heap.getMax());
//        heap.deleteMax();
//        StdOut.println();
////        StdOut.println(heap.toString());
//        StdOut.println("Current Max Element After Delete: " +heap.getMax());

        BinaryHeap heap = new BinaryHeap();
        heap.insert(3);
        heap.insert(1);
        heap.insert(6);
        heap.insert(5);
        heap.insert(2);
        heap.insert(4);
        StdOut.println(heap.toString());
    }
}
