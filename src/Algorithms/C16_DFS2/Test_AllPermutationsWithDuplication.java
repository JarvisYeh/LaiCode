package Algorithms.C16_DFS2;

import java.util.*;

public class Test_AllPermutationsWithDuplication {
	// Method 1: use swap and hashset
	// if s[i] has swapped to idx before
	// not swap same character to idx again
	public List<String> allPermutationsI(String set) {
		List<String> res = new ArrayList<>();
		if (set == null) {
			return res;
		}
		char[] input = set.toCharArray();
		DFS(0, input, res);
		return res;
	}

	private void DFS(int index, char[] input, List<String> res) {
		// base case
		if (index == input.length) {
			res.add(new String(input));
			return;
		}

		// to avoid swap with duplicate values
		Set<Character> set = new HashSet<>();
		// every time before swap, check to see if it's swapped before
		for (int j = index; j < input.length; j++) {
			if (!set.contains(input[j])) {
				swap(input, index, j);
				DFS(index + 1, input, res);
				swap(input, index, j);
				set.add(input[j]);
			}
		}
	}

	private void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// Method 2: use boolean array
	// need to sort first, skip duplicate element on the run
	public List<String> allPermutationsII(String set) {
		List<String> res = new ArrayList<>();
		if (set == null) {
			return res;
		}
		char[] input = set.toCharArray();
		Arrays.sort(input);
		helper(new boolean[set.length()], input, new StringBuilder(), res);
		return res;
	}

	private void helper(boolean[] used, char[] arr, StringBuilder sb, List<String> res) {
		if (sb.length() == arr.length) {
			res.add(new String(sb));
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (used[i]) continue;	// skip used index
			// if it's duplicate element, and previous same element is not use, can not use this element
			if (i > 0 && arr[i - 1] == arr[i] && !used[i - 1]) continue;
			used[i] = true;
			sb.append(arr[i]);
			helper(used, arr, sb, res);
			sb.deleteCharAt(sb.length() - 1);
			used[i] = false;
		}
	}


	public static void main(String[] args) {
		Test_AllPermutationsWithDuplication test = new Test_AllPermutationsWithDuplication();
		System.out.println(test.allPermutationsII("abb"));
	}
}
