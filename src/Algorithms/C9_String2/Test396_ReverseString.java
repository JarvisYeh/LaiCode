package Algorithms.C9_String2;

public class Test396_ReverseString {
	/**
	 * Solution 1: use two pointers
	 * [0,i) ∩ （j,length - 1]processed
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 **/
	public String reverseI(String input) {
		int i = 0, j = input.length() - 1;
		char[] charArray = input.toCharArray();
		while (i <= j) {
			char tmp = charArray[i];
			charArray[i++] = charArray[j];
			charArray[j--] = tmp;
		}
		return new String(charArray);
	}

	/**
	 * Solution 2: use recursion
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 **/
	public String reverseII(String input) {
		char[] charArray = input.toCharArray();
		reverse(charArray, 0, input.length() - 1);
		return String.valueOf(charArray);
	}

	private void reverse(char[] input, int left, int right) {
		if (left >= right) {
			return;
		}

		char tmp = input[left];
		input[left] = input[right];
		input[right] = tmp;
		reverse(input, left + 1, right - 1);
	}

	public static void main(String[] args) {
		Test396_ReverseString test = new Test396_ReverseString();
		System.out.println(test.reverseII("abcdefg"));
	}

}
