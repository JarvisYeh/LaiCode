package C6_HeapAndGraphSeachAlgorithms1;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test26_KthSmallestNumberInSortedMatrix {
	/**
	 * Related to Best First Search
	 * BFS pop and push neighbors, pay attention to avoid pushing same node, and avoid crossing the boundary
	 * pop from minHeap K times, the last one is answer
	 **/
	public int kthSmallest(int[][] matrix, int k) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean[][] generated = new boolean[rows][cols];

		// minHeap using the val in Cell as the reference
		Queue<Cell> minHeap = new PriorityQueue<>(k, new Comparator<Cell>() {
			@Override
			public int compare(Cell c1, Cell c2) {
				if (c1.val == c2.val) {
					return 0;
				}
				return c1.val < c2.val ? -1 : 1;
			}
		});

		minHeap.offer(new Cell(matrix[0][0], 0, 0));
		for (int i = 0; i < k - 1; i++) {
			Cell curr = minHeap.poll();
			// if the vertical neighbor exists and is not being expanded
			if (curr.row + 1 <= rows - 1 && !generated[curr.row + 1][curr.col]) {
				minHeap.offer(new Cell(matrix[curr.row + 1][curr.col], curr.row + 1, curr.col));
				generated[curr.row + 1][curr.col] = true;
			}

			// if the horizontal neighbor exists and is not being expanded
			if (curr.col + 1 <= cols - 1 && !generated[curr.row][curr.col + 1]) {
				minHeap.offer(new Cell(matrix[curr.row][curr.col + 1], curr.row, curr.col + 1));
				generated[curr.row][curr.col + 1] = true;
			}
		}
		return minHeap.peek().val;
	}
}

class Cell {
	int val;
	int row;
	int col;

	public Cell(int val, int row, int col) {
		this.val = val;
		this.row = row;
		this.col = col;
	}
}
