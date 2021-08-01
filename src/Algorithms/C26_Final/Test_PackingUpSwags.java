package Algorithms.C26_Final;

public class Test_PackingUpSwags {
	// given n swags
	// divide it into size 1, 2, 4, 9, .... blocks
	// return the minimal block amount to divide n
	// TC: O(n^2)
	// SC: O(n)
	public int minimalBlocks(int n) {
		// DP[i] represents the minimal blocks needed for n swags
		int[] minBlocks = new int[n + 1];
		// base cases
		for (int i = 1; i*i <= n; i++) {
			minBlocks[i*i] = 1;
		}

		for (int i = 1; i <= n; i++) {
			if (minBlocks[i] != 0) continue;
			minBlocks[i] = i;
			// 左大段，右大段，都去查找
			// 4 -> (1, 3), (2, 2)
			// 5 -> (1, 4), (2, 3)
			for (int j = 1; j <= i/2; j++) {
				minBlocks[i] = Math.min(minBlocks[i], minBlocks[j] + minBlocks[i - j]);
			}
			// 使用右小段，时间复杂度更低，O(n^(3/2))
			// 左大段，右小段
			// 右小段为1, 4, 9, 16...,即只需要一个block
			//for (int j = 1; j*j < i; j++) {
			//	minBlocks[i] = Math.min(minBlocks[i], minBlocks[i - j*j] + 1);
			//}
		}
		return minBlocks[n];
	}

	public static void main(String[] args) {
		Test_PackingUpSwags t = new Test_PackingUpSwags();
		System.out.println(t.minimalBlocks(10));
	}
}
