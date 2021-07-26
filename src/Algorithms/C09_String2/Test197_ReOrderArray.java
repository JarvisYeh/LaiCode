package Algorithms.C09_String2;

public class Test197_ReOrderArray {
	/**
	 * ABCDE12345 -> A1B2C3D4E5
	 **/

	/**
	 * Solution 1
	 * 将string分为两部分，逐一移动
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 **/
	public int[] reorderI(int[] array) {
		if (array == null || array.length == 0 || array.length == 1) {
			return array;
		}
		int[] res = new int[array.length];
		int i = 0, j = array.length / 2;
		int resIndex = 0;
		while (resIndex < array.length) {
			res[resIndex++] = array[i++];
			res[resIndex++] = array[j++];
		}

		if (i != array.length/2) {
			res[resIndex++] = array[i];
		}
		if (j != array.length) {
			res[resIndex++] = array[j];
		}
		return res;
	}

	/**
	 * Solution 2
	 * In place with Recursion
	 * Time Complexity: O(nlog n)
	 * Space Complexity: O(log n)
	 **/
	public int[] reorder(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}

		// laicode中这道题并不是ABCD1234而是纯粹的数字进行reorder
		// 所以奇数的情况下最后一位其实不变，所以去除后转为偶数长度
		if (array.length % 2 == 0) {
			shuffle(array, 0, array.length - 1);
		} else {
			shuffle(array, 0, array.length - 2);
		}
		return array;
	}

	// keep the size of first and third chunks to be size/4
	// 因为right - left + 1一定是偶数(ABC123, ABCD1234)，mid一定在left + size/2
	// [left, leftMid) = size/4
	// [mid, rightMid) = size/4
	private void shuffle(int[] array, int left, int right) {
		if (left >= right) {
			return;
		}
		int size = (right - left + 1);
		int leftMid = left + size/4;
		int mid = left + size/2;
		int rightMid = mid + size/4;

		reverse(array, leftMid, mid - 1);
		reverse(array, mid, rightMid - 1);
		reverse(array, leftMid, rightMid - 1);

		shuffle(array, left, left + size/4*2 - 1);
		shuffle(array, left + size/4*2, right);
	}

	private void reverse(int[] array, int left, int right) {
		while (left <= right) {
			int tmp = array[left];
			array[left++] = array[right];
			array[right--] = tmp;
		}
	}



}
