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

	public long reverseBitsII(long n) {
		long x = 0;
		for (int i = 0; i < 32; i++) {
			x <<= 1;
			x += ((n >> i) & 1);
		}
		return x;
	}


	public static void main(String[] args) {
		Test626_ReverseBitsof32bitInteger t = new Test626_ReverseBitsof32bitInteger();
		System.out.println(Long.toBinaryString(t.reverseBitsII(1L)));
	}

}
