package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test191_LargestProductOfLength {
	public int largestProductI(String[] words) {
		int[] intDict = new int[words.length];
		// O(n^2)
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				intDict[i] |= (1 << (words[i].charAt(j) - 'a'));
			}
		}

		// O(n^2)
		int max = 0;
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if ((intDict[i] & intDict[j]) == 0) {
					int size = words[i].length()*words[j].length();
					if (size > max) max = size;
				}
			}
		}
		return max;
	}
	public int largestProductII(String[] dict) {
		// corner case
		if (dict.length <= 1) {
			return 0;
		}

		// sort the dict using length of string
		Arrays.sort(dict, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return Integer.compare(s2.length(), s1.length());
			}
		});

		// pre-processing
		int intDict[] = new int[dict.length];
		for (int i = 0; i < dict.length; i++) {
			for (int j = 0; j < dict[i].length(); j++) {
				intDict[i] |= 1 << (dict[i].charAt(j) - 'a');
			}
		}

		// BFS-2
		PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				int l1 = dict[o1.i].length() * dict[o1.j].length();
				int l2 = dict[o2.i].length() * dict[o2.j].length();
				return Integer.compare(l2, l1);
			}
		});
		// offer first pair [0, 1] since two string can not be the same string
		// namely [0, 0] is not allowed
		maxHeap.offer(new Pair(0, 1));
		while (!maxHeap.isEmpty()) {
			// expand and check
			Pair curr = maxHeap.poll();
			if ((intDict[curr.i] & intDict[curr.j]) == 0) {
				return dict[curr.i].length() * dict[curr.j].length();
			}
			// generate valid pair
			// new pair -> i + 1 or j + 1
			// e.g. same string is not valid pair
			// e.g. out of index string is not valid pair
			if (curr.i + 1 < dict.length && curr.i + 1 != curr.j) {
				maxHeap.offer(new Pair(curr.i + 1, curr.j));
			}
			if (curr.j + 1 < dict.length && curr.i != curr.j + 1) {
				maxHeap.offer(new Pair(curr.i, curr.j + 1));
			}
		}
		return 0;
	}

	/**
	 * Class represent two strings' index
	 */
	private class Pair {
		int i;
		int j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) {
		Test191_LargestProductOfLength t = new Test191_LargestProductOfLength();
		System.out.println(t.largestProductI(new String[]{"aa", "bb", "ccc", "abcfgh", "ehi"}));

	}
}
