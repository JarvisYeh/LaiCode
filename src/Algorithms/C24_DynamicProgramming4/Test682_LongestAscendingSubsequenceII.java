package Algorithms.C24_DynamicProgramming4;

import java.util.ArrayList;
import java.util.List;

public class Test682_LongestAscendingSubsequenceII {
	// method 1:
	// store the preceding element together with max length
	// TC: O(n^2)
	// SC: O(n)
	public int[] longestI(int[] a) {
		if (a == null || a.length == 0)
			return new int[0];
		// init DP[0], maxLen = 1, maxIdx = 0
		int maxLen = 1;
		int maxIdx = 0;
		// lens[i]: longest increasing subsequence length end at i
		int[] lens = new int[a.length];
		// pred[i]: preceding element index of longest increasing subsequence end at i
		int[] preds = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			// init DP[i] as using a[i] as a subsequence
			// i.e. len = 1, no preceding element
			lens[i] = 1;
			preds[i] = -1;
			// check all a[j] where j < i
			for (int j = i - 1; j >= 0; j--) {
				// if a[j] can bring longer length, update lens[i] and preds[i]
				// j-th element is now the preceding element of i
				if (a[j] < a[i] && lens[j] + 1 > lens[i]) {
					lens[i] = lens[j] + 1;
					preds[i] = j;
				}
			}

			// update global max when DP[i] is settled
			if (lens[i] > maxLen) {
				maxLen = lens[i];
				maxIdx = i;
			}
		}

		// start from the end, i.e. maxIdx
		// keep looking at the preceding element, idx = preds[maxIdx]
		// store the element into result arr, res[i] = a[maxIdx]
		int[] res = new int[maxLen];
		for (int i = maxLen - 1; i >= 0; i--) {
			res[i] = a[maxIdx];
			maxIdx = preds[maxIdx];
		}
		return res;
	}


	// method 2:
	// check each j in [0, maxIdx) backward
	// if DP[j] == DP[maxIdx] - 1 && array[j] < array[maxIdx], find the preceding element of maxIdx
	// update maxIdx = j
	public int[] longestII(int[] a) {
		if (a == null || a.length == 0)
			return new int[0];
		int maxLen = 1;
		int maxIdx = 0;
		int[] DP = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			DP[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				// update DP[i] for all a[j] < a[i]
				if (a[j] < a[i]) {
					DP[i] = Math.max(DP[i], DP[j] + 1);
				}
			}
			// update global max
			if (DP[i] > maxLen) {
				maxLen = DP[i];
				maxIdx = i;
			}
		}

		int[] res = new int[maxLen];
		for (int i = maxLen - 1; i >= 0; i--) {
			// add a[maxIdx] into result array
			res[i] = a[maxIdx];
			// check from maxIdx backward
			for (int j = maxIdx - 1; j >= 0; j--) {
				// if target found, update len and idx
				if (a[j] < a[maxIdx] && DP[j] == maxLen - 1) {
					maxLen = DP[j];
					maxIdx = j;
					break;
				}
			}
		}
		return res;
	}

	// method 3:
	// use binary search to improve TC
	// TC: O(nlogn)
	// SC: O(n)
	public int[] longestIII(int[] a) {
		if (a == null || a.length == 0)
			return new int[0];
		int[] DP = new int[a.length];
		List<Integer> lowestEnds = new ArrayList<>();
		lowestEnds.add(-1);
		int max = 1, maxIdx = 0;
		for (int i = 0; i < a.length; i++) {
			// search for index of smaller element > a[i]
			// if not found, return -1
			int idx = binarySearch(lowestEnds, a[i]);
			if (idx == -1) {
				lowestEnds.add(a[i]);
				DP[i] = lowestEnds.size() - 1;
			} else {
				lowestEnds.set(idx, a[i]);
				DP[i] = idx;
			}
			// update max and maxIdx
			if (DP[i] > max) {
				max = DP[i];
				maxIdx = i;
			}
		}

		// post-processing
		int[] res = new int[DP[maxIdx]];
		for (int i = res.length - 1; i >= 0; i--) {
			res[i] = a[maxIdx];
			int j = maxIdx - 1;
			while (j >= 0 && DP[j] != DP[maxIdx] - 1) j--;
			maxIdx = j;
		}
		return res;
	}

	private int binarySearch(List<Integer> arr, int target) {
		int l = 1, r = arr.size() - 1;
		while (l < r - 1) {
			int mid = l + ((r - l) >> 1);
			if (arr.get(mid) < target) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}

		// now l = r - 1, search range has 2 values
		if (l > 0 && l < arr.size() && arr.get(l) >= target) {
			return l;
		} else if (r > 0 && r < arr.size() && arr.get(r) >= target) {
			return r;
		}
		return -1;
	}

	public static void main(String[] args) {
		Test682_LongestAscendingSubsequenceII t = new Test682_LongestAscendingSubsequenceII();
		t.longestIII(new int[]{9,32,16,21,21,18,14,19,32,11,13});
	}
}
