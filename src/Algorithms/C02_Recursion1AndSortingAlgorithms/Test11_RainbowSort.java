package Algorithms.C02_Recursion1AndSortingAlgorithms;

public class Test11_RainbowSort {
    /**
     * [0, i) 				-1
     * [i, j) 				0
     * [j, k] 				to be checked
     * (k, arr.length - 1] 	1
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     **/
    public int[] rainbowSort(int[] arr) {
        int i = 0, j = 0, k = arr.length - 1;
        if (arr[j] == -1) {
            swap(arr, i++, j++);
        } else if (arr[j] == 0) {
            j++;
        } else {
            swap(arr, j, k--);
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
