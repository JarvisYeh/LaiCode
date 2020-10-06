package Algorithms.C16_DFS2;

import java.util.Arrays;
import java.util.HashSet;

public class Test264_KeepDistanceForIdenticalElements {
	/**
	 * permutation的过程中判断permutation是否合法
	 * 利用hashset或者boolean array
	 * 如果当前判断的元素没有出现过，则可以确定在该位置
	 * 	并且加入hashset
	 * 如果当前判断的元素之前出现过
	 * 	判断是否可以放在该位置
	 * 		arr[i - arr[i] - 1] == arr[i]
	 * 	不可以跳过，判断下一个元素
	 * @param k
	 * @return
	 */
	public int[] keepDistance(int k) {
		int[] res = new int[2*k];
		int[] curr = new int[2*k];
		for (int i = 0; i < 2*k; i++) {
			curr[i] = i/2 + 1;
		}

		HashSet<Integer> set = new HashSet<>();
		DFS(0, set, curr, res);
		return res[0] != 0 ? res : null;
	}

	private void DFS(int index, HashSet<Integer> set, int[] curr, int[] res) {
		if (index == curr.length) {
			for (int i = 0; i < res.length; i++) {
				res[i] = curr[i];
			}
			return;
		}

		// all permutations
		// hashset used for avoid duplicate elements in permutation
		HashSet<Integer> deDup = new HashSet<>();
		for (int j = index; j <curr.length; j++) {
			// if it's not consider before, consider to swap this element
			if (!deDup.contains(curr[j])) {
				// 如果是这个元素第一次出现
				// 确定该元素在该位置
				if (!set.contains(curr[j])) {
					swap(curr, index, j);
					// 将该元素加入set，表明该元素确定过位置一次
					set.add(curr[index]);
					DFS(index + 1, set, curr, res);
					set.remove(curr[index]);
					swap(curr, index, j);
				}
				// 如果该元素已经确定过位置
				else {
					// 确定如果放置第二个元素在当前位置是否在合法
					// 		合法的意思是，之前放置的元素和当前位置间隔该元素的值
					// 		exp: 3xxx3
					// 如果不合法检查下一个元素是否可以放在该位置，即continue
					int prevIndex = index - curr[j] - 1;
					if (prevIndex < 0 || curr[prevIndex] != curr[j]) {
						continue;
					}
					swap(curr, index, j);
					DFS(index + 1, set, curr, res);
					swap(curr, index, j);
				}
				deDup.add(curr[j]);
			}
		}

	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	/**
	 * Method2 : same as N queens
	 * 在固定位置放置，直到放置不了，放到最后即为result
	 */
	public int[] keepDistanceII(int k) {
		int[] res = new int[2*k];
		int[] curr = new int[2*k];
		DFSII(0, curr, res, k);
		return res[0] == 0 ? null : res;
	}

	private void DFSII(int i, int[] curr, int[] res, int k) {
		// base case
		if (i == k + 1) {
			for (int j = 0; j < res.length; j++) {
				res[j] = curr[j];
			}
			return;
		}

		for (int j = 0; j < curr.length; j++) {
			if (checkPass(curr, j, i)) {
				curr[j] = i;
				curr[j + i + 1] = i;
				DFSII(i + 1, curr, res, k);
				curr[j] = 0;
				curr[j + i + 1] = 0;
			}
		}
	}

	private boolean checkPass(int[] curr, int position, int target) {
		return curr[position] == 0 && position + target + 1 < curr.length && curr[position + target + 1] == 0;
	}

	public static void main(String[] args) {
		Test264_KeepDistanceForIdenticalElements test = new Test264_KeepDistanceForIdenticalElements();
		System.out.println(Arrays.toString(test.keepDistanceII(3)));
	}
}
