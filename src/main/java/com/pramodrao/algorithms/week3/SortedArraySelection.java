package com.pramodrao.algorithms.week3;

/**
 * @author pramod.rao
 */
public class SortedArraySelection {

    public static Comparable getKey(Comparable[] a1, Comparable[] a2, int rank) {
        Comparable[] merged = mergeAndRank(a1, a2);
        return merged[rank];
    }

    private static Comparable[] mergeAndRank(Comparable[] a1, Comparable[] a2)
    {
        int a1Index = 0;
        int a2Index = 0;
        Comparable[] merged = new Comparable[a1.length + a2.length];
        for ( int i = 0; i <= a1.length; i++ ) {

            if ( a1Index >= a1.length ) {
                System.arraycopy(a2, a2Index, merged, i, a2.length - a2Index);
                break;
            }

            if ( a2Index >= a2.length ) {
                System.arraycopy(a1, a1Index, merged, i, a2.length - a2Index);
                break;
            }

            if ( a1[a1Index].compareTo(a2[a2Index]) > 0 ) {
                merged[i] = a2[a2Index];
                a2Index++;
            } else {
                merged[i] = a1[a1Index];
                a1Index++;
            }
        }
        return merged;
    }

}
