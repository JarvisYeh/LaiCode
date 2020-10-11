package Algorithms.C16_DFS2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test63_AllSubsetsII {
	/**
	 * 找到input string的所有subset
	 * input string有重复元素
	 **/
	public List<String> subSets(String set) {
		// corner case
		if (set == null) {
			return new ArrayList<>();
		}
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();

		char[] input = set.toCharArray();
		Arrays.sort(input);
		DFS(0, input, res, sb);
		return res;
	}

	private void DFS(int index, char[] input, List<String> res, StringBuilder sb) {
		// base case
		if (index == input.length) {
			res.add(new String(sb));
			return;
		}

		// add input[index]
		sb.append(input[index]);
		DFS(index + 1, input, res, sb);

		sb.deleteCharAt(sb.length() - 1);
		// do not add input[index]
		// however, if input[index] is not add
		// so should the following characters which are same as input[index]
		while (index + 1 < input.length && input[index + 1] == input[index]) {
			index++;
		}
		// now arr[index + 1] != arr[index]
		// skip all duplicate elements
		DFS(index + 1, input, res, sb);
	}

	public static void main(String[] args) {
		Test63_AllSubsetsII test = new Test63_AllSubsetsII();
		test.subSets("abb");
	}
}
