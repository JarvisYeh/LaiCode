package Algorithms.C15_Midterm;

public class Test_CutStringToPalindrome {
	/**
	 * Time Complexity: O(n^3)
	 * Space Complexity: O(n)
	 * @param input
	 * @return
	 */
	public int minimalCut(String input) {
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

	public static void main(String[] args) {
		Test_CutStringToPalindrome test = new Test_CutStringToPalindrome();
		System.out.println(test.minimalCut("ababbbabbababa"));
	}

}
