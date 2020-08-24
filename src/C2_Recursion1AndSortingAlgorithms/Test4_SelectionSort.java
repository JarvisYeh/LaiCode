package C2_Recursion1AndSortingAlgorithms;

import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Test4_SelectionSort {
    /**
     * Sort the input array, returns nothing
     * Time Complexity: O(n^2) = O(n+(n-1)+(n-1)+...+(1))
     * Space Complexity: O(1)
     **/
    public void selectionSort(int[] arr) {
        for (int i=0; i<arr.length; i++) {
            int minIndex = i;
            for (int j=i+1; j<arr.length; j++) {
                if (arr[j] < arr[i]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Selection sort implementation with 3 stacks
     * @param in
     * @return
     */
    public Deque<Integer> selectionSortWith3Stacks(Deque<Integer> in) {
        // corner case
        if (in == null || in.size() == 0) {
            return new LinkedList<>();
        }

        Deque<Integer> res = new LinkedList<>();
        Deque<Integer> buf = new LinkedList<>();

        while (in.size() != 0) {
            int min = in.peekFirst();
            while (in.size() != 0) {
                int temp = in.pollFirst();
                if (temp < min) {
                    min = temp;
                }
                buf.offerFirst(temp);
            }

            while (buf.size() != 0) {
                int temp = buf.pollFirst();
                if (temp == min) {
                    res.offerFirst(temp);
                } else {
                    in.offerFirst(temp);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Test4_SelectionSort test4_selectionSort = new Test4_SelectionSort();
        // Test for array
        int[] arr = {-1, 5, 3, 1, 100};
        test4_selectionSort.selectionSort(arr);
        System.out.println(Arrays.toString(arr));

        // Test for Stack
        Deque<Integer> in = new LinkedList<>();
        for (int num: arr) {
            in.offerFirst(num);
        }
        Deque<Integer> res = test4_selectionSort.selectionSortWith3Stacks(in);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
