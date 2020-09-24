package Algorithms.C9_String2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test65_AllPermutationsII {
	/**
	 * input中会有duplication
	 * 提前剪枝
	 * Time Complexity: O(n*n!)
	 * Space Complexity: O(n^2) 每层都有hashset
	 **/
	public List<String> permutations(String input) {
		List<String> res = new ArrayList<>();
		if (input == null) {
			return res;
		}
		if (input == "") {
			res.add("");
			return res;
		}
		char[] charArray = input.toCharArray();
		permutations(charArray, 0, res);
		return res;
	}

	private void permutations(char[] input, int index, List<String> res) {
		if (index == input.length - 1) {
			res.add(new String(input));
		}

		HashSet<Character> set = new HashSet<>();
		for (int i=index; i<input.length; i++) {
			// 剪枝，如果之前交换过相同的元素，不再交换
			if (!set.contains(input[i])) {
				set.add(input[i]);
				swap(input, index, i);
				permutations(input, index + 1, res);
				swap(input, index, i);
			}
		}
	}

	private void swap(char[] input, int i, int j) {
		char tmp = input[i];
		input[i] = input[j];
		input[j] = tmp;
	}

	public static void main(String[] args) {
		Test65_AllPermutationsII test = new Test65_AllPermutationsII();
		System.out.println(test.permutations("abbb").toString());
	}

}
