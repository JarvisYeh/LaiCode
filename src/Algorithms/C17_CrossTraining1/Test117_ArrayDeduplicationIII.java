package Algorithms.C17_CrossTraining1;

import java.util.Arrays;

public class Test117_ArrayDeduplicationIII {
	// sorted array去除所有重复字母
	// 因为是sorted本身就不可能repeatedly removal
	// 1233 => 12
	public int[] deDup(int[] array) {
		// keep[0, slow)
		// check array[fast] and onwards, not keep same elements
		int slow = 0;
		int fast = 0;
		while (fast < array.length) {
			int newFast = fast + 1;
			while (newFast < array.length && array[fast] == array[newFast]) {
				newFast++;
			}
			// after the loop newFast is the first index that its content != array[fast]
			// if there are no duplicate elements in [fast, newFast]
			if (newFast - fast == 1) {
				array[slow++] = array[fast++];
			} else {
				fast = newFast;
			}
		}
		return Arrays.copyOf(array, slow);
	}

	public static void main(String[] args) {
		Test117_ArrayDeduplicationIII t = new Test117_ArrayDeduplicationIII();
		System.out.println(Arrays.toString(t.deDup(new int[]{1, 2, 2, 1, 3, 3, 4, 4})));
	}

}
