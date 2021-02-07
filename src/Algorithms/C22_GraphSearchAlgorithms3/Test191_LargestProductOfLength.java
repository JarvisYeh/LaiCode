package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test191_LargestProductOfLength {
public int largestProduct(String[] dict) {
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
		int tmp = 0;
		for (char c : dict[i].toCharArray()) {
			tmp |= 1 << c - 'a';
		}
		intDict[i] = tmp;
	}

	// BFS-2
	PriorityQueue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
		@Override
		public int compare(Pair o1, Pair o2) {
			int l1 = dict[o1.i].length() * dict[o1.j].length();
			int l2 = dict[o2.i].length() * dict[o2.j].length();
			if (l1 > l2) {
				return -1;
			} else if (l1 < l2) {
				return 1;
			} else {
				return 0;
			}
		}
	});
	// offer first pair [0, 1] since two string can not be the same string
	// namely [0, 0] is not allowed
	maxHeap.offer(new Pair(0, 1));
	while (!maxHeap.isEmpty()) {
		// expand and check
		Pair curr = maxHeap.poll();
		if (checkNoSameChar(intDict[curr.i], intDict[curr.j])) {
			return dict[curr.i].length() * dict[curr.j].length();
		}
		// generate valid pair
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
 * @param i
 * @param j
 * @return true if the corresponding strings the i and j represent does not contain same characters
 */
private boolean checkNoSameChar(int i, int j) {return (i & j) == 0;}

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
		System.out.println(t.largestProduct(new String[]{"aa","bb","ccc","abcfgh","ehi"}));

	}
}
