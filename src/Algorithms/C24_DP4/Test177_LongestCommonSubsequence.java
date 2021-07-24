package Algorithms.C24_DP4;

public class Test177_LongestCommonSubsequence {
	public int longest(String source, String target) {
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

	public static void main(String[] args) {
		Test177_LongestCommonSubsequence t = new Test177_LongestCommonSubsequence();
		t.longest("af", "abf");
	}
}
