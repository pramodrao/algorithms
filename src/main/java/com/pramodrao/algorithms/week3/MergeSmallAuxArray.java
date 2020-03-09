package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author pramod.rao
 * Merging with smaller auxiliary array.
 * Suppose that the subarray a[0] to a[n−1] is sorted and the subarray a[n] to a[2∗n−1] is sorted.
 * How can you merge the two subarrays so that a[0] to a[2∗n−1] is sorted
 * using an auxiliary array of length nnn (instead of 2n2n2n)?
 */
public class MergeSmallAuxArray {

    public static final int[] merge(int[] data) {

        final int capacity = data.length;
        final int auxLen = capacity/2;
        int[] aux = new int[auxLen];
        System.arraycopy(data, 0, aux, 0, auxLen);

        int auxIndex = 0;
        int dataIndex = auxLen;
        for ( int i = 0; i < capacity; i++ ) {
            if ( auxIndex >= auxLen ) break;
            if ( dataIndex >= capacity ) {
                System.arraycopy(aux, auxIndex, data, i, (auxLen-auxIndex-1));
                break;
            }
            if ( aux[auxIndex] > data[dataIndex] ) {
                data[i] = data[dataIndex];
                dataIndex++;
            } else {
                data[i] = aux[auxIndex];
                auxIndex++;
            }
        }

        return data;
    }

    public static void main(String[] args) {

        int[] test = new int[10];
        test[0] = 1;
        test[1] = 3;
        test[2] = 6;
        test[3] = 8;
        test[4] = 9;
        test[5] = 2;
        test[6] = 4;
        test[7] = 5;
        test[8] = 7;
        test[9] = 10;

        test = MergeSmallAuxArray.merge(test);
        for ( int i = 0; i < 10; i++ ) {
            StdOut.println(test[i]);
        }

    }
}
