package C11_Recursion2;

public class Test_Power {
	/**
	 * a^b with corner cases
	 **/
	public double power(int a, int b) {
		if (a == 0) {
			if (b <= 0) {
				throw new IllegalArgumentException("b should be positive when a = 0");
			} else {
				return 1;
			}
		}

		long res = 1;
		int count = Math.abs(b);
		for (int i = 0; i < count; i++) {
			res *= a;
		}
		if (b < 0) {
			return 1 / (double)res;
		} else {
			return res;
		}
	}

	public static void main(String[] args) {
		Test_Power test = new Test_Power();
		System.out.println(test.power(2, -20));
	}
}
