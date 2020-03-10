package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author pramod.rao
 */
public class QuickSort {

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo; int j = hi+1;
        while (true) {
            while ( a[++i].compareTo(a[lo]) < 0 ) {
                if (i == hi) break;
            }

            while ( a[--j].compareTo(a[lo]) > 0 ) {
                if (j == lo) break;
            }

            if ( i >= j ) break;
            swap(a, i , j);
        }

        swap(a, lo, j);
        return j;
    }

    private static void swap(Comparable[] data, int i, int j) {
        Comparable x = data[i];
        data[i] = data[j];
        data[j] = x;
    }

    public static void main(String[] args) {
//        Comparable[] data = { 1, 20, 6, 4, 5 };
//        Comparable[] data = { 2, 1, 3, 1, 2 };
//        Comparable[] data = {1, 1, 1, 2, 2 };
//        Comparable[] data = {2, 4, 1, 3, 5 };
        Comparable[] data = {5, 2, 3, 8, 6, 1 };

        sort(data);
        StdOut.print("Sorted: ");
        for ( int i = 0; i < data.length; i++ ) {
            StdOut.print(data[i] +", ");
        }
    }
}
