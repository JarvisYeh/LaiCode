package Algorithms.C16_DFS2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test641_AllSubsetsIIofSizeK {
	/**
	 * With duplication characters
	 * return subset with size = k
	 **/

	// Method 1:
	// each level represents an idx in set
	// each node has 2 branches, add/not add set[idx]
	public List<String> subSetsIIOfSizeKI(String set, int k) {
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


	// Method 2:
	// assume k unique letters, each letters at most n
	// k levels, each level represents a unique character
	// each node has n + 1 branches, meaning choose 0 ~ n of this letter
	public List<String> subSetsIIOfSizeKII(String set, int k) {
		if (set == null || set.length() < k) {
			return new ArrayList<>();
		}

		// generate frequency map for each character
		HashMap<Character, Integer> freqMap = new HashMap<>();
		for (int i = 0; i < set.length(); i++) {
			Integer count = freqMap.getOrDefault(set.charAt(i), 0);
			freqMap.put(set.charAt(i), count + 1);
		}
		List<String> res = new ArrayList<>();
		helper(0, new ArrayList<Character>(freqMap.keySet()), freqMap, new StringBuilder(), res, k);
		return res;
	}

	private void helper(int idx, List<Character> keyList, HashMap<Character, Integer> freqMap,
						StringBuilder sb, List<String> res, int k) {
		// base case 1 & pruning
		if (sb.length() == k) {
			char[] arr = sb.toString().toCharArray();
			Arrays.sort(arr);
			res.add(new String(arr));
			return;
		}

		// base case 2
		if (idx == keyList.size()) {
			return;
		}

		int len = sb.length();
		// choose 0 key.get(idx)
		helper(idx + 1, keyList, freqMap, sb, res, k);
		// choose 1 - n key.get(idx)
		for (int i = 0; i < freqMap.get(keyList.get(idx)); i++) {
			sb.append(keyList.get(idx));
			// pruning
			if (sb.length() > k) break;
			helper(idx + 1, keyList, freqMap, sb, res, k);
		}
		// restore sb
		sb.delete(len, sb.length());
	}
}
