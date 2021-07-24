package Algorithms.C24_DynamicProgramming4;

public class Test683_CountAscendingSubsequence {
	public int numIncreasingSubsequences(int[] a) {
		int sum = 0;
		int[] DP = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			int count = 1;
			for (int j = i - 1; j >= 0; j--) {
				if (a[j] < a[i]) count += DP[j];
			}
			DP[i] = count;
			sum += DP[i];
		}
		return sum;
	}
}
