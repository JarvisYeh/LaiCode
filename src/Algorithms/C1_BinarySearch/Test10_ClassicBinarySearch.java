package Algorithms.C1_BinarySearch;

public class Test10_ClassicBinarySearch {
    /**
     * Returns the index of the target number
     * If the not found, return -1
     * Search in range [left, right]
     * To be search amount: right - left + 1, continue searching when
     * the search amount is larger or equal to 1.
     * right - left + 1 >= 1
     * left <= right
     **/
    public int binarySearch(int[] arr, int target) {
        // corner case
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {			//left = 2, right = 2
            // avoid overflow problem
            int mid = left + (right - left)/2; 	//mid = 2
            if (arr[mid] > target) {
                right = mid - 1; 			//right = 3-1 =2
            } else if (arr[mid] < target) {
                left = mid + 1; 				//left = 2
            } else { // target found
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        Test10_ClassicBinarySearch test = new Test10_ClassicBinarySearch();
        int res = test.binarySearch(new int[] {3,4,5,6,6,9,16}, 5);
        System.out.println(res);
    }

}
