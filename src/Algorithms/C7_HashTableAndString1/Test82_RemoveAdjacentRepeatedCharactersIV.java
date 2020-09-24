package Algorithms.C7_HashTableAndString1;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test82_RemoveAdjacentRepeatedCharactersIV {
	/**
	 * 消消乐，消除重复元素后又出现的重复元素也要删除
	 **/
	// Solution 1: using stack
	public String deDupI(String input) {
		if (input == null || input.length() == 0) {
			return new String();
		}
		char[] charArray = input.toCharArray();
		Deque<Character> stack = new ArrayDeque<>();
		stack.offerFirst(charArray[0]);

		int i = 1;
		// go through each characters
		while (i < charArray.length) {
			if (stack.size() == 0 || stack.peekFirst() != charArray[i]) {
				stack.offerFirst(charArray[i]);
			} else {
				while (i < charArray.length && stack.peekFirst() == charArray[i]) {
					i++;
				}
				stack.pollFirst();
			}
		}

		char[] result = new char[stack.size()];
		for (i=result.length - 1; i>=0; i--) {
			result[i] = stack.pollFirst();
		}
		return new String(result);
	}

	// Solution 2: using two pointers
	/**
	 * Case 1. first character to be checked
	 * 	keep it
	 * Case 2. character to be checked != previous reversed character
	 * 	keep it
	 * Case 3. character to be checked == previous reversed character
	 * 	j++ to the first not same character
	 * 	delete previous reversed character
	 **/
	public String deDupII(String input) {
		if (input == null || input.length() == 0) {
			return new String();
		}

		char[] charArray = input.toCharArray();
		int i = 0, j = 0;
		while (j < charArray.length) {
			if (i == 0 || charArray[j] != charArray[i-1]) {
				charArray[i++] = charArray[j++];
			} else {
				while (j < charArray.length && charArray[j] == charArray[i-1]) {
					j++;
				}
				i--;
			}
		}
		return new String(charArray, 0, i);
	}


	public static void main(String[] args) {
		Test82_RemoveAdjacentRepeatedCharactersIV test = new Test82_RemoveAdjacentRepeatedCharactersIV();
		test.deDupI("abc");
	}
}
