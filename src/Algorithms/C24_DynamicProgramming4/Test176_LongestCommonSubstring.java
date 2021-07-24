package Algorithms.C24_DynamicProgramming4;

public class Test176_LongestCommonSubstring {
	public String longestCommon(String source, String target) {
		int m = source.length(), n = target.length();
		int[][] DP = new int[m + 1][n + 1];
		int maxLen = 0;
		int[] range = new int[2];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					DP[i][j] = 0;
				} else if (source.charAt(i - 1) == target.charAt(j - 1)){
					DP[i][j] = DP[i - 1][j - 1] + 1;
					if (DP[i][j] > maxLen) {
						maxLen = DP[i][j];
						range[0] = i - DP[i][j];
						range[1] = i;
					}
				}
			}
		}
		return source.substring(range[0], range[1]);
	}
}
