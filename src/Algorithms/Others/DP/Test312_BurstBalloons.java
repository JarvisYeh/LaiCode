package Algorithms.Others.DP;

public class Test312_BurstBalloons {
	// divide each segment {l, r} into {l, k - 1}, k, {k + 1, r}
	// where k = l ... r
	// burst order: left, right, and finally k-th balloon
	// DP[i][j] = max_among_all_k {DP[l][k - 1] + s[l - 1]*s[k]*s[r + 1] + DP[k + 1][r]}
	// base case is when l == r
	public int maxCoins(int[] nums) {
		int n = nums.length;
		// DP[i][j] means the max score for balloons [i, j]
		int[][] DP = new int[n][n];
		for (int size = 1; size <= n; size++) {
			for (int l = 0, r = l + size - 1;r < n; l++, r++) {
				int max = 0;
				for (int k = l; k <= r; k++) {
					int left = k - l > 0 ? DP[l][k - 1] : 0;	// k - l is the size of left segment, size > 0, look up table
					int right = r - k > 0 ? DP[k + 1][r] : 0;	// r - k is the size of right segment, size > 0, look up table
					int midScore = (l - 1 >= 0 ? nums[l - 1] : 1)
							* nums[k]
							* (r + 1 < n ? nums[r + 1] : 1);
					max = Math.max(max, left + right + midScore);
				}
				DP[l][r] = max;
			}
		}
		return DP[0][n - 1];
	}

	public static void main(String[] args) {
		Test312_BurstBalloons t = new Test312_BurstBalloons();
		System.out.println(t.maxCoins(new int[]{3,1,5,8}));
	}
}
