package com.pramodrao.algorithms.week1;

/**
 * @author pramod.rao
 */
public class BitonicArraySearch {

    private int ascendingBinarySearch(int arr[], int low, int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private int descendingBinarySearch(int arr[], int low,
                                       int high, int key) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] < key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private int findBitonicPoint(int arr[], int n, int l, int r) {
        int mid;

        mid = (r + l) / 2;
        if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
            return mid;
        } else {
            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                findBitonicPoint(arr, n, mid, r);
            } else {
                if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                    findBitonicPoint(arr, n, l, mid);
                }
            }
        }
        return mid;
    }

    public int searchBitonic(int arr[], int n, int key, int index) {
        if (key > arr[index]) {
            return -1;
        } else if (key == arr[index]) {
            return index;
        } else {
            int temp = ascendingBinarySearch(arr, 0, index - 1, key);
            if (temp != -1) {
                return temp;
            }

            return descendingBinarySearch(arr, index + 1, n - 1, key);
        }
    }

    public static void main(String args[]) {
        int arr[] = {-8, 1, 2, 3, 4, 5, -2, -3};
        int key = 1;
        int n, l, r;
        n = arr.length;
        l = 0;
        r = n - 1;
        int index;

        BitonicArraySearch bas = new BitonicArraySearch();
        index = bas.findBitonicPoint(arr, n, l, r);

        int x = bas.searchBitonic(arr, n, key, index);

        if (x == -1) {
            System.out.println("Element Not Found");
        } else {
            System.out.println("Element Found at index " + x);
        }

    }
}
