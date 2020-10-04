package Algorithms.C16_DFS2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test641_AllSubsetsIIofSizeK {
	/**
	 * With duplication characters
	 * return subset with size = k
	 **/
	public List<String> subSetsIIOfSizeK(String set, int k) {
		if (set == null || set.length() < k) {
			return new ArrayList<>();
		}

		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		char[] input = set.toCharArray();
		Arrays.sort(input);

		DFS(0, 0, input, sb, res, k);
		return res;
	}

	private void DFS(int index, int pick, char[] input, StringBuilder sb, List<String> res, int k) {
		// base case 1
		if (pick == k) {
			res.add(new String(sb));
			return;
		}
		// base case 2
		if (index == input.length) {
			return;
		}

		// reserve input[index]
		sb.append(input[index]);
		DFS(index + 1, pick + 1, input, sb, res, k);

		// skip input[index] and following same characters
		sb.deleteCharAt(sb.length() - 1);
		while (index + 1 < input.length && input[index + 1] == input[index]) {
			index++;
		}
		DFS(index + 1, pick, input, sb, res, k);
	}

}
