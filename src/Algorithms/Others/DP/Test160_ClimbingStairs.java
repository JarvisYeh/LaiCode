package Algorithms.Others.DP;

public class Test160_ClimbingStairs {
	public int stairs(int n) {
		if (n == 1) return 1;
		if (n == 2) return 2;
		// DP[i] represents for i steps, how many ways there is to climb the stairs
		int[] DP = new int[n + 1];
		DP[1] = 1;
		DP[2] = 2;
		for (int i = 3; i <= n; i++) {
			DP[i] = DP[i - 1] + DP[i - 2];
		}
		return DP[n];
	}
}
