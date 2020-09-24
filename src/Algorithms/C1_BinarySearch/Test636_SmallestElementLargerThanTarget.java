package Algorithms.C1_BinarySearch;

public class Test636_SmallestElementLargerThanTarget {
    /**
     * Returns the index of the element that is smallest among the elements that’s larger than target
     * if no elements larger than target, returns -1
     **/
    public int smallestLarger(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0, right = arr.length - 1;
        // while > 2 elements in search space
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // post processing
        if (arr[left] > target) {
            return left;
        } else {
            return arr[right] > target ? right : -1;
        }
    }

}
