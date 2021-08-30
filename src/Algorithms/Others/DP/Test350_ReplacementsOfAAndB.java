package Algorithms.Others.DP;

public class Test350_ReplacementsOfAAndB {
	// change the problem into find the index with minimal (b to the left + a to the right)
	// so that the flip amount is minimal
	// TC: O(n)
	// SC: O(n)
	public int minReplacements(String input) {
		int n = input.length();
		int[] countB = new int[n];
		int[] countA = new int[n];
		for (int i = 0; i < n; i++) {
			countB[i] = (i == 0 ? 0 : countB[i - 1]) + (input.charAt(i) == 'b' ? 1 : 0);
		}

		for (int i = n - 1; i >= 0; i--) {
			countA[i] = (i == n - 1 ? 0 : countA[i + 1]) + (input.charAt(i) == 'a' ? 1 : 0);
		}

		int min = n;
		for (int i = 0; i < n; i++) {
			// b in [0, i] + a in [i, n - 1]
			// flip b in [0, i) and a in (i, n - 1]
			// do not need to flip s[i], so -1
			min = Math.min(min, countB[i] + countA[i] - 1);
		}
		return min;
	}

	public static void main(String[] args) {
		Test350_ReplacementsOfAAndB t = new Test350_ReplacementsOfAAndB();
		t.minReplacements("ba");

	}
}
