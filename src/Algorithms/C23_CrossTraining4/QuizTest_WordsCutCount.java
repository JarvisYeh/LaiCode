package Algorithms.C23_CrossTraining4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class QuizTest_WordsCutCount {
	public int wordCuts(String input, List<String> dict) {
		if (input == null || input.length() == 0 || dict.size() == 0) {
			return -1;
		}

		// construct dictionary hashMap
		HashSet<String> dictSet = new HashSet<>();
		for (String s : dict) {
			dictSet.add(s);
		}
		List<Integer> s = new ArrayList<>();
		// how many possibilities cut solution for substring[0, i)
		// 0 means not possible to cut
		int[] counts = new int[input.length() + 1];
		counts[0] = 0;
		for (int i = 1; i <= input.length(); i++) {
			// first check s[0, i) is a dictionary word or not
			if (dictSet.contains(input.substring(0, i))) {
				counts[i]++;
			}
			// now check whether it can be divided into two parts
			// left: [0, j)
			// right: [j, i)
			for (int j = 1; j < i; j++) {
				if (!dict.contains(input.substring(j, i))) {
					continue;
				}
				counts[i] += counts[j];
			}
		}

		return counts[input.length()];
	}


	public static void main(String[] args) {
		QuizTest_WordsCutCount t = new QuizTest_WordsCutCount();
		int res = t.wordCuts("catsand", Arrays.asList("cat", "cats", "sand", "and"));
		System.out.println(res);
	}
}
