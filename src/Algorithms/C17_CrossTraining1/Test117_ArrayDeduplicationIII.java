package Algorithms.C17_CrossTraining1;

import java.util.Arrays;

public class Test117_ArrayDeduplicationIII {
	// sorted array去除所有重复字母
	// 因为是sorted本身就不可能repeatedly removal
	// 1233 => 12
	public int[] dedup(int[] array) {
		int slow = 0, fast = 0;
		while (fast < array.length) {
			if (slow > 0 && array[fast] == array[slow - 1]) {
				while (fast < array.length && array[fast] == array[slow - 1]) fast++;
				slow--;    // remove array[slow] from preserved range
			} else {
				array[slow++] = array[fast++];
			}
		}
		return Arrays.copyOf(array, slow);
	}

	public static void main(String[] args) {
		Test117_ArrayDeduplicationIII t = new Test117_ArrayDeduplicationIII();
		System.out.println(Arrays.toString(t.dedup(new int[]{1, 2, 2, 1, 3, 3, 4, 4})));
	}

}
