package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.In;

/**
 * @author pramod.rao
 */
public class DecimalDominants {

    public static void getValues(int[] data) {

        int n = data.length;
        int[][] counts = new int[n][];
        for ( int i = 0; i < n; i++) {
            if ( null != counts[data[i]]) {
                counts[data[i]][1]++;
            } else {
                counts[data[i]][1] = 1;
            }
        }

        for ( int i = 0; i < max; i++ ) {

        }
    }
}
