package Algorithms.C21_CrossTraining3;

public class Test_FindAll1sSubMatrix {

	public int findAll1sSubMatrix(int[][] matrix) {
		Test198_LargestRectangleInHistogram helper = new Test198_LargestRectangleInHistogram();
		int maxArea = 0;
		int[] current = null;
		for (int i = 0; i < matrix.length; i++) {
			if (current == null) {
				current = matrix[0];
			} else {
				for (int j = 0; j < matrix[i].length; j++) {
					current[j] = matrix[i][j] == 0 ? 0 : 1 + current[j];
				}
			}
			maxArea = Math.max(maxArea, helper.largestII(current));

		}
		return maxArea;
	}

	public static void main(String[] args) {
		Test_FindAll1sSubMatrix test = new Test_FindAll1sSubMatrix();
		System.out.println(test.findAll1sSubMatrix(new int[][]{{0, 0, 0, 0}, {1, 1, 1, 1}, {0, 1, 1, 1}, {1, 0, 1, 1}}));
	}
}
