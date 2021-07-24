package Algorithms.C24_DynamicProgramming4;

public class Test143_MinimumCutsForPalindromes {
	/**
	 * Method 1: use DP tp store min cuts
	 * Time Complexity: O(n^3)
	 * Space Complexity: O(n)
	 * @param input
	 * @return
	 */
	public int minCutsI(String input) {
		if (input == "")
			return 0;
		// M represents the minimal cut it needed for [0, i] to be a palindrome
		int[] M = new int[input.length()];
		// single character is already a palindrome
		M[0] = 0;


		// fill M[]
		for (int i = 0; i < M.length; i++) {
			// corner case, if [0, i] is already a palindrome
			if (checkPalindrome(input, 0, i)) {
				M[i] = 0;
				continue;
			}

			// if [i, j] is not a palindrome, cut it into left segment and right segment (one cut)
			// left segment: [0, j]
			// right segment: (j, i]
			// the cur in left can be obtained in M[]
			// if right is palindrome, total cut = left + 1, update min
			// if right is not palindrome, check next j, the previous cut is not a possible answer
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < i; j++) {
				if (checkPalindrome(input, j+1, i)) {
					int left = M[j];
					min = Math.min(min, M[j] + 1);
				}
			}
			M[i] = min;
		}

		return M[M.length - 1];
	}

	/**
	 * Check [i, j] of input String is a palindrome
	 * @param input
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean checkPalindrome(String input, int i, int j) {
		int left = i, right = j;
		while (left <= right) {
			if (input.charAt(left++) != input.charAt(right--)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Method 2:
	 * use DP to store whether s[i][j] is a palindrome
	 * and use DP to store minCuts[i]
	 * Time Complexity: O(n^2)
	 * Space Complexity: O(n^2)
	 * @param s
	 * @return
	 */
	public int minCutsII(String s) {
		if (s == "")
			return 0;
		// mc[i]: the minimal curt to cut s[0, i] to all palindrome
		int[] mc = new int[s.length()];
		// isPalindrome[j][i]: whether s[j][i] is palindrome
		boolean[][] isPal = new boolean[s.length()][s.length()];
		mc[0] = 0;

		// calculate isPal[i][j]
		// O(n^2)
		for (int i = 1; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				if (s.charAt(i) == s.charAt(j) && (i - j <= 1 || isPal[j + 1][i - 1])) {
					isPal[j][i] = true;
				}
			}
		}

		// calculate mc[i]
		// O(n^2)
		for (int i = 1; i < s.length(); i++) {
			if (isPal[0][i]) {
				mc[i] = 0;
				continue;
			}
			// s[0, i] has i gaps, maximum cut = i
			int minCut = i;
			// left : [0, j]
			// right : (j, i], size = i - j > 0
			for (int j = 0; j < i; j++) {
				//   j=1   i=5
				// a a b c d,
				// right (1, 5], is not a palindrome, need cut = 5 - 1 = 4
				// minCut = Math.min(minCut, mc[j] + (isPal[j + 1][i] ? 1: i - j);
				if (isPal[j + 1][i]) {
					minCut = Math.min(mc[j] + 1, minCut);
				}
			}
			mc[i] = minCut;
		}
		return mc[s.length() - 1];
	}

	public static void main(String[] args) {
		Test143_MinimumCutsForPalindromes test = new Test143_MinimumCutsForPalindromes();
		System.out.println(test.minCutsII("aabcdef"));
	}

}
