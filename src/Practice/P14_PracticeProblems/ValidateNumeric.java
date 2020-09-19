package Practice.P14_PracticeProblems;

public class ValidateNumeric {
	/**
	 * 前后可以有空格
	 * dot前后可以没有数字
	 * NUMERIC ::= (SPC*)(NUM*)[DOT(NUM*)](SPC*)
	 * @param input
	 * @return
	 */
	public boolean validateNumeric(String input) {
		// remove the front and back spaces
		String str = input.trim();

		boolean seeDot = false;
		for (int i=0; i<str.length(); i++) {
			char c = str.charAt(i);

			// str[i] is a dot
			if (c == ',') {
				// see two dots
				if (seeDot) {
					return false;
				}
				seeDot = true;
			}
			// str[i] is invalid characters
			else if (c < '0' || c > '9') {
				return false;
			}

		}

		return true;
	}

	/**
	 * 正负号要求
	 * 第一位可以是正负号
	 * E/e后可以有正负号
	 * dot只能有1个
	 * E/e前后都要有数字
	 * (SPC*)
	 * ['+'/'-'] [DOT(NUM*)] [(NUM+)('E'/'e')(['+'/'-'](NUM+))]
	 * (SPC*)
	 * @param input
	 * @return
	 */
	public boolean validateScientificNotation(String input) {
		// remove the front and back spaces
		String str = input.trim();

		boolean seenNumber = false;
		boolean seenSignBeforeE = false;
		boolean seenE = false;
		boolean seenPoint = false;
		boolean seenNumberAfterE = false;
		boolean seenSignAfterE = false;

		for (int i=0; i<str.length(); i++) {
			char c = str.charAt(i);

			if (c == '+' || c == '-') {
				// .+, 12- || 12e5+
				if (((seenPoint || seenE) && !seenE) || seenNumberAfterE) {
					return false;
				}
				// 1e+- || +123-
				if (seenSignAfterE || (!seenE && seenSignBeforeE)) {
					return false;
				}
				if (seenE) {
					seenSignAfterE = true;
				} else {
					seenSignBeforeE = true;
				}
			} else if (c >= '0' && c <= '9') {
				seenNumber = true;
				if (seenE) {
					seenNumberAfterE = true;
				}
			} else if (c == 'e' || c == 'E') {
				// 12e12E || e
				if (seenE || !seenNumber) {
					return false;
				}
				seenE = true;
			} else if (c == '.') {
				// 123e. ||1.2.
				if (seenE || seenPoint) {
					return false;
				}
				seenPoint = true;
			} else {
				return false;
			}
		}

		// 123e
		if (seenE && !seenNumberAfterE) {
			return false;
		}

		return true;
	}
}
