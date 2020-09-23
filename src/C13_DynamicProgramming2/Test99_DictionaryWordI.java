package C13_DynamicProgramming2;

import java.util.HashSet;
import java.util.Set;

public class Test99_DictionaryWordI {
	public boolean canBreak(String input, String[] dict) {
		Set<String> dictSet = new HashSet<>();
		for (String s : dict) {
			dictSet.add(s);
		}

		// M[i] represents size i subString [0, i) can be break into dictionary or not
		boolean[] M = new boolean[input.length() + 1];
		// "" assume empty string can be break into dictionary set
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

}
