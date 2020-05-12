package com.pramodrao.dsa;

/**
 * @author pramod.rao
 */
public class SingleElementInSortedArray {
    public static int singleNonDuplicate(int[] nums) {
        if (null == nums || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];

        int unique = find(nums, 0, nums.length-1);
        return unique;
    }

    private static int find(int nums[], int start, int end) {
        if(start > end) return -1;
        if(start == end) return nums[start];

        int mid = (start + end)/2;

        if(mid % 2 == 0) {
            if(nums[mid] == nums[mid+1]) return find(nums, mid+2, end);
            else return find(nums, start, mid);
        } else if(mid % 2 == 1) {
            if(nums[mid] == nums[mid-1]) return find(nums, mid+1, end);
            else return find(nums, start, mid-1);
        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{1,1,2,3,3,4,4,8,8};
        int[] nums = new int[]{3,3,7,7,10,11,11};
        System.out.println(singleNonDuplicate(nums));
    }
}
