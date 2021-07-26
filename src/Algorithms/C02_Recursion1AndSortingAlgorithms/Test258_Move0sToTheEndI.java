package Algorithms.C02_Recursion1AndSortingAlgorithms;

public class Test258_Move0sToTheEndI {
    /**
     * [0, i) not 0
     * [i, j] to be checked
     * [k, arr.length) 0
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     **/
    public int[] arrayShuffle(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            if (arr[i] == 0) {
                arr[i] = arr[j];
                arr[j--] = 0;
            } else {
                i++;
            }
        }
        return arr;
    }

}
