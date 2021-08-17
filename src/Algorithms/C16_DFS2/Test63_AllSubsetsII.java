package Algorithms.C16_DFS2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test63_AllSubsetsII {
	/**
	 * 找到input string的所有subset
	 * input string有重复元素
	 **/

	// method 1
	// DFS
	public List<String> subSetsI(String set) {
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


	// method 2
	// DFS
	public List<String> subSetsII(String set) {
		if (set == null) return new ArrayList<>();
		char[] input = set.toCharArray();
		Arrays.sort(input);
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		dfs(0, sb, input, res);
		return res;
	}

	private void dfs(int index, StringBuilder sb, char[] input, List<String> res) {
		if (index == input.length) {
			res.add(sb.toString());
			return;
		}

		// add one from [index, len)
		for (int i = index; i < input.length; i++) {
			// only add input[i] if it's different from this round input[i - 1]
			if (i == index || input[i] != input[i - 1]) {
				sb.append(input[i]);
				dfs(i + 1, sb, input, res);
				sb.deleteCharAt(sb.length() - 1);
			}
		}

		// now i = input.length, namely no add anything
		dfs(input.length, sb, input, res);
	}


	// Method 3:
	// assume k unique letters, each letters at most n
	// k levels, each level represents a unique character
	// each node has n + 1 branches, meaning choose 0 ~ n of this letter
	public List<String> subSetsIII(String set) {
		// corner case
		if (set == null) return new ArrayList<>();
		// generate frequency map for each character
		HashMap<Character, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < set.length(); i++) {
			Integer count = freqMap.getOrDefault(set.charAt(i), 0);
			freqMap.put(set.charAt(i), count + 1);
		}
		List<String> res = new ArrayList<>();
		helper(0, new ArrayList<Character>(freqMap.keySet()), freqMap, new StringBuilder(), res);
		return res;
	}

	private void helper(int idx, List<Character> keyList, HashMap<Character, Integer> freqMap,
						StringBuilder sb, List<String> res) {
		// base case
		if (idx == keyList.size()) {
			res.add(new String(sb));
			return;
		}

		int len = sb.length();
		// choose 0 key.get(idx)
		helper(idx + 1, keyList, freqMap, sb, res);
		// choose 1 - n key.get(idx)
		for (int i = 0; i < freqMap.get(keyList.get(idx)); i++) {
			sb.append(keyList.get(idx));
			helper(idx + 1, keyList, freqMap, sb, res);
		}
		// restore sb
		sb.delete(len, sb.length());
	}

	public static void main(String[] args) {
		Test63_AllSubsetsII test = new Test63_AllSubsetsII();
		test.subSetsII("abb");
	}
}
