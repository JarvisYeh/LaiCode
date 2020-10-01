package Algorithms.C14_DynamicProgramming3;

public class Test105_LargestXOf1s {
	public int largest(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int[][] left = getLeft(matrix);
		int[][] right = getRight(matrix);
		return merge(left, right);
	}

	private int merge(int[][] one, int[][] two) {
		int max = 0;
		for (int i = 0; i < one.length; i++) {
			for (int j = 0; j < one[0].length; j++) {
				one[i][j] = Math.min(one[i][j], two[i][j]);
				max = Math.max(one[i][j], max);
			}
		}
		return max;
	}


	private int[][] getLeft(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[][] leftUp = new int[rows][cols];
		int[][] leftDown = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 1) {
					leftUp[i][j] = getNumber(leftUp, i - 1, j - 1) + 1;
				}
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = cols - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					leftDown[i][j] = getNumber(leftDown, i - 1, j + 1) + 1;
				}
			}
		}

		merge(leftUp, leftDown);
		return leftUp;
	}

	private int[][] getRight(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[][] rightUp = new int[rows][cols];
		int[][] rightDown = new int[rows][cols];

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 1) {
					rightUp[i][j] = getNumber(rightUp, i + 1, j - 1) + 1;

				}
			}
		}

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = cols - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					rightDown[i][j] = getNumber(rightDown, i + 1, j + 1) + 1;
				}
			}
		}

		merge(rightUp, rightDown);
		return rightUp;
	}

	private int getNumber(int[][] mat, int i, int j) {
		if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length) {
			return 0;
		}
		return mat[i][j];
	}

}
