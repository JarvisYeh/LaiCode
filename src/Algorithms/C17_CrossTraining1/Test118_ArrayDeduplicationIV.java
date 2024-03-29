package Algorithms.C17_CrossTraining1;

import java.util.Arrays;

public class Test118_ArrayDeduplicationIV {
	// unsorted array, repeatedly removal
	public int[] dedup(int[] array) {
		int slow = 0;
		int fast = 0;
		// keep [0, slow)
		while (fast < array.length) {
			// keep
			if (slow == 0 || array[fast] != array[slow - 1]) {
				array[slow++] = array[fast++];
			}
			// skip all same elements afterwards
			// also throw away the original kept array[slow - 1]
			else {
				while (fast < array.length && array[fast] == array[slow - 1]) {
					fast++;
				}
				slow--;
			}
		}
		return Arrays.copyOf(array, slow);

	}
}
