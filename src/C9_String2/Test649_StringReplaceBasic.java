package C9_String2;

import java.util.HashSet;

public class Test649_StringReplaceBasic {
	/**
	 * Case 1: target.length < source.length
	 * 	不需要新建array，从左到右，两个指针谁小移谁
	 * Case 2: target.length > source.length
	 * 	需要新建更长的array
	 * 这里是通用方法，即不管谁长，都新建一个char array
	 **/
	public String replace(String input, String source, String target) {
		char[] ori = input.toCharArray();
		char[] s = source.toCharArray();
		char[] t = target.toCharArray();

		HashSet<Integer> matchIndexSet = findMatchIndex(ori, s, t);

		char[] res = new char[ori.length + (t.length - s.length)*matchIndexSet.size()];
		int i=0, j=0;
		while (i < res.length) {
			if (matchIndexSet.contains(j)) {
				// copy target string to result string
				for (int z = 0; z < t.length; z++) {
					res[i + z] = t[z];
				}
				i += t.length;
				j += s.length;
			} else {
				res[i++] = ori[j++];
			}
		}
		return new String(res);
	}


	/**
	 * obtain the hash set contains the start index
	 * of each matching position
	 **/
	private HashSet<Integer> findMatchIndex(char[] ori, char[] s, char[] t) {
		HashSet<Integer> res = new HashSet<>();
		for (int i = 0; i <= ori.length - s.length; i++) {
			if (checkEquals(ori, i, s)) {
				res.add(i);
			}
		}
		return res;
	}

	/**
	 * check if 'ori' contains same characters as 's' start at index
	 **/
	private boolean checkEquals(char[] ori, int index, char[] s) {
		for (int i=0; i<s.length; i++) {
			if (ori[index + i] != s[i]) {
				return false;
			}
		}
		return true;
	}

}
