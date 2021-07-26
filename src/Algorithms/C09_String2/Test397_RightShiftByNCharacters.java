package Algorithms.C09_String2;

public class Test397_RightShiftByNCharacters {
	/**
	 * Step 1:
	 * 	reverse whole sentence
	 * Step 2:
	 * 	reverse [0, n - 1], [n, length - 1]
	 * 	Time Complexity: O(n)
	 * 	Space Complexity: O(n)
	 **/
	public String rightShiftI(String input, int n) {
		if (input == null || input.length() == 0) {
			return input;
		}

		if (n > input.length()) {
			n = n % input.length();
		}
		char[] charArray = input.toCharArray();
		reverse(charArray, 0, charArray.length - 1);
		reverse(charArray, 0, n - 1);
		reverse(charArray, n, charArray.length - 1);
		return new String(charArray);
	}

	private void reverse(char[] input, int start, int end) {
		int i = start, j = end;
		while (i <= j) {
			char tmp = input[i];
			input[i++] = input[j];
			input[j--] = tmp;
		}
	}

	/**
	 * Solution 2: use subString()
	 * @param input, n
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 */
	public String rightShiftII(String input, int n) {
		if (input == null || input.length() == 0) {
			return input;
		}
		n = n % input.length();
		String right = input.substring(input.length() - n);
		String left = input.substring(0, input.length() - n);
		return right + left;
	}



	public static void main(String[] args) {
		Test397_RightShiftByNCharacters test = new Test397_RightShiftByNCharacters();
		System.out.println(test.rightShiftII("abcdefg", 3));
	}

}
