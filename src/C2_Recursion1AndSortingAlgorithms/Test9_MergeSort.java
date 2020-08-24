package C2_Recursion1AndSortingAlgorithms;

import java.util.Arrays;

public class Test9_MergeSort {
    /**
     * Time Complexity: O(nlogn)
     * Space Complexity: O(n)
     **/
    public int[] mergeSort(int[] arr, int left, int right) {
        // base case
        if (left == right) {
            return new int[]{arr[left]};
        }

        // recursive rule
        // split to child, return sorted to parent
        int mid = left + (right - left) / 2;
        int[] leftRes = mergeSort(arr, left, mid);
        int[] rightRes = mergeSort(arr, mid + 1, right);

        return merge(leftRes, rightRes);
    }

    private int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0;
        int resIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                res[resIndex++] = left[leftIndex++];
            } else {
                res[resIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            res[resIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            res[resIndex++] = right[rightIndex++];
        }
        return res;
    }




    /**
     * Merge Sort with helper array
     *
     * @param arr
     */
    public int[] mergeSortWithHelper(int[] arr) {
        int[] helper = new int[arr.length];
        mergeSortWithHelper(arr, helper, 0, arr.length - 1);
        return arr;
    }

    public void mergeSortWithHelper(int[] arr, int[] helper, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;

        mergeSortWithHelper(arr, helper, left, mid);
        mergeSortWithHelper(arr, helper, mid + 1, right);

        mergeWithHelper(arr, helper, left, mid, right);
    }

    private void mergeWithHelper(int[] arr, int[] helper, int left, int mid, int right) {
        // copy from arr to helper
        for (int i = left; i <= right; i++) {
            helper[i] = arr[i];
        }
        int leftIndex = left, rightIndex = mid + 1, index = left;

        while (leftIndex <= mid && rightIndex <= right) {
            if (helper[leftIndex] < helper[rightIndex]) {
                arr[index++] = helper[leftIndex++];
            } else {
                arr[index++] = helper[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            arr[index++] = helper[leftIndex++];
        }
    }



    public static void main(String[] args) {
        Test9_MergeSort test9_mergeSort = new Test9_MergeSort();
        int[] input = {-1, 2, 4, 10, 23, 1, -3};
        System.out.println(Arrays.toString(test9_mergeSort.mergeSort(input, 0, input.length - 1)));

        System.out.println(Arrays.toString(input));
        test9_mergeSort.mergeSortWithHelper(input);
        System.out.println(Arrays.toString(input));
    }
}
