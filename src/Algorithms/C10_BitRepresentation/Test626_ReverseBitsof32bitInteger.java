package Algorithms.C10_BitRepresentation;

public class Test626_ReverseBitsof32bitInteger {
	public long reverseBits(long n) {
		int i = 0, j = 31;
		while (i < j) {
			n = swap(n, i++, j--);
		}
		return n;
	}

	private long swap(long n, int i, int j) {
		long left = n >> i & 1;
		long right = n >> j & 1;
		if (left != right) {
			n ^= 1L << i;
			n ^= 1L << j;
		}
		return n;
	}

}
