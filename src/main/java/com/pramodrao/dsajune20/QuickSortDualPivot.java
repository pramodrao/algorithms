package com.pramodrao.dsajune20;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author pramod.rao
 */
public class QuickSortDualPivot {

    public static void sort(int nums[]) {

        if (null == nums || nums.length < 2) return;
        sort(nums, 0, nums.length-1);
    }

    public static void sort(int nums[], int lo, int hi) {

        if (hi <= lo) return;

        int leftPivot = nums[lo];
        int rightPivot = nums[hi];

        if(leftPivot > rightPivot) {
            swap(nums, lo, hi);
            leftPivot = nums[lo];
            rightPivot = nums[hi];
        }

        int cursor = lo + 1;
        int leftPartition = lo + 1;
        int rightPartition = hi - 1;

        while (cursor <= rightPartition) {
            if(nums[cursor] < leftPivot) {
                swap(nums, leftPartition, cursor);
                leftPartition++;
                cursor++;
            } else if (nums[cursor] > rightPivot) {
                swap(nums, rightPartition, cursor);
                rightPartition--;
            } else {
                cursor++;
            }
        }

        swap(nums, lo, --leftPartition);
        swap(nums, hi, ++rightPartition);

        sort(nums, lo, leftPartition-1);
        sort(nums, leftPartition+1, rightPartition-1);
        sort(nums, rightPartition+1, hi);

    }
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
//        int[] data = { 1, 20, 6, 4, 5 };
//        int[] data = { 2, 1, 3, 1, 2 };
//        int[] data = {1, 1, 1, 2, 2 };
//        int[] data = {2, 4, 1, 3, 5 };
        int[] data = {5, 2, 3, 8, 6, 1 };

        sort(data);
        StdOut.print("Sorted: ");
        for ( int i = 0; i < data.length; i++ ) {
            StdOut.print(data[i] +", ");
        }
    }
}
