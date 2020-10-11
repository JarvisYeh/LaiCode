package Algorithms.C17_CrossTraining1;

import java.util.Arrays;

public class Test115_ArrayDeduplicationI {
	// 去除sorted array中的重复元素
	public int[] dedup(int[] array) {
		int slow = 0;
		int fast = 0;
		// keep [0, slow)
		while (fast < array.length) {
			// keep it
			if (slow == 0 || array[fast] != array[slow - 1]) {
				array[slow++] = array[fast++];
			} else {
				fast++;
			}
		}
		return Arrays.copyOf(array, slow);
	}

}
