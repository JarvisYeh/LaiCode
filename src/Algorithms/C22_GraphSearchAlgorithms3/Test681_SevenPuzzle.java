package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class Test681_SevenPuzzle {
	private static int[][] Directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public int numOfSteps(int[] values) {
		Board init = new Board(new int[]{0,1,2,3,4,5,6,7});
		Queue<Board> queue = new ArrayDeque<>();
		HashMap<Board, Integer> generated = new HashMap<>();

		queue.offer(init);
		generated.put(init, 0);

		while (!queue.isEmpty()) {
			Board curr = queue.poll();
			int currSteps = generated.get(curr);
			int[] zeroPos = curr.findZero();
			for (int[] dir : Directions) {
				if (curr.swap(zeroPos[0], zeroPos[1], zeroPos[0] + dir[0], zeroPos[1] + dir[1])) {
					// swap successful meaning no out of boundary
					if (!generated.containsKey(curr)) {
						Board neighbor = curr.clone();
						queue.offer(neighbor);
						generated.put(neighbor, currSteps + 1);
					}
					curr.swap(zeroPos[0], zeroPos[1], zeroPos[0] + dir[0], zeroPos[1] + dir[1]);
				}
			}
		}
		return generated.getOrDefault(new Board(values), -1);
	}


	private static class Board {
		private int R = 2;
		private int C = 4;
		int[][] board = new int[R][C];

		private Board() {}

		public Board(int[] values) {
			for (int i = 0; i < values.length; i++) {
				board[i/C][i%C] = values[i];
			}
		}

		public int[] findZero() {
			for (int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					if (board[i][j] == 0) {
						return new int[]{i, j};
					}
				}
			}
			return null;
		}

		/**
		 * Swap (i1, j2) (i2, j2) values in board
		 * if either point is out of boundary, return false
		 * @param i1
		 * @param j1
		 * @param i2
		 * @param j2
		 * @return
		 */
		public boolean swap(int i1, int j1, int i2, int j2) {
			if (i1 >= R || i1 < 0 || i2 >=R || i2 < 0 || j1 >= C || j1 < 0 || j2 >=C || j2 < 0) {
				return false;
			}
			int tmp = board[i1][j1];
			board[i1][j1] = board[i2][j2];
			board[i2][j2] = tmp;
			return true;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (getClass() != o.getClass()) return false;
			Board b = (Board) o;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (b.board[i][j] != this.board[i][j]) {
						return false;
					}
				}
			}
			return true;
		}

		@Override
		public int hashCode() {
			int hashCode = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					hashCode = hashCode*10 + board[i][j];
				}
			}
			return hashCode;
		}

		@Override
		protected Board clone() {
			Board newBoard = new Board();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					newBoard.board[i][j] = board[i][j];
				}
			}
			return newBoard;
		}
	}

	public static void main(String[] args) {
		Test681_SevenPuzzle test = new Test681_SevenPuzzle();
		test.numOfSteps(new int[]{3,6,0,7,1,2,4,5});
	}
}
