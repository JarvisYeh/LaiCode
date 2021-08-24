package Algorithms.Others.DP;

public class Test90_ArrayHopperIII {
	public int minJump(int[] array) {
		int n = array.length;
		int[] DP = new int[n];
		DP[n - 1] = array[n - 1] > 0 ? 1 : Integer.MAX_VALUE;
		for (int i = n - 2; i >= 0; i--) {
			if (i + array[i] >= n) {
				DP[i] = 1;
			} else {
				DP[i] = Integer.MAX_VALUE;
				for (int j = i + 1; j <= i + array[i]; j++) {
					if (DP[j] == Integer.MAX_VALUE) continue;
					DP[i] = Math.min(DP[i], DP[j] + 1);
				}
			}
		}
		return DP[0] == Integer.MAX_VALUE ? -1 : DP[0];
	}
}
