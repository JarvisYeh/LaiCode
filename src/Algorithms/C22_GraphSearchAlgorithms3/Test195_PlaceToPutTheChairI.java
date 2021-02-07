package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.*;

public class Test195_PlaceToPutTheChairI {
	private static final char OB = 'O';
	private static final char EQUIP = 'E';

	public List<Integer> putChair(char[][] gym) {
		int M = gym.length;
		int N = gym[0].length;
		int[][] cost = new int[M][N];	// matrix to record the sum of cost from [i, j] to all Equipment
		// 对于每个Equipment，做一次BFS
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// for each Equipment
				if (gym[i][j] == EQUIP) {
					if (!addCost(gym, cost, i, j)) {
						return Arrays.asList(-1, -1);
					}
				}
			}
		}

		// 找到sumOfCost最小的点
		List<Integer> res = null;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// for each Equipment
				if (gym[i][j] != EQUIP && gym[i][j] != OB) {
					if (res == null) {
						res = Arrays.asList(i, j);
					} else if (cost[i][j] < cost[res.get(0)][res.get(1)]) {
						res.set(0, i);
						res.set(1, j);
					}
				}
			}
		}
		return res == null ? Arrays.asList(-1, -1) : res;
	}

	/**
	 * Doing BFS from E [i, j] to all other cells
	 * Using a queue
	 * Record current path cost, doing level order traversal for a graph
	 * 	一层一层遍历图，每次到达外层cost+1
	 * 该函数调用一次意味着
	 * 	在cost矩阵中的所有'E'和'C'位置上
	 * 	加入从E[i, j]到这些位置的Dmin
	 * @param gym
	 * @param cost
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean addCost(char[][] gym, int[][] cost, int i, int j) {
		// initialization
		int currCost = 1; // 记录下一圈cost
		boolean[][] generated = new boolean[gym.length][gym[0].length]; // 记录已经被generate过的cell
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
						// 对于当前的E来说，D(E, [x, y]) = currCost
						cost[nei.x][nei.y] += currCost;
						queue.offer(nei);
						generated[nei.x][nei.y] = true;
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
				// 如果cell是E，但是却没有被generate过，则说明题目无解
				if (gym[x][y] == EQUIP && !generated[x][y]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * return all valid neighbors from curr cell
	 * @param gym
	 * @param curr
	 * @return
	 */
	private List<Pair> getNei(char[][] gym, Pair curr) {
		List<Pair> neis = new ArrayList<>();
		int M = gym.length;
		int N = gym[0].length;
		int x = curr.x, y = curr.y;

		// 将没有越界并且不是障碍物的neighbors返回
		if (x + 1 < M && gym[x + 1][y] != OB) {
			neis.add(new Pair(x + 1, y));
		}
		if (x - 1 >= 0 && gym[x - 1][y] != OB) {
			neis.add(new Pair(x - 1, y));
		}
		if (y + 1 < N && gym[x][y + 1] != OB) {
			neis.add(new Pair(x, y + 1));
		}
		if (y - 1 >= 0 && gym[x][y - 1] != OB) {
			neis.add(new Pair(x, y - 1));
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
}
