package C1_BinarySearch;

import java.util.Arrays;

public class Test267_SearchInSortedMatrix {
    /**
     * Returns the target number location array {i, j} in matrix
     * Assume the matrix is sorted, square matrix
     * If target not found, returns {-1, -1}
     * same things as in binary search
     * range [left, right]
     * left = 0
     * right = mat.length * mat[0].length - 1
     **/
    public int[] search(int[][] mat, int target) {
        // corner case
        if (mat == null || mat.length == 0) {
            return new int[]{-1, -1};
        }

        int left = 0, right = mat.length * mat[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            int i = mid / mat[0].length;
            int j = mid % mat[0].length;
            if (mat[i][j] > target) {
                right = mid - 1;
            } else if (mat[i][j] < target) {
                left = mid + 1;
            } else {
                return new int[]{i, j};
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        Test267_SearchInSortedMatrix test = new Test267_SearchInSortedMatrix();
        int[][] arr = new int[][] {{1, 2, 3}, {4, 5, 7}, {8, 9, 10}};
        System.out.println(Arrays.toString(test.search(arr, 7)));
    }
}
