package Algorithms.C13_DynamicProgramming2;

import java.util.HashSet;
import java.util.Set;

public class Test99_DictionaryWordI {
	public boolean canBreakI(String input, String[] dict) {
		Set<String> dictSet = new HashSet<>();
		for (String s : dict) {
			dictSet.add(s);
		}

		// M[i] represents size i subString [0, i) can be break into dictionary or not
		boolean[] M = new boolean[input.length() + 1];
		// "" assume empty string can be break into dictionary set
		// if interviewer ask for "" to be false, set it as the separate corner case
		// 	i.e. if (input == "") return false;
		M[0] = true;
		for (int i = 0; i <= M.length; i++) {
			for (int j = 0; j < i; j++) {
				// M[j]: [0, j), 左大段
				// subString: [j, i)，右小段
				if (M[j] && dictSet.contains(input.substring(j, i))) {
					M[i] = true;
					break;
				}
			}
		}

		return M[M.length - 1];
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
