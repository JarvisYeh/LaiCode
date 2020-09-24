package Algorithms.C10_BitRepresentation;

public class Test75_NumberOfDifferentBits {
	public int diffBits(int a, int b) {
		int tmp = a ^ b;
		int count = 0;
		for (int i=0; i<32; i++) {
			count += (tmp >> i & 1);
		}
		return count;

	}
}
