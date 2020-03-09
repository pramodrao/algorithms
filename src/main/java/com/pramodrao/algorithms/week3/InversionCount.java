package com.pramodrao.algorithms.week3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

import static java.util.Arrays.*;

/**
 * @author pramod.rao
 * Counting inversions.
 * An inversion in an array a[]a[\,]a[] is a pair of entries a[i]a[i]a[i] and a[j]a[j]a[j]
 * such that i<ji < ji<j but a[i]>a[j]a[i] > a[j]a[i]>a[j].
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 */
public class InversionCount {

    private static int sortAndCount(int[] data, int leftIndex, int midIndex, int rightIndex)
    {
        int[] left = new int[(midIndex+1)-leftIndex];
        System.arraycopy(data, leftIndex, left, 0, (midIndex+1)-leftIndex);

        int[] right = new int[rightIndex-midIndex];
        System.arraycopy(data, midIndex+1, right, 0, rightIndex - midIndex);

        int inverseCount = 0;
        int leftI = 0;
        int rightI = 0;
        for ( int i = leftIndex; i <= data.length; i++ ) {

            if ( leftI >= left.length ) {
                System.arraycopy(right, rightI, data, i, right.length - rightI);
                break;
            }

            if ( rightI >= right.length ) {
                System.arraycopy(left, leftI, data, i, left.length - leftI);
                break;
            }

            if ( left[leftI] > right[rightI] ) {
                data[i] = right[rightI];
                rightI++;
                inverseCount += (midIndex + rightI - i);
            } else {
                data[i] = left[leftI];
                leftI++;
            }
        }
        return inverseCount;
    }

    private static int mergeAndCount(int[] data, int leftIndex, int rightIndex)
    {
        int inversionCount = 0;
        if (leftIndex < rightIndex) {
            int midIndex = (leftIndex + rightIndex) / 2;
            StdOut.println("midIndex: " +midIndex +", leftIndex: " +leftIndex +", rightIndex: " +rightIndex);
            inversionCount += mergeAndCount(data, leftIndex, midIndex);
            inversionCount += mergeAndCount(data, midIndex + 1, rightIndex);
            inversionCount += sortAndCount(data, leftIndex, midIndex, rightIndex);
        }
        return inversionCount;
    }

    // Driver code
    public static void main(String[] args)
    {
//        int[] data = { 1, 20, 6, 4, 5 };
//        int[] data = { 2, 1, 3, 1, 2 };
//        int[] data = {1, 1, 1, 2, 2 };
        int[] data = {2, 4, 1, 3, 5 };
//        int[] data = {5, 2, 3, 8, 6, 1 };

        int inverse = mergeAndCount(data, 0, data.length - 1);
        StdOut.print(inverse +"\t");
        for ( int i = 0; i < data.length; i++ ) {
            StdOut.print(data[i] +", ");
        }

    }
}
