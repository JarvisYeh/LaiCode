package C1_BinarySearch;

public class Test19_kClosestInSortedArray {
    /**
     * Returns the k elements which is closest to the target
     * The returned array is in ascending order according how close to the target
     * Assume k > 0, k < arr.length
     **/
    public int[] kClosest(int[] arr, int target, int k) {
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

}
