package Algorithms.C16_DFS2;

import java.util.Arrays;
import java.util.HashSet;

public class Test264_KeepDistanceForIdenticalElements {
	/**
	 * Method 1:
	 * determine index by index
	 * permutation的过程中判断permutation是否合法
	 * 利用hashset或者boolean array
	 * 如果当前判断的元素没有出现过，则可以确定在该位置
	 * 如果当前判断的元素之前出现过
	 * 判断是否可以放在该位置
	 * 		num = arr[j]
	 * 		arr[i - num - 1] == num
	 * 可以进入下一层recursion（即决定下一个index的数字）
	 * 不可以则判断当前层下一个元素
	 * recursion tree has O(2k) levels
	 * last level: O(n!) nodes
	 * each node has for loop at most O(n = 2k)
	 * TC: O((2k)*(2k)!)
	 * SC: O(2k)
	 */
	public int[] keepDistanceI(int k) {
		int[] curr = new int[2 * k];
		for (int i = 0; i < k; i++) {
			curr[i] = curr[i + k] = i + 1;
		}

		// used[i] means number i is determined its position before
		// number range from [1, k]
		boolean[] used = new boolean[k + 1];
		return DFSI(0, used, curr) ? curr : null;
	}

	private boolean DFSI(int index, boolean[] used, int[] curr) {
		if (index == curr.length) {
			return true;
		}

		// all permutations
		for (int j = index; j < curr.length; j++) {
			int num = curr[j];
			// any number first time occurs, can be put in curr[index]
			if (!used[num]) {
				swap(curr, index, j);
				used[num] = true;	// marked as used once
				if (DFSI(index + 1, used, curr)) return true;
				used[num] = false;
				swap(curr, index, j);
			}
			// num occurs before
			// check if it's legal to put it in curr[index]
			// 		e.g. curr[index - num - 1] = num
			// 		exp: 3xxx3
			else if (index - num - 1 >= 0 && curr[index - num - 1] == num){
				swap(curr, index, j);
				if (DFSI(index + 1, used, curr)) return true;
				swap(curr, index, j);
			}
		}
		return false;
	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * Method 2: determine index by index
	 * not use swap to determine each positions
	 * but loop from index = [1, curr.length) to check all index
	 * use a int[] to count the times that a specific num is determined in current array
	 * recursion tree O(2k) levels, each node has k branches
	 * each node has for loop O(k)
	 * TC: O(k*[(k)^(2k)])
	 * SC: O(2k)
	 */
	public int[] keepDistanceII(int k) {
		// countUsed[i]: how many number i is determined position in current array
		int[] countUsed = new int[k + 1];
		int[] curr = new int[2*k];
		return helper(0, curr, countUsed, k) ? curr : null;
	}

	private boolean helper(int idx, int[] curr, int[] countUsed, int k) {
		// base case
		if (idx == curr.length) {
			return true;
		}

		// curr[0:idx] is all determined number as far as this level concerns
		// check number form [i, k]
		for (int i = 1; i <= k; i++) {
			// first time occurs
			// or 3xxx3
			if (countUsed[i] == 0 || (countUsed[i] == 1 && idx - i - 1 >= 0 && curr[idx - i - 1] == i)) {
				countUsed[i]++;
				curr[idx] = i;
				if (helper(idx + 1, curr, countUsed, k)) return true;
				countUsed[i]--;
			}
		}
		return false;
	}

	/**
	 * Method3: determine pair by pair (number by number)
	 * check pairs from [1, k]
	 * recursion tree k levels
	 * each node has O(2*k) branches
	 * each node has O(2*k) for loop
	 * TC: (2k*[(2k)^k])
	 * SC: O(k)
	 */
	public int[] keepDistanceIII(int k) {
		int[] curr = new int[2 * k];
		return DFSII(0, curr, k) ? curr : null;
	}

	private boolean DFSII(int n, int[] curr, int k) {
		// base case: all k pairs are placed
		if (n == k + 1) {
			return true;
		}

		// check all index to put pair {n, n}
		for (int idx = 0; idx < curr.length; idx++) {
			// check if idx and idx + n + 1 haven't been placed
			if (curr[idx] == 0 && idx + n + 1 < curr.length && curr[idx + n + 1] == 0) {
				curr[idx] = curr[idx + n + 1] = n;
				if (DFSII(n + 1, curr, k)) return true;
				curr[idx] = curr[idx + n + 1] = 0;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Test264_KeepDistanceForIdenticalElements test = new Test264_KeepDistanceForIdenticalElements();
		System.out.println(Arrays.toString(test.keepDistanceI(3)));
	}
}
