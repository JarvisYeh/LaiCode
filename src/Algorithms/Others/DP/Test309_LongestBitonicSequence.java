package Algorithms.Others.DP;

public class Test309_LongestBitonicSequence {
	// transform the problem into finding a index with longest ([0, i] increasing subsequence + [i, n - 1] decreasing subsequence)
	// TC: O(n)
	// SC: O(n)
	public int longestBitonic(int[] array) {
		int n = array.length;
		// incrLen[i] is the length of longest increasing subsequence in [0 : i] ending at i
		int[] incrLen = new int[n];
		// decLen[i] is the length of longest decreasing subsequence in [i : len - 1] ending at i
		int[] decLen = new int[n];

		for (int i = 0; i < n; i++) {
			if (i == 0) {
				incrLen[i] = 1;
			} else {
				incrLen[i] = 1;
				for (int j = i - 1; j >= 0; j--) {
					if (array[j] < array[i]) {
						incrLen[i] = Math.max(incrLen[i], incrLen[j] + 1);
					}
				}
			}
		}

		for (int i = n - 1; i >= 0; i--) {
			if (i == n - 1) {
				decLen[i] = 1;
			} else {
				decLen[i] = 1;
				for (int j = i + 1; j < n; j++) {
					if (array[j] < array[i]) {
						decLen[i] = Math.max(decLen[i], decLen[j] + 1);
					}
				}
			}
		}

		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, incrLen[i] + decLen[i] - 1);
		}
		return max;
	}

	public static void main(String[] args) {
		Test309_LongestBitonicSequence t = new Test309_LongestBitonicSequence();
		t.longestBitonic(new int[]{1,10,3,2,11,4,6,3,1});
	}
}
