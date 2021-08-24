package Algorithms.Others.DP;

public class Test_CuttingRopeAtLeastKCuts {
	// return the max product of cutting n meters' rope with >= k cuts
	// n >= 2
	// k > n
	public int maxProduct(int n, int k) {
		// n meters' rope can have at most n - 1 cuts
		if (n <= k) return -1;

		// prods[i][j]: the max product of i meters' rope with at least k cuts
		// only valid for i > k
		int[][] prods = new int[n + 1][k + 1];
		// base case
		prods[1][0] = 1;	// 1 meters rope, at least 0 cuts

		for (int j = 0; j <= k; j++) {
			for (int i = j + 1; i <= n; i++) {
				// k == 0
				if (j == 0) {
					// cut initialize as exactly = 0 situation
					prods[i][j] = i;
					// cut i meters rope to {left} U {right}
					// {left} 是大段
					// right = {i - left} 是大段
					for (int left = 1; left < i; left++) {
						prods[i][j] = Math.max(prods[i][j], prods[left][0] * prods[i - left][0]);
					}
				}
				// k > 0
				else {
					// cut i meters rope to {keep} U {i - keep}
					// {keep} 是小段
					// {i - keep} 是大段，查表
					for (int keep = 1; keep < i; keep++) {
						// 如果还需要切的刀数 > 切的长度，skip
						if (j >= i - keep) continue;
						prods[i][j] = Math.max(prods[i][j], keep * prods[i - keep][j - 1]);
					}
				}
			}
		}

		return prods[n][k];
	}

	public static void main(String[] args) {
		Test_CuttingRopeAtLeastKCuts t = new Test_CuttingRopeAtLeastKCuts();
		System.out.println(t.maxProduct(10, 0));
	}
}
