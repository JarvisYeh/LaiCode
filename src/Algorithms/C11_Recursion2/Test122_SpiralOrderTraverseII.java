package Algorithms.C11_Recursion2;

import java.util.ArrayList;
import java.util.List;

public class Test122_SpiralOrderTraverseII {
	public List<Integer> spiral(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		helper(matrix, res, 0, matrix.length, matrix[0].length);
		return res;
	}

	public void helper(int[][] mat, List<Integer> list, int offset, int row_size, int col_size) {
		if (row_size <= 1 || col_size <= 1) {
			if (row_size == 1) {
				for (int i = 0; i < col_size; i++) {
					list.add(mat[offset][offset + i]);
				}
			} else if (col_size == 1) {
				for (int i = 0; i < row_size; i++) {
					list.add(mat[offset + i][offset]);
				}
			}
			return;
		}

		for (int i = 0; i <= col_size - 2; i++) {
			list.add(mat[offset][offset + i]);
		}
		for (int i = 0; i <= row_size - 2; i++) {
			list.add(mat[offset + i][mat[0].length - 1 - offset]);
		}
		for (int i = 0; i <= col_size - 2; i++) {
			list.add(mat[mat.length - 1 - offset][mat[0].length - 1 - offset - i]);
		}
		for (int i = 0; i <= row_size - 2; i++) {
			list.add(mat[mat.length - 1 - offset - i][offset]);
		}
		helper(mat, list, offset + 1, row_size - 2, col_size - 2);
	}
}
