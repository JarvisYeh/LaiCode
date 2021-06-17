package Algorithms.C12_DynamicProgramming1;

import java.util.HashSet;
import java.util.Set;

public class Test99_DictionaryWordI {
	// use DP[i] as size = i words can be break into dictionary words
	public boolean canBreakI(String input, String[] dict) {
		// M[i]代表size = i，即[0,i)的字符串是否canBreak
		boolean[] M = new boolean[input.length() + 1];
		M[0] = true;

		Set<String> set = new HashSet<>();
		for (int i = 0; i < dict.length; i++) {
			set.add(dict[i]);
		}

		for (int i = 1; i <= input.length(); i++) {
			boolean exist = false;
			for (int j = 0; j <= i - 1; j++) {
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

	// use DP[i] as s[0, i] substring can be break into dictionary words
	public boolean canBreakII(String s, String[] dict) {
		int n = s.length();
		// whether s[0, i] can be break into dict words
		boolean[] canBreak = new boolean[n];
		HashSet<String> set = new HashSet<>();
		for (String word : dict) {
			set.add(word);
		}


		for (int i = 0; i < n; i++) {
			// no break
			if (set.contains(s.substring(0, i + 1))) {
				canBreak[i] = true;
				continue;
			}
			// break [0, i] to [0, j] (j, i]
			for (int j = 0; j < i; j++) {
				if (canBreak[j] && set.contains(s.substring(j + 1, i + 1))) {
					canBreak[i] = true;
					break;
				}
			}
		}
		return canBreak[n - 1];
	}

}
