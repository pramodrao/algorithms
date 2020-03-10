package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author pramod.rao
 */
public class FindingDuplicates {

    public static void sort(Comparable[] data) {
        sort(data, 0, data.length-1);
    }

    private static void sort(Comparable[] data, int lo, int hi) {
        if (hi < lo) return;

        int lt = lo; int i=lo; int gt=hi;
        while (i <= gt) {
            int compare = data[i].compareTo(data[lt]);
            if(compare < 0) swap(data, lt++, i++);
            else if (compare > 0) swap(data, i, gt--);
            else i++;
        }
        sort(data, lo, lt-1);
        sort(data, gt+1, hi);
    }

    private static void swap(Comparable[] data, int i, int j) {
        Comparable x = data[i];
        data[i] = data[j];
        data[j] = x;
    }

    public static void main(String[] args) {
        String[] data = new String[]{"P","A","B","X","W","P","P","V","P","D","P","C","Y","Z"};
        sort(data);

        for ( int i = 0; i < data.length-1; i++ ){
            StdOut.print(data[i] +",");
        }
        StdOut.println(data[data.length-1]);

    }
}
