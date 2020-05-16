package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class MaxSumCircularSubarray {

    public static int maxSubarraySumCircular(int[] A) {
        int n = A.length;
        int maxKadane = kadane(A);
        int max = 0;
        for (int i = 0; i < n; i++) {
            max += A[i];
            A[i] = -A[i];
        }
        int invertedKadane = kadane(A);
        max = max + invertedKadane;
        return (max <= 0) ? maxKadane : ((max > maxKadane) ? max : maxKadane);
    }

    private static int kadane (int a[]) {
        int n = a.length;
        int max = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + a[i];
            if (max < sum) max = sum;
            if (sum < 0) sum = 0;
        }
        return max;
    }
    public static void main(String[] args) {
//        int[] A = new int[]{1,-2,3,-2}; // 3
//        int[] A = new int[]{5,-3,5}; // 10
//        int[] A = new int[]{3,-1,2,-1}; // 4
        int[] A = new int[]{3,-2,2,-3}; // 3
        System.out.println(maxSubarraySumCircular(A));
    }
}
