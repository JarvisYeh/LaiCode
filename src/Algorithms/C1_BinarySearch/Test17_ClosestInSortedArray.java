package Algorithms.C1_BinarySearch;

public class Test17_ClosestInSortedArray {
    /**
     * Returns the index of the number that is closest to the target
     * If the target found, return the target index
     * Assume no duplication elements
     * If two elements are same close to target, returns index of smaller one
     **/
    public int closest(int[] arr, int target) {
        // corner case
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0, right = arr.length - 1;
        // if right - left + 1 > 2, do search space pruning
        // which is right - left > 1
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else if (arr[mid] < target) {
                left = mid;
            } else {
                return mid;
            }
        }

        // post processing
        return Math.abs(arr[left] - target) < Math.abs(arr[right] - target) ? left : right;
    }

    public static void main(String[] args) {
        Test17_ClosestInSortedArray test = new Test17_ClosestInSortedArray();
        int res = test.closest(new int[]{3, 4, 5, 6, 12, 16}, 6);
        System.out.println(res);
    }
}
