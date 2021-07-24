package Algorithms.C24_DynamicProgramming4;

public class LeetCode1312_MinimumInsertionStepsToMakeAStringPalindrome {
	// TC: O(n^2)
	// SC: O(n^2)
	public int minInsertions(String s) {
		// ms[i][j] represents the minimal characters needed to be inserted to make s[i, j] a palindrome
		int[][] minSteps = new int[s.length()][s.length()];
		// from left ro right
		for (int j = 0; j < s.length(); j++) {
			for (int i = j; i >= 0; i--) {
				if (i == j) {               // only 1 char
					minSteps[i][j] = 0;
				} else if (s.charAt(i) == s.charAt(j)) {    // s[i] == s[j]
					if (i == j - 1) {
						minSteps[i][j] = 0;  // only 2 chars
					} else {                 // > 2 chars
						minSteps[i][j] = minSteps[i + 1][j - 1];
					}
				} else {
					// min(insertLeft, insertRight) + 1
					minSteps[i][j] = 1 + Math.min(minSteps[i][j - 1], minSteps[i + 1][j]);
				}
			}
		}
		return minSteps[0][s.length() - 1];
	}
}
