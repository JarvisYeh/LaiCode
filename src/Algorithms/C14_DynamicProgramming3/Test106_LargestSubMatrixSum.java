package Algorithms.C14_DynamicProgramming3;


public class Test106_LargestSubMatrixSum {
	// 给定一个矩阵，含有正数负数和0
	// 求出其中的和最大的子矩阵
	/**
	 * Solution 1: 1D prefix
	 * for row i in M
	 *  	M[i][j] represents sum from M[i][0] to M[i][j]
	 * Time Complexity: O(n^5)
	 * Space Complexity: O(n^2)
	 **/
	public int largestI(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] M = new int[rows][cols];

		// M[i][j] represents sum from M[i][0] to M[i][j]
		for (int i = 0; i < rows; i++) {
			M[i][0] = matrix[i][0];
			for (int j = 1; j < cols; j++) {
				M[i][j] = M[i][j - 1] + matrix[i][j];
			}
		}
		int max = Integer.MIN_VALUE;

		// left-up : (i, j)
		// right-bottom: (k, t)
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				for (int k = i; k < rows; k++) {
					for (int t = j; t < cols; t++) {
						int sum = 0;
						for (int r = i; r <=k; r++) {
							// the first in each row has to be add back
							sum += M[r][t] - M[r][j] + matrix[r][j];
						}
						max = Math.max(max, sum);
					}
				}
			}
		}

		return max;
	}

	/**
	 * Solution 2: 2D prefix
	 * M[i][j] represents sum in sub Matrix
	 *  (0, 0), (0, j)
	 *  (i, 0, (i, j)
	 *
	 * Time Complexity: O(n^4)
	 * Space Complexity: O(n^2)
	 **/
	public int largestII(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[][] M = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// base case
				if (i == 0 && j == 0) {
					M[i][j] = matrix[i][j];
				} else if (i == 0) {
					M[i][j] = M[i][j - 1] + matrix[i][j];
				} else if (j == 0) {
					M[i][j] = M[i - 1][j] + matrix[i][j];
				}
				// induction rule
				else {
					M[i][j] = M[i - 1][j] + M[i][j - 1] - M[i - 1][j - 1] + matrix[i][j];
				}
			}
		}

		int max = Integer.MIN_VALUE;
		// left-up : (i, j)
		// right-bottom: (k, t)
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				for (int k = i; k < rows; k++) {
					for (int t = j; t < cols; t++) {
						int sum = 0;
						if (i == 0 && j == 0) {
							sum = M[k][t];
						} else if (i == 0) {
							sum = M[k][t] - M[k][j - 1];
						} else if (j == 0) {
							sum = M[k][t] - M[i - 1][t];
						} else {
							sum = M[k][t] - M[i - 1][t] - M[k][j - 1]  + M[i - 1][j - 1];
						}
						max = Math.max(max, sum);
					}
				}
			}
		}
		return max;
	}

	/**
	 * Solution 3.1:
	 * M[i][j] represents
	 *  0～i行的第j列元素之和
	 *  (0, j)
	 *  (i, j)
	 * 计算sum of subMatrix时
	 *  	for a 为matrix上边界
	 *  		for b 为matrix下边界
	 *  			int[] sum = new int[cols];
	 *  				sum[s]表示当前的a, b边界下，第s列的所有值之和
	 *  			1. sum[j] = M[b][j] - M[a][j]为a~b行第j列的所有元素之和 - O(n)
	 *  				将a-b上下边界内的元素拍扁，使得问题变为一维问题
	 *  			2. 求sum array中的最大sub array - O(n)
	 *  			内部的两个O(n)为求和，即为O(2n) = O(n)
	 *
	 * Time Complexity: O(n^3)
	 * Space Complexity: O(n^2)
	 **/
	public int largestIII(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[][] M = new int[rows][cols];

		// M[i][j] 代表0～i行的第j列元素之和
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// base case
				if (i == 0) {
					M[i][j] = matrix[i][j];
				}
				// induction rule
				else {
					M[i][j] = M[i - 1][j] + matrix[i][j];
				}
			}
		}

		int max = Integer.MIN_VALUE;

		for (int a = 0; a < rows; a++) {
			for (int b = a; b < rows; b++) {
				// step 1: construct s[]
				int[] s = new int[cols];
				for (int j = 0; j < cols; j++) {
					if (a == 0) {
						s[j] = M[b][j];
					} else {
						s[j] = M[b][j] - M[a - 1][j];
					}
				}

				// get the largest sum for rows from a to b
				int largestSum = s[0];
				int currSum = s[0];
				// step 2: obtain the largest sum of sub array in s[]
				for (int j = 1; j < cols; j++) {
					if (currSum < 0) {
						currSum = s[j];
					} else {
						currSum += s[j];
					}
					largestSum = Math.max(largestSum, currSum);
				}

				// update max
				max = Math.max(max, largestSum);
			}
		}

		return max;
	}

	/**
	 * Solution 3.2:
	 * flatten[j] 存 matrix[i1][j] ~ matrix[i2][j]之和
	 * 每次i2+1, 所有的flatten[j]更新
	 * 每次i1变化，生成新的flatten
	 * Time Complexity: O(n^3)
	 * Space Complexity: O(n)
	 **/
	public int largest(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;

		int max = Integer.MIN_VALUE;

		for (int i1 = 0; i1 < m; i1++) {				// top bar
			// flatten[j] stores sum_{i = i1->i2}{mat[i][j]}
			// initialize as all 0s
			int[] flatten = new int[n];

			for (int i2 = i1; i2 < m; i2++) {			// bottom bar
				// calculate flatten
				for (int j = 0; j < n; j++) {
					flatten[j] += matrix[i2][j];
				}

				// find largest sum of subarray problem
				int currSum = 0;
				for (int j = 0; j < n; j++) {
					if (currSum < 0) {
						currSum = flatten[j];
					} else {
						currSum += flatten[j];
					}
					max = Math.max(currSum, max);
				}
			}
		}
		return max;
	}

}
