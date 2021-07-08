package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.*;

public class Test195_PlaceToPutTheChairI {
	private static final char OB = 'O';
	private static final char EQUIP = 'E';

	public List<Integer> putChair(char[][] gym) {
		int M = gym.length;
		int N = gym[0].length;
		int[][] cost = new int[M][N];	// matrix to record the sum of cost from [i, j] to all Equipment
		// global generated matrix, ensure the 'C' surround by 'O' will not update result even though cost = 0
		boolean[][] generatedInAllBFS = new boolean[M][N];

		// 对于每个Equipment，做一次BFS
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// for each Equipment
				if (gym[i][j] == EQUIP) {
					if (!addCost(gym, cost, i, j, generatedInAllBFS)) {
						return Arrays.asList(-1, -1);
					}
				}
			}
		}

		// 找到sumOfCost最小的点
		int min = Integer.MAX_VALUE;
		int x = -1, y = -1;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// for each place to put chair
				// and check if it's being generated in BFS
				if (gym[i][j] != EQUIP && gym[i][j] != OB && generatedInAllBFS[i][j]) {
					if (cost[i][j] < min) {
						min = cost[i][j];
						x = i;
						y = j;
					}
				}
			}
		}
		return Arrays.asList(x, y);
	}

	/**
	 * Doing BFS from E [i, j] to all other cells
	 * Using a queue
	 * Record current path cost, doing level order traversal for a graph
	 * 	一层一层遍历图，每次到达外层cost+1
	 * 该函数调用一次意味着
	 * 	在cost矩阵中的所有'E'和'C'位置上
	 * 	加入从E[i, j]到这些位置的Dmin
	 */
	private boolean addCost(char[][] gym, int[][] cost, int i, int j, boolean[][] generatedInAllBFS) {
		// initialization
		int currCost = 1; // 记录下一圈cost
		boolean[][] generated = new boolean[gym.length][gym[0].length]; // 记录已经被generate过的cell
		generated[0][0] = true;
		generatedInAllBFS[0][0] = true;
		Queue<Pair> queue = new ArrayDeque<>(); // 记录当前圈的所有cell
		queue.offer(new Pair(i, j));

		// loop
		while (!queue.isEmpty()) {
			// 对当前圈的所有点
			// 1. 记录neighbors的cost
			// 2. generate进queue
			// 3. 记录其已经被generate过
			int pairNum = queue.size();
			for (int l = 0; l < pairNum; l++) {
				Pair curr = queue.poll();
				List<Pair> neighbors = getNei(gym, curr);
				for (Pair nei : neighbors) {
					if (!generated[nei.x][nei.y]) {
						// 对于Ei来说，D(Ei, [x, y]) = currCost
						// 加入D([x, y], sum(Ei))
						cost[nei.x][nei.y] += currCost;
						queue.offer(nei);
						generated[nei.x][nei.y] = true;
						generatedInAllBFS[nei.x][nei.y] = true;
					}
				}
			}
			// 下一圈的cost要在内圈的cost基础上+1
			currCost++;
		}

		// check if there is a Equipment cell is unreachable from current Equipment [i, j]
		// 如果有则说明障碍物分隔了Equipments，则该题目不可能有解
		for (int x = 0; x < gym.length; x++) {
			for (int y = 0; y < gym[0].length; y++) {
				// if there is a Ej that is not generated, means from start Ei can not reach that Ej
				// no solution
				if (gym[x][y] == EQUIP && !generated[x][y]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * return all valid neighbors from curr cell
	 */
	private List<Pair> getNei(char[][] gym, Pair curr) {
		List<Pair> neis = new ArrayList<>();
		int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		int m = gym.length;
		int n = gym[0].length;

		for (int[] dir : dirs) {
			int x = curr.x + dir[0], y = curr.y + dir[1];
			// valid neighbor if in boundary and not obstacle
			if (x >= 0 && x < m && y >= 0 && y < n && gym[x][y] != OB) neis.add(new Pair(x, y));
		}
		return neis;
	}

	private class Pair {
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Test195_PlaceToPutTheChairI t = new Test195_PlaceToPutTheChairI();
		System.out.println(t.putChair(new char[][]{
				{'O','C','O','C','C'},
				{'O','O','O','C','E'},
				{'O','O','C','C','C'},
				{'C','C','C','C','C'},
				{'C','C','C','C','C'}}).toString()
		);
	}
}
