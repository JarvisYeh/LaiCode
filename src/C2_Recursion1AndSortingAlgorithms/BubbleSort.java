package C2_Recursion1AndSortingAlgorithms;

import java.util.Arrays;

public class BubbleSort {
    public void bubbleSort(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            for (int j=0; j<arr.length - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = {-1, 2, 3123, 44, 1, 31, -23, 24};
        bubbleSort.bubbleSort(input);
        System.out.println(Arrays.toString(input));
    }
}
