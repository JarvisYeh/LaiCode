package Algorithms.C21_CrossTraining3;

import java.util.*;

public class Test651_CommonNumbersOfTwoArraysII {
	// Method 1:
	// use two hashset
	// TS: O(n + m)
	// SC: O(n + m)
	public List<Integer> commonI(int[] A, int[] B) {
		List<Integer> res = new ArrayList<>();
		HashMap<Integer, Integer> mapA = new HashMap<>();
		HashMap<Integer, Integer> mapB = new HashMap<>();
		for (int i : A) {
			int count = mapA.getOrDefault(i, 0);
			mapA.put(i, count + 1);
		}

		for (int i : B) {
			int count = mapB.getOrDefault(i, 0);
			mapB.put(i, count + 1);
		}

		for (Map.Entry<Integer, Integer> entry : mapA.entrySet()) {
			int countA = entry.getValue();
			int countB = mapB.getOrDefault(entry.getKey(), 0);
			for (int i = 0; i < Math.min(countA, countB); i++) {
				res.add(entry.getKey());
			}
		}
		res.sort(Integer::compare);
		return res;
	}

	// Method 2:
	// sort first, then use pointers
	// TC: O(nlogn + mlogm + m + n)
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
