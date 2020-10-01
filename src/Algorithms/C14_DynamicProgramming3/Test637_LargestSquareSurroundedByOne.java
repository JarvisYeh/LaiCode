package Algorithms.C14_DynamicProgramming3;

import java.util.Arrays;

public class Test637_LargestSquareSurroundedByOne {
	/**
	 * 首先使用DP对各行的[0, i]的最长含1 subArray : left[][]
	 * 	物理意义：每行向左看最长的全1 array长度
	 * 然后使用DP对各列求出[0, j]的最长含1 subArray : up[][]
	 * 	物理意义：每列向上看每列最长的全1 array长度
	 * for (i, j)
	 * 	for min(left[i, j], up[i, j])
	 * 		检查up[左下角] >= side?
	 * 		检查left[右上角] >= side?
	 **/
	public int largestSquareSurroundedByOne(int[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] left = new int[rows][cols];
		int[][] up = new int[rows][cols];


		// construct left, up matrix
		// left represents the longest arm from [0, i] in specific rows
		// up represents the longest arm from [0, j] in specific columns
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 1) {
					left[i][j] = getNumber(left, i, j - 1) + 1;
					up[i][j] = getNumber(up, i - 1, j) + 1;
				}
			}
		}

		int max = 0;

		// iterate from [0, i]
		// 	iterate from [0, j]
		// 		iterate from [0, min(left[i, j], up[i, j])]
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int maxSidePossible = Math.min(left[i][j], up[i][j]);
				// prune
				if (maxSidePossible < max) {
					continue;
				}
				// iterate through different side length
				for (int side = maxSidePossible; side > 0; side--) {
					if (j - side + 1 < 0 || i - side + 1 < 0) {
						continue;
					} else if (up[i][j - side + 1] >= side && left[i - side + 1][j] >= side) {
						if (max < side) {
							max = side;
							break;
						}
					} else {
						continue;
					}
				}
			}
		}
		return max;
	}

	private int getNumber(int[][] matrix, int i , int j) {
		if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
			return 0;
		}
		return matrix[i][j];
	}

	public static void main(String[] args) {
		Test637_LargestSquareSurroundedByOne test = new Test637_LargestSquareSurroundedByOne();
		int[][] input = new int[][]{{1,1,1,1,1,1,1,0,0,0,1,1,0,1,1,0,0},{1,1,1,0,1,1,1,0,1,1,0,1,1,0,1,1,1},{1,0,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0},{1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1},{1,1,1,1,0,1,0,0,1,0,1,1,1,1,1,1,1},{1,0,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1},{1,1,1,1,1,1,1,0,1,0,1,1,1,1,0,1,0},{1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1}};
		System.out.println(test.largestSquareSurroundedByOne(input));
	}

}
