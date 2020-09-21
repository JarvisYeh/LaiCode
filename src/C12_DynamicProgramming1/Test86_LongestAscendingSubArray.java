package C12_DynamicProgramming1;

public class Test86_LongestAscendingSubArray {
	public int longest(int[] array) {
		int curr_size = 0;
		int max_size = 0;
		for (int i=0; i<array.length; i++) {
			if (i == 0 || array[i] <= array[i - 1]) {
				curr_size = 1;
			} else {
				curr_size += 1;
			}
			max_size = Math.max(curr_size, max_size);
		}
		return max_size;
	}
}
