package Algorithms.C10_BitRepresentation;

public class Test74_PowerOfTwo {
	/**
	 * 二进制表示中只有1位是1则是二的方
	 **/
	public boolean isPowerOfTwoI(int number) {
		if (number < 0) {
			return false;
		}
		int count = 0;
		for (int i=0; i<32; i++) {
			if ((number >> i & 1) == 1) {
				count += 1;
			}
		}
		return count == 1 ? true : false;
	}

	/*
	 *  01000 - 1 = 00111
	 *  01000 & 00111 = 0
	 */
	public boolean isPowerOfTwoII(int number) {
		return number > 0 && (number & (number - 1)) == 0;
	}

}
