package com.pramodrao.dsa;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pramod.rao
 */
public class IntervalListIntersections {
    public static int[][] intervalIntersection(int[][] A, int[][] B) {

        if (null == A || null == B || A.length == 0 || B.length == 0) return new int[][]{};

        int a = 0, b = 0;
        List<int[]> interval = new ArrayList<>();
        while(a < A.length && b < B.length) {
            int aStart = A[a][0];
            int aEnd = A[a][1];

            int bStart = B[b][0];
            int bEnd = B[b][1];

            if (aStart >= bStart && aStart <= bEnd ) {
                if (aEnd <= bEnd) {
                    interval.add(new int[]{aStart, aEnd});
                    a++;
                } else if ( aEnd >= bEnd) {
                    interval.add(new int[]{aStart, bEnd});
                    b++;
                }
            } else if (bStart >= aStart && bStart <= aEnd ) {
                if (bEnd <= aEnd) {
                    interval.add(new int[]{bStart, bEnd});
                    b++;
                } else if ( bEnd >= aEnd) {
                    interval.add(new int[]{bStart, aEnd});
                    a++;
                }
            } else {
                if (aEnd > bEnd) b++;
                else a++;
            }
        }
        return interval.toArray(new int[interval.size()][]);
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{{0,2},{5,10},{13,23},{24,25}};
        int[][] B = new int[][]{{1,5},{8,12},{15,24},{25,26}};
        int[][] C = intervalIntersection(A, B);

        System.out.print("{");
        for (int i = 0; i < C.length; i++) {
            System.out.print("{");
            for (int j = 0; j < C[i].length; j++) {
                System.out.print(C[i][j] +",");
            }
            System.out.print("},");
        }
        System.out.print("}");
    }
}
