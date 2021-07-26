package Algorithms.C01_BinarySearch;

public class Test16_LastOccurence {
    public int lastOccurI(int[] arr, int target) {
        // corner case
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;

        while (right - left > 1) {
            int mid = left + (right-left)/2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                left = mid;
            }
        }

        // post processing
        if (arr[right] == target) {
            return right;
        } else {
            return arr[left] == target ? left : -1;
        }
    }

    // method without post-processing
    public int lastOccurII(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 2);
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else { // arr[mid] = target
                if (mid == arr.length - 1 || arr[mid + 1] != target) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Test16_LastOccurence test = new Test16_LastOccurence();
        System.out.println(test.lastOccurII(new int[]{5,5,5,5,5,1}, 5));
    }
}
