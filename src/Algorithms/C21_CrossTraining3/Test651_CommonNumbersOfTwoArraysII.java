package Algorithms.C21_CrossTraining3;

import java.util.*;

public class Test651_CommonNumbersOfTwoArraysII {
	// method 1:
	// use two tree map
	// TS: O(nlogn + mlogm + nlogm)
	// SC: O(n + m)
	public List<Integer> commonI(int[] A, int[] B) {
		List<Integer> res = new ArrayList<>();
		TreeMap<Integer, Integer> mapA = new TreeMap<>();
		TreeMap<Integer, Integer> mapB = new TreeMap<>();
		for (int i : A) {
			if (mapA.containsKey(i)) {
				mapA.put(i, mapA.get(i) + 1);
			} else {
				mapA.put(i, 1);
			}
		}

		for (int i : B) {
			if (mapB.containsKey(i)) {
				mapB.put(i, mapB.get(i) + 1);
			} else {
				mapB.put(i, 1);
			}
		}

		for (Map.Entry<Integer, Integer> entry : mapA.entrySet()) {
			int countA = entry.getValue();
			if (mapB.containsKey(entry.getKey())) {
				int countB = mapB.get(entry.getKey());
				for (int i = 0; i < Math.min(countA, countB); i++) {
					res.add(entry.getKey());
				}
			}
		}
		return res;
	}

	// method 2:
	// sort first, then use pointers
	// TC: O(nlogn + mlogm + min(n,m))
	// SC: O(1)
	public List<Integer> commonII(int[] A, int[] B) {
		List<Integer> res = new ArrayList<>();
		Arrays.sort(A);
		Arrays.sort(B);
		int i = 0, j = 0;
		while (i < A.length && j < B.length) {
			if (A[i] < B[j]) {
				i++;
			} else if (A[i] > B[j]) {
				j++;
			} else {
				res.add(A[i]);
				i++;
				j++;
			}

		}
		return res;
	}
}
