package Algorithms.C17_CrossTraining1;

import java.util.Arrays;

public class Test116_ArrayDeduplicationII {
	// sorted array 去重，最多保留2个
	public int[] dedup(int[] array) {
		int slow = 0;
		int fast = 0;
		// keep [0, slow)
		// check array[fast]
		while (fast < array.length) {
			if (slow <= 1 || array[fast] != array[slow - 2]) {
				array[slow++] = array[fast++];
			} else {
				fast++;
			}
		}
		return Arrays.copyOf(array, slow);
	}

}
