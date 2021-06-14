package Algorithms.C13_DynamicProgramming2;

public class Test101_LargestSquareOf1s {
	/**
	 * M[i][j]代表以i, j为右下角的全1正方形最大边长
	 * Time Complexity: O(n^2)
	 * Space Complexity: O(n^2)
	 **/
	public int largest(int[][] matrix) {
		int[][] M = new int[matrix.length][matrix[0].length];
		int max = 0;

		// induction rules
		for (int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				// base cases
				// 左和上边界的点只和自己的值相关
				if (i == 0 || j == 0) {
					M[i][j] = matrix[i][j];
				}
				// 如果该点为0，则以该点为右下角的正方形边长为0
				else if (matrix[i][j] == 0) {
					M[i][j] = 0;
				}
				// 否则，看上，左上，左的临近位置的最小值+1
				else {
					M[i][j] = 1 + Math.min(M[i - 1][j - 1], Math.min(M[i][j - 1], M[i - 1][j]));
				}
				max = Math.max(max, M[i][j]);
			}
		}
		return max;
	}


	public static void main(String[] args) {
		Test101_LargestSquareOf1s test = new Test101_LargestSquareOf1s();
		test.largest(new int[][]{{1,1,1,1},{1,1,1,0},{1,1,1,1},{1,1,0,1}});
	}

}
