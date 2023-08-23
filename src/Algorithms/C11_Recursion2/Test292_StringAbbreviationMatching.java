package Algorithms.C11_Recursion2;

public class Test292_StringAbbreviationMatching {
	/**
	 * Time Complexity:
	 * O(n^2)
	 * Space Complexity:
	 * O(n^2)
	 **/
	public boolean matchI(String text, String pattern) {
		// base case
		// 同时走完，说明之前的走匹配
		if (text.length() == 0 && pattern.length() == 0) {
			return true;
		}
		// 不同时走完，说明最后不匹配
		if (text.length() == 0 || pattern.length() == 0) {
			return false;
		}

		if (Character.isAlphabetic(pattern.charAt(0))) {
			if (text.charAt(0) != pattern.charAt(0)) {
				return false;
			}
			return matchI(text.substring(1), pattern.substring(1));
		} else {
			int i = 0;
			int count = 0;
			while (i < pattern.length() && Character.isDigit(pattern.charAt(i))) {
				count = count * 10 + pattern.charAt(i) - '0';
				i++;
			}
			if (count > text.length()) {
				return false;
			}
			return matchI(text.substring(count), pattern.substring(i));
		}
	}


	/**
	 * 优化，不使用subString
	 * Time Complexity:
	 * O(n)
	 * Space Complexity:
	 * O(n)
	 **/
	public boolean matchII(String text, String pattern) {
		return matchII(text, 0, pattern, 0);
	}

	public boolean matchII(String text, int textStart, String pattern, int patternStart) {
		// base case
		// 同时走完，说明之前的走匹配
		if (text.length() == textStart && pattern.length() == patternStart) {
			return true;
		}
		// 不同时走完，说明最后不匹配
		if (text.length() == textStart || pattern.length() == patternStart) {
			return false;
		}

		if (Character.isAlphabetic(pattern.charAt(patternStart))) {
			if (text.charAt(textStart) != pattern.charAt(patternStart)) {
				return false;
			}
			return matchII(text, textStart + 1, pattern, patternStart + 1);
		} else {
			int i = patternStart;
			int count = 0;
			while (i < pattern.length() && Character.isDigit(pattern.charAt(i))) {
				count = count * 10 + pattern.charAt(i) - '0';
				i++;
			}
			if (textStart + 1 + count > text.length()) {
				return false;
			}
			return matchII(text, textStart + count, pattern, i);
		}
	}

	public boolean matchIII(String text, String pattern) {
		// corner case
		if (text == null || pattern == null) return false;

		int m = text.length(), n = pattern.length();
		boolean[][] DP = new boolean[n + 1][m + 1];     // DP[i][j] represents whether pattern[0:i) matches input[0:j)
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 && j == 0) DP[i][j] = true;      // DP[0][0] = true
				if (i == 0 || j == 0) continue;             // DP[0][j] and DP[0][i] = false
				if (Character.isAlphabetic(pattern.charAt(i - 1))) {
					DP[i][j] = pattern.charAt(i - 1) == text.charAt(j - 1) ? DP[i - 1][j - 1] : false;
				} else {
					int digitStart = i - 1, count = 0;
					while (digitStart >= 0 && Character.isDigit(pattern.charAt(digitStart))) {
						count = count + (pattern.charAt(digitStart) - '0') * (int) (Math.pow(10, i - 1 - digitStart));
						digitStart--;
					}
					if (count > j) {
						DP[i][j] = false;
					} else {
						DP[i][j] = DP[digitStart + 1][j - count];
					}
				}
			}
		}
		return DP[n][m];
	}

	public static void main(String[] args) {
		Test292_StringAbbreviationMatching test = new Test292_StringAbbreviationMatching();
		System.out.println(test.matchIII("sophisticatedd", "s111d"));
	}
}
