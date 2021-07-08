package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.*;

public class Test501_AlienDictionary {
	public String alienOrder(String[] words) {
		HashMap<Character, List<Character>> graph = new HashMap<>();
		HashMap<Character, Integer> degree = new HashMap<>();
		// if buildGraph method return false, which means input is invalid, return "" directly
		if (!generateGraph(graph, degree, words))
			return "";
		StringBuilder sb = new StringBuilder();

		Queue<Character> q = new ArrayDeque<>();
		// put all nodes without incoming edges into queue
		// they are start of the dictionary
		for (Map.Entry<Character, Integer> e : degree.entrySet()) {
			if (e.getValue() == 0) {
				q.offer(e.getKey());
			}
		}

		while (!q.isEmpty()) {
			char curr = q.poll();
			sb.append(curr);
			// if curr doesn't have outward edges
			if (!graph.containsKey(curr)) {
				continue;
			}
			// check all next character of curr characters in graph
			for (Character nextChar : graph.get(curr)) {
				int count = degree.get(nextChar);
				degree.put(nextChar, count - 1);
				// if all character with smaller lexicographical order are all polled out
				// incoming edges number will become 0, and that node can be add into queue
				if (count - 1 == 0) q.offer(nextChar);
			}
		}
		// if the graph exist cycle, which is a -> b, b <- a, then no solution
		for (Map.Entry<Character, Integer> e : degree.entrySet()) {
			if (e.getValue() != 0) return "";
		}
		return new String(sb);
	}


	// return the garph of
	private boolean generateGraph(HashMap<Character, List<Character>> graph,
								  HashMap<Character, Integer> degree,
								  String[] words) {
		// put all possible character into graph and degree
		for (String s : words) {
			for (char c : s.toCharArray()) {
				graph.putIfAbsent(c, new ArrayList<>());
				degree.putIfAbsent(c, 0);
			}
		}

		// compare adjacent pair of words
		for (int i = 0; i < words.length - 1; i++) {
			// compare words[i] with words[i + 1]
			// start from first character
			int j = 0;
			while (j < words[i].length() && j < words[i + 1].length()) {
				if (words[i].charAt(j) != words[i + 1].charAt(j)) {
					// add edge from start -> end to graph
					char start = words[i].charAt(j), end = words[i + 1].charAt(j);
					List<Character> neiList = graph.getOrDefault(start, new ArrayList<>());
					neiList.add(end);
					graph.put(start, neiList);
					// put all possible vertex of graph (start and end) into degree map
					int count = degree.getOrDefault(end, 0);
					degree.put(end, count + 1);
					break;
				}
				// if second words already reach end, but first words has longer length
				// "abc" , "ab", that is invalid lexicographical order, return false to main method
				if (j == words[i + 1].length() - 1 && words[i].length() > words[i + 1].length())
					return false;
				j++;
			}
		}
		return true;
	}
}
