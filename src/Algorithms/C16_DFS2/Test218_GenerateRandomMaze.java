package Algorithms.C16_DFS2;

import java.util.Arrays;
import java.util.Random;

public class Test218_GenerateRandomMaze {
	public int[][] maze(int n) {
		int[][] maze = new int[n][n];
		// fill matrix will all 1s
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				maze[i][j] = 1;
			}
		}
		// start point left top corner
		maze[0][0] = 0;
		dfs(0, 0, maze);
		return maze;
	}

	private void dfs(int i, int j, int[][] maze) {
		// 注意这里 dirs要定义在recursion function里面
		// 否则for loop iterate的同时
		// 进入下一层dfs时候，dirs又会被shuffle，有的direction在这一层永远不会用用到
		int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		shuffle(dirs);
		for (int[] dir : dirs) {
			int newi = i + dir[0]*2, newj = j + dir[1]*2;
			// check if this direction is valid
			if (newi < 0 || newj < 0 || newi >= maze.length || newj >= maze[0].length || maze[newi][newj] == 0) continue;

			for (int step = 1; step <= 2; step++) {
				newi = i + dir[0] * step;
				newj = j + dir[1] * step;
				maze[newi][newj] = 0;
			}
			// recursively check {newi, newj}
			dfs(i + dir[0]*2, j + dir[1]*2, maze);
		}
	}

	private void shuffle(int[][] dirs) {
		Random rand = new Random();
		// swap 3 with rand [0, 4)
		// swap 2 with rand [0, 3)
		// swap 1 with rang [0, 2)
		for (int i = 4; i > 1; i--) {
			int idx = rand.nextInt(i);
			int[] tmp = dirs[idx];
			dirs[idx] = dirs[i - 1];
			dirs[i - 1] = tmp;
		}
	}

	public static void main(String[] args) {
		Test218_GenerateRandomMaze t = new Test218_GenerateRandomMaze();
		for (int i = 0; i < 10; i++) {
			for (int[] row : t.maze(5)) {
				System.out.println(Arrays.toString(row));
			}
			System.out.println("");
		}
	}
}
