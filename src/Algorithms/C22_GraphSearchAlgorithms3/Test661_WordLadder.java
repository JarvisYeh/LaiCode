package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.*;

public class Test661_WordLadder {
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		HashMap<String, Integer> wordIndex = new HashMap<String, Integer>();
		// O(n)
		for (int i = 0; i < wordList.size(); i++) {
			wordIndex.put(wordList.get(i), i);
		}

		// BFS-1
		Queue<String> queue = new ArrayDeque<>();
		queue.offer(beginWord);
		HashMap<String, Integer> stepMaps = new HashMap<>();
		stepMaps.put(beginWord, 1);
		while (!queue.isEmpty()) {
			String currWord = queue.poll();
			int currStep = stepMaps.get(currWord);
			// find all neighbors that are in the provided dictionary
			List<String> neighbors = getNeighbors(currWord, wordIndex);
			for (String nei : neighbors) {
				if (!stepMaps.containsKey(nei)) {
					stepMaps.put(nei, currStep + 1);
					queue.offer(nei);
				}
			}
		}
		// if can not route to the end word, return 0
		return stepMaps.getOrDefault(endWord, 0);

	}

	// O(word.length*25)
	private List<String> getNeighbors(String currWord, HashMap<String, Integer> wordIndex) {
		List<String> neis = new ArrayList<>();
		StringBuilder wordBuilder = new StringBuilder(currWord);

		// O(word.length)
		// iterate through each index position of the original word
		for (int i = 0; i < wordBuilder.length(); i++) {
			char oriLetter = wordBuilder.charAt(i);
			// O(25)
			// change that position letter to new letter, which range from 'a' to 'z'
			for (char c = 'a'; c <= 'z'; c++) {
				if (c == oriLetter) {
					continue;
				}
				wordBuilder.setCharAt(i, c);
				if (wordIndex.containsKey(wordBuilder.toString())) {
					neis.add(wordBuilder.toString());
				}
				wordBuilder.setCharAt(i, oriLetter);
			}
		}
		return neis;
	}

}
