package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.*;

public class Test662_WordLadderII {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		HashMap<String, Integer> wordIndex = new HashMap<String, Integer>();
		// O(n)
		for (int i = 0; i < wordList.size(); i++) {
			wordIndex.put(wordList.get(i), i);
		}

		// BFS-1

		// predecessor stores <node, list of one step previous node>
		// the nodes should be candidates for the shortest path
		HashMap<String, List<String>> predecessors = new HashMap<>();
		predecessors.put(beginWord, new ArrayList<>());

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

					// if it is the first generated node
					// create the predecessor list and add current into that list
					List<String> predecessorsList = new ArrayList<>();
					predecessorsList.add(currWord);
					predecessors.put(nei, predecessorsList);
				} else {
					// if it is generated before
					// check its step, if current step + 1 is equal to the generated node step
					// meaning curr -> nei also could be a candidate for the shortest path
					if (stepMaps.get(nei) == currStep + 1) {
						predecessors.get(nei).add(currWord);
					}
				}

			}
		}

		// if no such path, return null directly
		if (!stepMaps.containsKey(endWord)) {
			return new ArrayList<>();
		}

		// do DFS to find each shortest path
		List<List<String>> res = new ArrayList<>();
		List<String> curr = new ArrayList<>();
		DFS(endWord, beginWord, predecessors, curr, res);
		return res;

	}

	private void DFS(String currWord, String beginWord, HashMap<String, List<String>> predecessors, List<String> curr, List<List<String>> res) {
		curr.add(currWord);
		if (currWord == beginWord) {
			List<String> reverseList = new ArrayList<>(curr);
			Collections.reverse(reverseList);
			res.add(reverseList);
			curr.remove(currWord);
			return;
		}

		for (String prevNode : predecessors.get(currWord)) {
			DFS(prevNode, beginWord, predecessors, curr, res);
		}
		curr.remove(currWord);
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

	public static void main(String[] args) {
		Test662_WordLadderII test = new Test662_WordLadderII();
		String[] s = {"hit", "hot", "dot", "dog", "lot", "log", "cog"};

		List<List<String>> res = test.findLadders("hit", "cog", Arrays.asList(s));
		System.out.println(res.size());
		for (List<String> list : res) {
			System.out.println(list);
		}

	}
}
