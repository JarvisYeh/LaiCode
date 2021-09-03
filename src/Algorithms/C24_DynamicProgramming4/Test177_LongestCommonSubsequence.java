package Algorithms.C24_DynamicProgramming4;

public class Test177_LongestCommonSubsequence {
	public int longestI(String source, String target) {
		if (source.length() == 0 || target.length() == 0) {
			return 0;
		}
		int m = source.length(), n = target.length();
		int[][] DP = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					DP[i][j] = 0;
				} else if (source.charAt(i - 1) == target.charAt(j - 1)) {
					DP[i][j] = DP[i - 1][j - 1] + 1;
				} else {
					DP[i][j] = Math.max(DP[i - 1][j], DP[i][j - 1]);
				}
			}
		}
		return DP[m][n];
	}

	// optimize space complexity to min(O(3m, 3n))
	// DP[i][j] only use DP[i - 1][j], DP[i][j - 1], DP[i][j]
	// so only previous one row/col and current one row/col is needed, so 2 rows/cols is needed
	// but if update current position it may overwrite the current row value that is needed for next position
	// e.g. update DP[i][j] but when calculate DP[i + 1][j], still need old DP[i][j] value
	// so instead of 2 rows/cols, we keep 3 rows/cols
	public int longestII(String source, String target) {
		if (source.length() == 0 || target.length() == 0) {
			return 0;
		}
		int m = source.length(), n = target.length();

		// DP[i][j] in precious definition is same as DP[i % 3][j] in this definition
		// update rows alternatively
		int[][] DP = new int[3][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					DP[i % 3][j] = 0;
				} else if (source.charAt(i - 1) == target.charAt(j - 1)) {
					DP[i % 3][j] = DP[(i - 1) % 3][j - 1] + 1;
				} else {
					DP[i % 3][j] = Math.max(DP[(i - 1) % 3][j], DP[i % 3][j - 1]);
				}
			}
		}
		return DP[m % 3][n];
	}


	public static void main(String[] args) {
		Test177_LongestCommonSubsequence t = new Test177_LongestCommonSubsequence();
		t.longestII("af", "abf");
	}
}
