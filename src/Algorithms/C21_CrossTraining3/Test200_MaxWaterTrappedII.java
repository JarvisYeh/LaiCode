package Algorithms.C21_CrossTraining3;

import java.util.*;

public class Test200_MaxWaterTrappedII {
	public int maxTrapped(int[][] matrix) {
		int maxTrapped = 0, m = matrix.length, n = matrix[0].length;
		boolean[][] generated = new boolean[m][n];

		List<Cell> list = new ArrayList<>();
		
		// generate the four sides elements of matrix to a list then heapify the list
		for (int i = 0; i < n; i++) {
			list.add(new Cell(0, i, matrix[0][i]));
			list.add(new Cell(m - 1, i, matrix[m - 1][i]));
			generated[0][i] = true;
			generated[m - 1][i] = true;
		}

		for (int i = 1; i < m - 1; i++) {
			list.add(new Cell(i, 0, matrix[i][0]));
			list.add(new Cell(i, n - 1, matrix[i][n - 1]));
			generated[i][0] = true;
			generated[i][n - 1] = true;
		}

		// heapify w.r.t. list
		PriorityQueue<Cell> minHeap = new PriorityQueue<>(list);

		int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		while (!minHeap.isEmpty()) {
			Cell curr = minHeap.poll();
			// check each neighbors of the expand node
			for (int[] dir : dirs) {
				int x = curr.x + dir[0];
				int y = curr.y + dir[1];
				if (x >= 0 && x < m && y >= 0 && y < n && !generated[x][y]) {
					if (curr.level > matrix[x][y]) {
						maxTrapped += curr.level - matrix[x][y];
					}
					generated[x][y] = true;
					minHeap.offer(new Cell(x, y, Math.max(curr.level, matrix[x][y])));
				}
			}
		}
		return maxTrapped;
	}

	private static class Cell implements Comparable<Cell> {
		int x;
		int y;
		int level;

		public Cell(int x, int y, int level) {
			this.x = x;
			this.y = y;
			this.level = level;
		}

		@Override
		public int compareTo(Cell o) {
			return Integer.compare(this.level, o.level);
		}
	}

	public static void main(String[] args) {
		Test200_MaxWaterTrappedII test = new Test200_MaxWaterTrappedII();
		System.out.println(test.maxTrapped(new int[][]{{4,3,2},{5,1,3},{4,3,2}}));
	}
}
