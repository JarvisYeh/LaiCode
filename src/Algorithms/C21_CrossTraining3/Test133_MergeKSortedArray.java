package Algorithms.C21_CrossTraining3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test133_MergeKSortedArray {
	// method 1:
	// iteration reduction
	// n is the size of each array, k is the amount of array
	// TC: O(n + 2n + 3n + 4n + ... + kn) = O(k^n)
	// SC: helper array O(kn)
	public int[] mergeI(int[][] arrayOfArrays) {
		int count = 0;
		for (int[] arr : arrayOfArrays) {
			count += arr.length;
		}
		int[] helper = new int[count];
		int[] res = new int[count];

		// every time merge res with arrayOfArrays[i], i range [0, k)
		count = 0;
		for (int i = 0; i < arrayOfArrays.length; i++) {
			int j = 0, k = 0, r = 0;
			// merge res[0:count] and arrayOfArrays[i]
			// to helper
			while (j < arrayOfArrays[i].length && k < count) {
				if (arrayOfArrays[i][j] <= res[k]) {
					helper[r++] = arrayOfArrays[i][j++];
				} else {
					helper[r++] = res[k++];
				}
			}
			while (j < arrayOfArrays[i].length) helper[r++] = arrayOfArrays[i][j++];
			while (k < count) helper[r++] = res[k++];
			count += arrayOfArrays[i].length;

			// swap helper and res pointer
			int[] tmp = helper;
			helper = res;
			res = tmp;
		}

		return res;
	}

	// method 2:
	// binary reduction with iteration
	// TC: O(kn*log k)
	// SC: O(kn)
	public int[] mergeII(int[][] arrayOfArrays) {
		// 0, 1 -> 0
		// 2, 3 -> 1
		// 4, 5 -> 2
		//...
		// k - 2, k - 1 -> (k - 1)/2
		int count = arrayOfArrays.length;	// count of current unmerged array, initialize as k
		while (count != 1) {				// O(log k) loop for each time, count /=2
			for (int i = 0; i < count/2; i++) {		// merge two array at a time in total O(kn)
				// merge 2*i and 2*i + 1
				int[] a = arrayOfArrays[2*i], b = arrayOfArrays[2*i + 1];
				int[] merged = new int[a.length + b.length];
				int x = 0, y = 0, z = 0;
				while (x < a.length && y < b.length) {
					if (a[x] <= b[y]) {
						merged[z++] = a[x++];
					} else {
						merged[z++] = b[y++];
					}
				}
				while (x < a.length) merged[z++] = a[x++];
				while (y < b.length) merged[z++] = b[y++];
				// saved the new merged array
				arrayOfArrays[i] = merged;
			}

			// if count is odd, there will be one array left in this level
			if (count % 2 == 1) {
				// save it for next level
				arrayOfArrays[count/2] = arrayOfArrays[count - 1];
				count = count/2 + 1;
			} else {
				count /= 2;
			}
		}
		return arrayOfArrays[0];
	}

	// method 3:
	// binary reduction with recursion
	// similar to mergeSort
	// TC: O(kn*log k)
	// SC: O(kn) for extra array in heap
	//	   O(log k) for stack
	public int[] merge(int[][] arrayOfArrays) {
		if (arrayOfArrays == null || arrayOfArrays.length == 0) return null;
		if (arrayOfArrays.length == 1) return arrayOfArrays[0];
		int mid = arrayOfArrays.length / 2;
		// copyOfRange is shallow copy, O(1)
		int[] left = merge(Arrays.copyOfRange(arrayOfArrays, 0, mid));
		int[] right = merge(Arrays.copyOfRange(arrayOfArrays, mid, arrayOfArrays.length));
		return mergeTwoArray(left, right);
	}

	private int[] mergeTwoArray(int[] left, int[] right) {
		int[] res = new int[left.length + right.length];
		int i = 0, j = 0, k = 0;
		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				res[k++] = left[i++];
			} else {
				res[k++] = right[j++];
			}
		}

		while (i < left.length) res[k++] = left[i++];
		while (j < right.length) res[k++] = right[j++];
		return res;
	}

	// method 4:
	// use minHeap to store k pointers
	// TC: O(knlog k)
	// SC: O(k)
	public int[] mergeIII(int[][] arrayOfArrays) {
		Queue<MyEntry>  minHeap = new PriorityQueue<>(new Comparator<MyEntry>() {
			@Override
			public int compare(MyEntry o1, MyEntry o2) {
				return Integer.compare(o1.value, o2.value);
			}
		});

		int size = 0;
		for (int i = 0; i < arrayOfArrays.length; i++) {
			if (arrayOfArrays[i] != null && arrayOfArrays[i].length > 0) {
				minHeap.offer(new MyEntry(i, 0, arrayOfArrays[i][0]));
				size += arrayOfArrays[i].length;
			}
		}

		int[] res = new int[size];
		for (int i = 0; i < res.length; i++) {
			MyEntry curr = minHeap.poll();
			res[i] = arrayOfArrays[curr.x][curr.y];
			if (curr.y + 1 < arrayOfArrays[curr.x].length) {
				minHeap.offer(new MyEntry(curr.x, curr.y + 1, arrayOfArrays[curr.x][curr.y + 1]));
			}
		}
		return res;
	}

	static private class MyEntry {
		private int x;
		private int y;
		private int value;

		private MyEntry(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Test133_MergeKSortedArray t = new Test133_MergeKSortedArray();
		System.out.println(Arrays.toString(
				t.mergeII(new int[][]{
						{1, 5, 7, 9, 10},
						{-10, 6, 100, 120},
						{55, 57, 59},
						{33, 90, 1000}
				})
		));
	}
}
