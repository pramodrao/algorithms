package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author pramod.rao
 * Shuffling a linked list.
 * Given a singly-linked list containing nnn items, rearrange the items uniformly at random.
 * Your algorithm should consume a logarithmic (or constant) amount of extra memory and
 * run in time proportional to nlog⁡nn \log nnlogn in the worst case.
 */
public final class ArrayShuffle {

    public static void shuffle(int[] data) {
        int n = data.length;

        for ( int i = 0; i < n; i++ ) {
            int random = StdRandom.uniform(i+1);
            int swap = data[i];
            data[i] = data[random];
            data[random] = swap;
        }
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5 };
        ArrayShuffle.shuffle(data);
        for (int i = 0; i < data.length; i++){
            StdOut.print(data[i] +", ");
        }
        StdOut.println();
    }
}
