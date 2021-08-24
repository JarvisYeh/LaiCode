package Algorithms.Others.DP;

public class Test632_CanIWin {
	// TC: O(n^2)
	// SC: O(n^2) and is improved to O(n), columns can be compressed to 2
	public boolean canWin(int[] nums) {
		int n = nums.length;
		int[] prefixSum = new int[n];
		for (int i = 0; i < n; i++) {
			prefixSum[i] = (i == 0 ? 0 : prefixSum[i - 1]) + nums[i];
		}

		// scores[i][j] is the max score one could get if one start first to pick for nums[i, j]
		int[][] scores = new int[n][2];
		for (int j = 0; j < n; j++) {
			for (int i = j; i >= 0; i--) {
				if (i == j) {
					// scores[i][j] = nums[i];
					scores[i][j % 2] = nums[i];
				} else {
					int pickLeft = nums[i] + getSum(prefixSum, i + 1, j) - scores[i + 1][j % 2];    // - scores[i + 1][j];
					int pickRight = nums[j] + getSum(prefixSum, i, j - 1) - scores[i][(j - 1) % 2]; // - scores[i][j - 1];
					scores[i][j % 2] = Math.max(pickLeft, pickRight); // scores[i][j] = Math.max(pickLeft, pickRight);
				}
			}
		}
		// if sum - picked < picked, means picked more than half, which means wining
		// return prefixSum[n - 1] - scores[0][n - 1] <= scores[0][n - 1];
		return prefixSum[n - 1] - scores[0][(n - 1) % 2] <= scores[0][(n - 1) % 2];
	}

	// return sum of nums[i, j]
	private int getSum(int[] prefix, int i, int j) {
		if (i == 0) return prefix[j];
		return prefix[j] - prefix[i - 1];
	}
}
