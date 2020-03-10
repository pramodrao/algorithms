package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author pramod.rao
 */
public class QuickSelect {

    public static Comparable select(Comparable[] data, int k) {
        int lo = 0;
        int hi = data.length -1;
        int elementIdex = -1;
        while (hi > lo) {
            int j = partition(data, hi, lo);
            if (j > k) hi = j-1;
            else if (j < k) lo = j+1;
            else {
                elementIdex = j;
                break;
            }
        }
        return data[elementIdex];
    }

    private static int partition(Comparable[] data, int hi, int lo) {
        int i = lo; int j = hi+1;
        while (true) {
            while(data[++i].compareTo(data[lo]) < 0) {
                if ( i == hi ) break;
            }

            while(data[--j].compareTo(data[hi]) > 0) {
                if ( j == lo ) break;
            }

            if ( i >=j ) break;
            swap(data, i , j);
        }

        swap(data, lo, j);
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

        Comparable selected = select(data, 4);
        StdOut.print("Selected Element: " +selected);
//        for ( int i = 0; i < data.length; i++ ) {
//            StdOut.print(data[i] +", ");
//        }
    }
}
