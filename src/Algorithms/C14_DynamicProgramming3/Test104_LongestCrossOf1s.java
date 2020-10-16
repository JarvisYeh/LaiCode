package Algorithms.C14_DynamicProgramming3;

public class Test104_LongestCrossOf1s {
	public int largest(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;

		int[][] leftUp = getLeftUp(matrix);
		int[][] rightDown = getRightDown(matrix);

		return merge(leftUp, rightDown);
	}

	/**
	 * Merge one and two to one
	 * reserve the min of (one[i, j], two[i, j])
	 * @param one
	 * @param two
	 * @return the maximum value of the merged matrix
	 */
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

	/**
	 * Each result matrix value int (i, j) represents
	 * (i, j) as the corner, extend arms to left and up, the maximum arm length it could get
	 * 	where two arms share same length
	 * @param matrix
	 * @return
	 */
	private int[][] getLeftUp(int[][] matrix) {
		int[][] left = new int[matrix.length][matrix[0].length];
		int[][] up = new int[matrix.length][matrix[0].length];

		for (int i = 0; i < left.length; i++) {
			for (int j = 0; j < left[0].length; j++) {
				if (matrix[i][j] == 1){
					// update (i, j) based on previous value
					left[i][j] = getNumber(left, i, j-1) + 1;
					up[i][j] = getNumber(up, i-1, j) + 1;
				}
			}
		}
		merge(left, up);
		return left;
	}

	/**
	 * Each result matrix value int (i, j) represents
	 * (i, j) as the corner, extend arms to right and bottom, the maximum arm length it could get
	 * 	where two arms share same length
	 * @param matrix
	 * @return
	 */
	private int[][] getRightDown(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] right = new int[rows][cols];
		int[][] down = new int[rows][cols];

		for (int i = rows - 1; i >= 0; i--) {
			for (int j = cols - 1; j >= 0; j--) {
				// if matrix[i][j] is 0, put 0 in both left[i][j] and up[i][j]
				// therefore, no action needed for that case since matrices initialize with all 0s
				if (matrix[i][j] == 1){
					// update (i, j) based on previous value
					right[i][j] = getNumber(right, i, j + 1) + 1;
					down[i][j] = getNumber(down, i+1, j) + 1;
				}
			}
		}
		merge(right, down);
		return right;
	}

	// if previous i, j is out of boundary, meaning it's the base case
	// return 0
	private int getNumber(int[][] mat, int i, int j) {
		if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length) {
			return 0;
		}
		return mat[i][j];
	}

	public static void main(String[] args) {
		Test104_LongestCrossOf1s test = new Test104_LongestCrossOf1s();
		test.largest(new int[][]{{0,1,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}});
	}
}
