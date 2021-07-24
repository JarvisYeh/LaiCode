package Algorithms.C24_DP4;

public class LeetCode44_WildcardMatching {
	public boolean isMatch(String s, String p) {
		int m = s.length(), n = p.length();
		boolean[][] DP = new boolean[m + 1][n + 1];

		// base cases
		// 1. s = "", p=""
		DP[0][0] = true;
		// 2. p = ""
		for (int i = 1; i <= m; i++) {
			DP[i][0] = false;
		}
		// 3. s = ""
		for (int j = 1; j <= n; j++) {
			if (p.charAt(j - 1) == '*') {
				DP[0][j] = DP[0][j - 1];
			} else {
				DP[0][j] = false;
			}
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
					// xxxxxxx  a
					// yyyyyyy a/?
					DP[i][j] = DP[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					// xxxxxx a
					// yyyyyy *
					// => xxxxxx a
					// => yyyyyy
					// or
					// => xxxxxx
					// => yyyyyy *
					DP[i][j] = DP[i - 1][j] || DP[i][j - 1];
				} else {
					DP[i][j] = false;
				}
			}
		}
		return DP[m][n];
	}
}
