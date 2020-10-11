package Algorithms.C17_CrossTraining1;

public class Test125_RotateMatrix {
	public void rotate(int[][] matrix) {
		rotate(matrix, matrix.length, 0);
	}

	private void rotate(int[][] matrix, int size, int offset) {
		if (size <= 1) {
			return;
		}


		for (int i = 0; i < size - 1; i++) {
			int tmp = matrix[offset][offset + i];

			matrix[offset][offset + i] = matrix[matrix.length - 1 - offset - i][offset];

			matrix[matrix.length - 1 - offset - i][offset] = matrix[matrix.length - 1 - offset][matrix.length - 1 - offset - i];

			matrix[matrix.length - 1 - offset][matrix.length - 1 - offset - i] = matrix[offset + i][matrix.length - 1 - offset];

			matrix[offset + i][matrix.length - 1 - offset] = tmp;
		}

		rotate(matrix, size - 2, offset + 1);
	}

	public static void main(String[] args) {
		Test125_RotateMatrix test = new Test125_RotateMatrix();
		test.rotate(new int[][]{{17,10},{34,58}});
	}

}
