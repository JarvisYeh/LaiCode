package Algorithms.C12_DynamicProgramming1;

import java.util.HashSet;
import java.util.Set;

public class Test99_DictionaryWordI {
	public boolean canBreak(String input, String[] dict) {
		// M[i]代表size = i，即[0,i)的字符串是否canBreak
		boolean[] M = new boolean[input.length() + 1];
		M[0] = true;

		Set<String> set = new HashSet<>();
		for (int i = 0; i < dict.length; i++) {
			set.add(dict[i]);
		}

		for (int i = 1; i <= input.length(); i++) {
			boolean exist = false;
			for (int j = 0; j <= i; j++) {
				// leftSeg: [0, j), size = j - 0 = j
				boolean left = M[j];
				// rightSeg: [j, i)
				boolean right = set.contains(input.substring(j, i));
				if (left && right) {
					exist = true;
					break;
				}
			}

			M[i] = exist;
		}
		return M[input.length()];
	}

}
