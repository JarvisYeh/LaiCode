package Algorithms.C17_CrossTraining1;

import java.util.Arrays;

public class Test_ArrayDeduplicationIV {
	// unsroted array消除连续元素，不重复删除
	// 123321 => 1221
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
}
