package Algorithms.Others.DP;

public class Test96_MergeStones {
	public int minCost(int[] stones) {
		int n = stones.length;

		// prefix[i]: sum(stones[0, i])
		int[] prefix = new int[n];
		for (int i = 0; i < n; i++)
			prefix[i] = (i == 0 ? 0 : prefix[i - 1]) + stones[i];

		// cost[i][j] is the minimal cost of merging stones [i, j]
		int[][] cost = new int[n][n];
		for (int j = 0; j < n; j++) {
			for (int i = j; i >= 0; i--) {
				if (i == j) {
					cost[i][j] = 0;
				} else {
					// divide [i, j] into [i, z] U [z + 1, j]
					cost[i][j] = Integer.MAX_VALUE;
					for (int z = i; z + 1 <= j; z++) {
						cost[i][j] = Math.min(cost[i][j], cost[i][z] + cost[z + 1][j]
								+ prefix[j] - prefix[i] + stones[i]);
					}
				}
			}
		}
		return cost[0][n - 1];
	}
}
