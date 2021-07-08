package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.*;

public class Test661_WordLadder {
	// TC : for each word O(V)
	//		check O(25*len(word))
	//		O(V*25*len(word))
	// SC : queue O(V) at most
	//	    map O(V) at most
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		HashSet<String> dict = new HashSet<>(wordList);
		HashMap<String, Integer> stepMap = new HashMap<>();
		Queue<String> queue = new ArrayDeque<>();
		List l = new ArrayList();

		// BFS-1
		queue.offer(beginWord);
		stepMap.put(beginWord, 1);
		while (!queue.isEmpty()) {
			// if found result, return the steps needed
			if (queue.peek().equals(endWord)) return stepMap.get(endWord);

			// poll one current word from queue
			int currStep = stepMap.get(queue.peek());
			char[] word = queue.poll().toCharArray();

			// find all possible neighbors
			// modify i-th letter from 'a' to 'z'
			for (int i = 0; i < word.length; i++) {
				char tmp = word[i];
				for (char c = 'a'; c <= 'z'; c++) {
					if (c == word[i]) continue;
					word[i] = c;
					String nei = new String(word);
					// if the neighbor is in dict, generated it
					if (!stepMap.containsKey(nei) && dict.contains(nei)) {
						queue.offer(nei);
						stepMap.put(nei, currStep + 1);
					}
				}
				word[i] = tmp;
			}
		}
		return 0;
	}

}
