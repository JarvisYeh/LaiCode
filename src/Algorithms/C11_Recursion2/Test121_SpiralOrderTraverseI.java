package Algorithms.C11_Recursion2;

import java.util.ArrayList;
import java.util.List;

public class Test121_SpiralOrderTraverseI {
	// 螺旋打印N*N matrix
	/**
	 * 每次recursion打印一个圈
	 * 圈分四次打印
	 * size为当前圈的边长
	 * offset为当前圈和最外圈的距离
	 * 圈分四次打印时每次打印的数量为当前的size - 1
	 **/
	public List<Integer> spiral(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		spiral(res, matrix, 0, matrix.length);
		return res;
	}

	private void spiral(List<Integer> res, int[][] mat, int offset, int size) {
		if (size  <= 1) {
			if (size == 1) {
				res.add(mat[offset][offset]);
			}
			return;
		}

		// offset行打印[offset, offset + size  - 2]列
		for (int i = 0; i <= size - 2; i++) {
			res.add(mat[offset][offset + i]);
		}

		// mat.length - 1 - offset列打印[offset, offset + i]行
		for (int i = 0; i <= size - 2; i++) {
			res.add(mat[offset + i][mat.length - 1 - offset]);
		}

		// mat.length - 1 - offset行打印[mat.length - 1 - offset, mat.length - 1 - offset - i]列
		for (int i = 0; i <= size - 2; i++) {
			res.add(mat[mat.length - 1 - offset][mat.length - 1 - offset - i]);
		}

		// offset列打印[mat.length - 1 - offset]行
		for (int i = 0; i <= size - 2; i++) {
			res.add(mat[mat.length - 1 - offset - i][offset]);
		}

		spiral(res, mat, offset + 1, size - 2);
	}

}
