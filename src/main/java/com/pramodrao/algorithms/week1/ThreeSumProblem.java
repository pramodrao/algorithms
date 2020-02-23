package com.pramodrao.algorithms.week1;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author pramod.rao
 */
public class ThreeSumProblem {

    public ThreeSumProblem( int[] elements ) {
        int count = 0;
        Arrays.sort(elements);
        int length = elements.length;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                int twoSum = elements[i] + elements[j];
                if ( find(twoSum, elements) ) count++;
            }
        }
        StdOut.println("Count (Three Sum): " +count);
    }

    private boolean find(int key, int[] elements) {
        int l = 0;
        int h = elements.length - 1;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (key > elements[mid]) l = mid + 1;
            else if (key < elements[mid]) h = mid - 1;
            else return true;
        }
        return false;
    }

}
