package Algorithms.C01_BinarySearch;

import java.util.*;

public class Test19_kClosestInSortedArray {
    /**
     * Returns the k elements which is closest to the target
     * The returned array is in ascending order according how close to the target
     * Assume k > 0, k < arr.length
     **/
    public int[] kClosestI(int[] arr, int target, int k) {
        // corner case
        if (arr == null || arr.length == 0) {
            return null;
        }

        // first find two numbers {left, target, right}, which is closest to the target
        int left = 0, right = arr.length;
        while (right - left > 1) {
            int mid = left + (right - left)/2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        // post processing
        int[] res = new int[k];
        for (int i=0; i<k; i++) {
            if (right > arr.length - 1) {
                res[i] = arr[left--];
            } else if (left < 0) {
                res[i] = arr[right++];
            } else {
                res[i] = Math.abs(arr[right] - target) < Math.abs(arr[left] - target) ? arr[right++] : arr[left--];
            }
        }

//        post processing answer 2:
//        int[] res = new int[k];
//        for (int i=0; i<k; i++) {
//            if (left < 0 || (right < arr.length && Math.abs(arr[right]-target) < Math.abs(arr[left]-target))) {
//                res[i] = arr[right++];
//            } else {
//                res[i] = arr[left--];
//            }
//        }

        return res;
    }

    // method 2: binary search + search in two sorted array
    // TC: O(log n + log k)
    // SC: O(1)
    public int[] kClosestII(int[] arr, int target, int k) {
        if (k == 0) {return new int[0];}
        int l = 0, r = arr.length - 1;
        // when search range is larger than 2
        while (l < r - 1) {
            int m = l + ((r - l) >> 1);
            if (arr[m] > target) {
                r = m;
            } else {
                l = m;
            }
        }

        // now r - l = 1
        // consider l -> 0 as an array, r -> len - 1 as another array
        // the distance to target in both two arrays are ascending
        int[] res = new int[k];
        int i = 0;
        while (true) {
            // 3 base cases
            if (l < 0) {
                for (int j = 0; j < k; j++) res[i++] = arr[r + j];
                break;
            }
            if (r >= arr.length) {
                for (int j = 0; j < k; j++) res[i++] = arr[l - j];
                break;
            }
            if (k == 1) {
                res[i] = Math.abs(arr[l] - target) <= Math.abs(arr[r] - target) ? arr[l] : arr[r];
                break;
            }

            // initialize lValue and rValue
            int lValue =  l - k/2 + 1 >= 0 ? Math.abs(target - arr[l - k/2 + 1]) : Integer.MAX_VALUE;
            int rValue = r + k/2 - 1 < arr.length ? Math.abs(target - arr[r + k/2 - 1]) : Integer.MAX_VALUE;
            // compare b[r + k/2 - 1] with a[l + k/2 - 1], which in our case is a[l - k/2 + 1]
            if (lValue <= rValue) {
                for (int j = 0; j < k/2; j++) res[i++] = arr[l - j];
                l = l - k/2;
            } else {
                for (int j = 0; j < k/2; j++) res[i++] = arr[r + j];
                r = r + k/2;
            }
            k = k - k/2;
        }

        // laicode上要求返回数组顺序为离target的距离升序
        List<Integer> tmp = new ArrayList<>();
        for (int num : res) {
            tmp.add(num);
        }
        tmp.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(Math.abs(o1 - target), Math.abs(o2 - target));
            }
        });
        for (i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }

    // method 3:
    // binary search + sliding window
    // TC: O(log n)
    // SC: O(1)
    public int[] kClosestIII(int[] arr, int target, int k) {
        int lo = 0, hi = arr.length - k;
        // search for left boundary of size = k + 1 window
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            // target to the left of window
            if (target < arr[mid]) {
                // left boundary upper bound < mid
                hi = mid - 1;
            }
            // target in window, closer to arr[mid]
            else if (target - arr[mid] <= arr[mid + k] - target) {
                hi = mid;
            }
            // target in window, closer to arr[mid + k]
            else if (target - arr[mid] > arr[mid + k] - target) {
                lo = mid + 1;
            }
            // target to the right of window
            else {
                lo = mid + 1;
            }
        }
        int[] res = Arrays.copyOfRange(arr, lo, lo + k);

        // laicode上要求返回数组顺序为离target的距离升序
        List<Integer> tmp = new ArrayList<>();
        for (int num : res) {
            tmp.add(num);
        }
        tmp.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(Math.abs(o1 - target), Math.abs(o2 - target));
            }
        });
        for (int i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Test19_kClosestInSortedArray t = new Test19_kClosestInSortedArray();
        t.kClosestII(new int[]{1,5}, 10, 2);
    }

}
