package Algorithms.C10_BitRepresentation;

public class Test78_HexadecimalRepresentation {
	/**
	 * Solution 1:
	 * Start from LSB, append 1 by 1 and reverse at last
	 **/
	public String hexI(int number) {
		if (number == 0) {
			return "0x0";
		}
		char[] dict = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuilder sb = new StringBuilder();
		while (number != 0) {
			sb.append(dict[number % 16]);
			number /= 16;
		}
		sb.append("x0");
		return new String(sb.reverse());
	}

	/**
	 * Solution 2:
	 * 从MSB开始，每4 bits代表一个16进制数字
	 * 同时需要一个flag，使得最开始没有意义的0不被加入结果
	 **/
	public String hexII(int number) {
		if (number == 0) {
			return "0x0";
		}
		char[] dict = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuilder sb = new StringBuilder();
		sb.append("0x");

		boolean isLeadingZero = true;
		for (int i=28; i>=0; i-=4) {
			char hexNum = dict[number >> i & 0xf];
			// 不是0或者是有意义的数字，都保留
			if (hexNum != '0' || !isLeadingZero) {
				// 只要不是0的数字，即表明已经有意义的数字开始了
				isLeadingZero = false;
				sb.append(hexNum);
			}
		}
		return new String(sb);
	}


}
