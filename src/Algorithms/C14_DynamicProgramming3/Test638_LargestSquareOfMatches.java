package Algorithms.C14_DynamicProgramming3;

public class Test638_LargestSquareOfMatches {
	/**
	 * 输入矩阵中的值代表右边与下边是否有火柴棒
	 * 0: 右下都没有
	 * 1: 只有右边有
	 * 2: 只有下边右
	 * 3: 右下都有
	 * 求火柴能围成的最大正方形边长
	 *
	 * 先使用DP
	 * 	求出每个index向右的最长边长
	 * 	求出每个index向下的最长边长
	 **/
	public int largestSquareOfMatches(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[][] down = new int[rows][cols];
		int[][] right = new int[rows][cols];

		// construct down, right matrix
		for (int i = rows - 1; i >= 0; i--) {
			for (int j = cols - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					right[i][j] = right[i][j + 1] + 1;
				}
				if (matrix[i][j] == 2) {
					down[i][j] = down[i + 1][j] + 1;
				}
				if (matrix[i][j] == 3) {
					right[i][j] = right[i][j + 1] + 1;
					down[i][j] = down[i + 1][j] + 1;
				}
			}
		}

		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int maxSide = Math.min(right[i][j], down[i][j]);
				// prune
				if (maxSide <= max) {
					continue;
				}

				for (int s = maxSide; s >= 1; s++) {
					// no need to check over-boundary
					// since min(right, down) assures that it will not cross the boundary
					if (down[i][j + s] >= s && right[i + s][j] >= s) {
						max = Math.max(max, s);
						break;
					}
				}
			}
		}
		return max;
	}

}
