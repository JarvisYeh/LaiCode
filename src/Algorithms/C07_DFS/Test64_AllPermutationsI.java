package Algorithms.C07_DFS;

import java.util.ArrayList;
import java.util.List;

public class Test64_AllPermutationsI {
	/**
	 * Each level has states representing what letter can be put in that position.
	 * 	The number of letters left is the state amount of that level
	 * Level amount = letter amount
	 * Solution 1:
	 * 	use a boolean array to represent if letter is being used previously
	 **/
	public List<String> permutationsI(String input) {
		List<String> result = new ArrayList<>();
		if (input == null) {
			return result;
		}
		StringBuilder sb = new StringBuilder();
		char[] set = input.toCharArray();
		boolean[] used = new boolean[set.length];
		findPermutationsI(set, used, sb, result);
		return result;
	}

	private void findPermutationsI(char[] set, boolean[] used, StringBuilder sb, List<String> result) {
		if (sb.length() == set.length) {
			result.add(sb.toString());
			return;
		}

		for (int i=0; i<set.length; i++) {
			if (!used[i]) {
				// add letter
				used[i] = true;
				sb.append(set[i]);

				findPermutationsI(set, used, sb, result);

				// delete letter
				sb.deleteCharAt(sb.length() - 1);
				used[i] = false;
			}
		}
	}

	/**
	 * Solution 2:
	 * 	use swap every time to determine a position
	 * 当输出的每个值都是包括所有的输入值的时候，可以考虑使用swap
	 **/
	public List<String> permutationsII(String input) {
		List<String> result = new ArrayList<>();
		if (input == null) {
			return result;
		}
		char[] set = input.toCharArray();
		findPermuationII(set, 0, result);
		return result;
	}

	private void findPermuationII(char[] set, int index, List<String> result) {
		if (index == set.length) {
			result.add(new String(set));
		}

		// position before index is determined
		for (int i=index; i<set.length; i++) {
			swap(set, index, i);			// 吃进去
			findPermuationII(set, index + 1, result);
			swap(set, index, i);			// 吐出来
		}
	}

	private void swap(char[] set, int i, int j) {
		char tmp = set[i];
		set[i] = set[j];
		set[j] = tmp;
	}


}
