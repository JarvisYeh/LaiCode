package Algorithms.C14_DynamicProgramming3;

public class Test103_LongestConsecutive1s {
	public int longest(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int max = array[0];
		int curr = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] == 0) {
				curr = 0;
			} else {
				curr++;
			}
			max = Math.max(max, curr);
		}
		return max;
	}

}
