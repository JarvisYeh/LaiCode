package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.*;

public class Test662_WordLadderII {
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		HashSet<String> dict = new HashSet<>(wordList);

		// BFS-1
		// predecessor stores <node, list of one step previous node>
		// the nodes should be candidates for the shortest path
		HashMap<String, List<String>> predecessors = new HashMap<>();
		HashMap<String, Integer> stepMaps = new HashMap<>();
		Queue<String> queue = new ArrayDeque<>();

		predecessors.put(beginWord, new ArrayList<>());
		queue.offer(beginWord);
		stepMaps.put(beginWord, 1);

		while (!queue.isEmpty()) {
			String currWord = queue.poll();
			char[] arr = currWord.toCharArray();
			int currStep = stepMaps.get(currWord);

			// modify i-th letter from 'a' to 'z'
			for (int i = 0; i < arr.length; i++) {
				char tmp = arr[i];
				for (char c = 'a'; c <= 'z'; c++) {
					if (c == tmp) continue;
					// modify letter and check if new word is in dict
					arr[i] = c;
					String nei = new String(arr);
					if (!dict.contains(nei)) continue;
					// if it's in dict, check if it's generated before
					if (!stepMaps.containsKey(nei)) {	// not generated before
						// update stepMap, predecessorsMap
						List<String> pre = new ArrayList<>();
						pre.add(currWord);
						stepMaps.put(nei, currStep + 1);
						predecessors.put(nei, pre);
						// offer to queue
						queue.offer(nei);
					} else {                            // generated before
						// just see if currStep + 1 is equal to the minimal steps in stepMap
						if (stepMaps.get(nei) == currStep + 1) {
							predecessors.get(nei).add(currWord);
						}
						// no need to generate again
					}
				}
				arr[i] = tmp;
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


	// method 2:
	// use only BFS
	// every time going one word after ward, copy all preceding transformation list from previous word
	public List<List<String>> findLaddersII(String beginWord, String endWord, List<String> wordList) {
		HashMap<String, List<List<String>>> pathsMap = new HashMap<>();
		Queue<String> q = new ArrayDeque<>();
		HashSet<String> dict = new HashSet<>(wordList);

		q.offer(beginWord);
		// start_w, {start_w}
		List<List<String>> startList = new ArrayList<>();
		startList.add(new ArrayList<>(Arrays.asList(beginWord)));
		pathsMap.put(beginWord, startList);
		while (!q.isEmpty()) {
			String curr = q.poll();
			List<List<String>> prePaths = pathsMap.get(curr);
			char[] arr = curr.toCharArray();
			for (int i = 0; i < curr.length(); i++) {
				char tmp = arr[i];
				for (char c = 'a'; c <= 'z'; c++) {
					if (c == tmp) continue;
					arr[i] = c;
					String nei = new String(arr);
					if (!dict.contains(nei)) continue;
					if (!pathsMap.containsKey(nei)) {   // never generated before
						// add itself to all prepaths
						List<List<String>> neiPaths = new ArrayList<>();
						for (List<String> prePath : prePaths) {
							neiPaths.add(new ArrayList<>(prePath));
							neiPaths.get(neiPaths.size() - 1).add(nei);
						}
						// update queue and map
						pathsMap.put(nei, neiPaths);
						q.offer(nei);
					} else {                            // generated before
						List<List<String>> neiPaths = pathsMap.get(nei);
						if (prePaths.get(0).size() + 1 == neiPaths.get(0).size()) {
							for (List<String> prePath : prePaths) {
								neiPaths.add(new ArrayList<>(prePath));
								neiPaths.get(neiPaths.size() - 1).add(nei);
							}
						}
					}
				}
				arr[i] = tmp;
			}
		}
		return pathsMap.getOrDefault(endWord, new ArrayList<>());
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
