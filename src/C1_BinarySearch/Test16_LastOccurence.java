package C1_BinarySearch;

public class Test16_LastOccurence {
    public int lastOccur(int[] arr, int target) {
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


    public static void main(String[] args) {
        Test16_LastOccurence test = new Test16_LastOccurence();
        System.out.println(test.lastOccur(new int[]{5,5,5,5,5,1}, 5));
    }
}
