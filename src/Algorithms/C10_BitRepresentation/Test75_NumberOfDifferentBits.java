package Algorithms.C10_BitRepresentation;

public class Test75_NumberOfDifferentBits {
	// TC: O(n)
	// SC: O(1)
	public int diffBitsI(int a, int b) {
		int tmp = a ^ b;
		int count = 0;
		for (int i=0; i<32; i++) {
			count += (tmp >> i & 1);
		}
		return count;
	}


	// count bits with O(log n) time complexity
	// TS: O(log n)
	// SC: O(log n)
	public int diffBitsII(int a, int b) {
		int tmp = a ^ b;
		return countBits(tmp);
	}

	// count how many 1 in 011001
	// 011001 => 01100, 1
	// count(011001) => count(01100) + LSB
	private int countBits(int num) {
		return num == 0 ? 0 : countBits(num >>> 1) + (num & 1);
	}
}
