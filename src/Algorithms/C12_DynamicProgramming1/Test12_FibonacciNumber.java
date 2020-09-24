package Algorithms.C12_DynamicProgramming1;

public class Test12_FibonacciNumber {
	/**
	 * 使用notebook来存储已经计算出的fibonacci数列结果，利用空间换取时间复杂度
	 **/
	public long fibonacci(int K) {
		if (K <= 0) {
			return 0;
		}
		// notebook
		long[] fibsFound = new long[K + 1];
		fibsFound[0] = 0;
		fibsFound[1] = 1;

		for (int i = 2; i <= K; i++) {
			fibsFound[i] = fibsFound[i - 1] + fibsFound[i-2];
		}
		return fibsFound[K];
	}
}