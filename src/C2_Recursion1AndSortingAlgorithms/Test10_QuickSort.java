package C2_Recursion1AndSortingAlgorithms;

import java.util.Random;

public class Test10_QuickSort {
    /**
     * [0, i) smaller than pivot
     * [i, j] to be checked
     * [j, arr.length) larger than pivot
     * Time Complexity:
     * 	Worst: O(n^2) Average: O(nlog n)
     * Space Complexity:
     * 	Worst: O(n)   Average: O(log n)
     **/

    static Random rand = new Random();
    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

    // nextInt(x) -> [0, x)
    // nextInt(right - left + 1) -> [0, right - left)
    // left + rand.nextInt(right - left + 1) -> [left, right)
        int pivotIndex = left + rand.nextInt(right - left + 1);

        swap(arr, right, pivotIndex);
        int i = left, j = right - 1;
        while (i <= j) {
            if (arr[i] <= arr[right]) {
                i++;
            } else {
                swap(arr, i, j--);
            }
        }
        swap(arr, i, right);
        quickSort(arr, left, i-1);
        quickSort(arr, i+1, right);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Test10_QuickSort test10_quickSort = new Test10_QuickSort();
        int[] input = new int[]{-1, 5, 2, 3,1, 2, 333};
        test10_quickSort.quickSort(input, 0, input.length - 1);
    }
}
