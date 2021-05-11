package Algorithms.C1_BinarySearch;

public class Test15_FirstOccurence {
    /**
     * Returns the index where the target first occurs
     * If target not found, return -1
     **/
    public int firstOccurI(int[] arr, int target) {
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
                right = mid;
            }
        }

        // post processing
        if (arr[left] == target) {
            return left;
        } else {
            return arr[right] == target ? right : -1;
        }
    }

    // method without post-processing
    public int firstOccurII(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid - 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else { // arr[mid] == target
                // if this is the first element in array, and this element is target
                // or if arr[mid - 1] != target, but arr[mid] = target
                // return this mid as result
                if (mid == 0 || arr[mid - 1] != target) {
                    return mid;
                }
                // other case, mid is not the result, shrink the range dispose mid
                else {
                    right = mid - 1;
                }

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Test15_FirstOccurence test = new Test15_FirstOccurence();
        System.out.println(test.firstOccurII(new int[]{5,5,5,5,5,1}, 5));
    }
}
