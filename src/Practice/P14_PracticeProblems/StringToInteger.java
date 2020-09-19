package Practice.P14_PracticeProblems;

public class StringToInteger {
	/**
	 * Integer ::=(SPC*)['+'|'-'](NUM+)(SPC*)
	 * 即input String前后可能有空格
	 * 且数字前可能有正负号
	 * @param input
	 * @return
	 */
	public int myAtoi (String input) {
		// corner case: null or empty input string
		if (input == null || input.length() == 0) {
			return -1;
		}

		// step 1: skip the front spaces
		int i = 0;
		while (i < input.length() && input.charAt(i) == ' ') {
			i++;
		}

		// step 2: check sign
		boolean positiveNum = true;
		if (i < input.length() && input.charAt(i) == '+' || input.charAt(i) == '-') {
			positiveNum = input.charAt(i) == '+';
			i++;
		}

		// step 2: deal with numbers
		long sum = 0; // in case of number overflows
		while (i < input.length() && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
			sum = sum * 10 + input.charAt(i) - '0';
			// if overflows a integer
			// returns max/min integer directly
			if (positiveNum && sum > (long)Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			}
			if (!positiveNum && -sum < (long)Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
		}
		sum = positiveNum ? sum : -sum;
		return (int) sum;
	}
}
