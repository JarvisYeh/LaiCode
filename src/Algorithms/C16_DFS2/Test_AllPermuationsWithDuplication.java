package Algorithms.C16_DFS2;

import java.util.*;

public class Test_AllPermuationsWithDuplication {
	public List<String> allPermutations(String set) {
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

	public static void main(String[] args) {
		Test_AllPermuationsWithDuplication test = new Test_AllPermuationsWithDuplication();
		System.out.println(test.allPermutations("abb"));
	}
}
