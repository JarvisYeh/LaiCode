package Algorithms.C21_CrossTraining3;

import java.util.*;

public class Test200_MaxWaterTrappedII {
	public int maxTrapped(int[][] matrix) {
		int maxTrapped = 0;
		boolean[][] generated = new boolean[matrix.length][matrix[0].length];

		Queue<Point> minHeap = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return Integer.compare(o1.level, o2.level);
			}
		});

		// generate the four sides elements of matrix to minHeap
		for (int i = 0; i < matrix[0].length; i++) {
			minHeap.offer(new Point(0, i, matrix[0][i]));
			minHeap.offer(new Point(matrix.length - 1, i, matrix[matrix.length - 1][i]));
			generated[0][i] = true;
			generated[matrix.length - 1][i] = true;
		}

		for (int i = 1; i < matrix.length - 1; i++) {
			minHeap.offer(new Point(i, 0, matrix[i][0]));
			minHeap.offer(new Point(i, matrix[0].length - 1, matrix[i][matrix[0].length - 1]));
			generated[i][0] = true;
			generated[i][matrix[0].length - 1] = true;
		}

		while (!minHeap.isEmpty()) {
			Point curr = minHeap.poll();
			// check each neighbors of the expand node
			maxTrapped += checkAndGenerate(curr.x, curr.y + 1, generated, matrix, minHeap, curr.level);
			maxTrapped += checkAndGenerate(curr.x, curr.y - 1, generated, matrix, minHeap, curr.level);
			maxTrapped += checkAndGenerate(curr.x + 1, curr.y, generated, matrix, minHeap, curr.level);
			maxTrapped += checkAndGenerate(curr.x - 1, curr.y, generated, matrix, minHeap, curr.level);
		}
		return maxTrapped;
	}

	private int checkAndGenerate(int x, int y, boolean[][] generated, int[][] matrix, Queue<Point> minHeap, int currLevel) {
		if (x >= 0 && x < generated.length && y >= 0 && y < generated[0].length && !generated[x][y]) {
			generated[x][y] = true;
			if (currLevel < matrix[x][y]) {
				minHeap.offer(new Point(x, y, matrix[x][y]));
				return 0;
			} else {
				minHeap.offer(new Point(x, y, currLevel));
				return currLevel - matrix[x][y];
			}
		}
		return 0;
	}

	private static class Point{
		int x;
		int y;
		int level;

		public Point(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}

	}

	public static void main(String[] args) {
		Test200_MaxWaterTrappedII test = new Test200_MaxWaterTrappedII();
		System.out.println(test.maxTrapped(new int[][]{{4,3,2},{5,1,3},{4,3,2}}));
	}
}
