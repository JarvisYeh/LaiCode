package Algorithms.C09_String2;

import java.util.HashSet;

public class Test649_StringReplaceBasicII {
	/**
	 * 新建array，从右到左replace
	 **/
	public String replace(String input, String source, String target) {
		HashSet<Integer> indexSet = findMatchIndex(input, source);

		// initialize the new char[]
		char[] res = new char[input.length() + (target.length() - source.length())*indexSet.size()];

		// start from end
		// i is the end of res char[]
		// j is the end of input string
		int i = res.length - 1, j = input.length() - 1;
		while (j >= 0) {
			if (!indexSet.contains(j)) {
				res[i--] = input.charAt(j--);
			} else {
				for (int k = target.length() - 1; k >= 0; k--) {
					res[i--] = target.charAt(k);
				}
				j -= source.length();
			}
		}
		return new String(res);
	}


	/**
	 * obtain the hash set contains the end index
	 * of each matching position
	 **/
	private HashSet<Integer> findMatchIndex(String s, String source) {
		HashSet<Integer> res = new HashSet<>();
		for (int i = s.length() - 1; i >= source.length() - 1; i--) {
			if (checkEquals(s, i, source)) {
				res.add(i);
			}
		}
		return res;
	}

	/**
	 * check if 'source' contains same characters as 's' end at index
	 **/
	private boolean checkEquals(String s, int index, String source) {
		for (int i = 0; i < source.length(); i++) {
			if (s.charAt(index - i) != source.charAt(source.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
}