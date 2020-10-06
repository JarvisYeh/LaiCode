package Algorithms.C16_DFS2;

import java.util.*;

public class Test643_AllPermutationsofSubsets {
public List<String> allPermutationsOfSubsets(String set) {
	List<String> res = new ArrayList<>();
	if (set == null) {
		return res;
	}
	char[] input = set.toCharArray();
	DFS(0, input, res);
	return res;
}

private void DFS(int index, char[] input, List<String> res) {
	// add the current settled value
	res.add(new String(Arrays.copyOf(input, index)));

	// base case
	if (index == input.length) {
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
		Test643_AllPermutationsofSubsets test = new Test643_AllPermutationsofSubsets();
		System.out.println(test.allPermutationsOfSubsets(""));
	}

}
