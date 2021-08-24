package Algorithms.C21_CrossTraining3;

public class Test102_LargestRectangleOf1s {
	// Method 1:
	// use the largest consecutive 1s as sub problem
	// generate look up and loop left matrix
	// iterate for each bottom right corner	O(mn)
	// 	iterate for each possible height	O(m) at most
	//		get possible width, update max area
	// TC: O(m^2n)
	// SC: O(mn)
	public int maximalRectangleI(char[][] matrix) {
		if (matrix == null || matrix.length == 0) return 0;
		int m = matrix.length, n = matrix[0].length;
		int[][] left = new int[m][n];
		int[][] up = new int[m][n];
		// O(mn)
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == '0') {
					left[i][j] = 0;
					up[i][j] = 0;
					continue;
				}

				if (i == 0 && j == 0) {
					left[i][j] = 1;
					up[i][j] = 1;
				} else if (i == 0) {    // first row, base case for up
					up[i][j] = 1;
					left[i][j] = left[i][j] + 1;
				} else if (j == 0) {    // first column, base case for left
					up[i][j] =  1 + up[i - 1][j];
					left[i][j] = 1;
				} else {
					up[i][j] = up[i - 1][j] + 1;
					left[i][j] = left[i][j - 1] + 1;
				}
			}
		}


		int max = 0;
		// iterate bottom right corner of rectangle
		for (int i = m - 1; i >= 0; i--) {       // O(m)
			for (int j = n - 1; j >= 0; j--) {   // O(n)
				int maxRows = up[i][j];
				int width = left[i][j];
				for (int r = 0; r < maxRows; r++) { // at most O(m)
					width = Math.min(width, left[i - r][j]);
					max = Math.max(max, (r + 1) * width);
				}
			}
		}
		return max;

	}

	// Method 2:
	// use max area in histogram as sub problem
	// TC: O(m4n)
	// SC: O(n)
	public int maximalRectangleII(char[][] matrix) {
		if (matrix == null || matrix.length == 0) return 0;
		int[] input = new int[matrix[0].length];
		int max = 0;
		// for each bottom rows
		for (int i = 0; i < matrix.length; i++) {
			// for each j update input[j]
			for (int j = 0; j < matrix[0].length; j++) { // O(n)
				if (matrix[i][j] == '1') {
					input[j] += 1;
				} else {
					input[j] = 0;
				}
			}
			max = Math.max(max, getMaxArea(input));		// O(3n)
		}
		return max;
	}

	// TC: O(3n)
	// SC: O(n)
	private int getMaxArea(int[] arr) {
		int n = arr.length;
		int[] lessLeftIdxs = new int[n], lessRightIdxs = new int[n];

		// generate lessLeftIdxs
		for (int i = 0; i < n; i++) {
			int prev = i - 1;
			while (prev >= 0 && arr[prev] >= arr[i]) prev = lessLeftIdxs[prev];
			lessLeftIdxs[i] = prev;
		}
		// generate lessRightIdxs
		for (int i = n - 1; i >= 0; i--) {
			int prev = i + 1;
			while (prev < n && arr[prev] >= arr[i]) prev = lessRightIdxs[prev];
			lessRightIdxs[i] = prev;
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			int area = (lessRightIdxs[i] - lessLeftIdxs[i] - 1)*arr[i];
			max = Math.max(max, area);
		}
		return max;
	}


	public static void main(String[] args) {
		Test102_LargestRectangleOf1s t = new Test102_LargestRectangleOf1s();
		System.out.println(t.maximalRectangleII(new char[][]{
				{'1', '1'}
		}));
//		t.maximalRectangle(new char[][]
//				{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}
//		);
	}

}
