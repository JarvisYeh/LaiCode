package C13_DynamicProgramming2;

public class Test97_LargestSubArraySum {
	/**
	 * M[i]代表[0, i]的范围内包含i的subarray的sum最大值
	 * M[i] = max(a[i], a[i] + M[i-1])
	 * 因为只用到了M[i-1]，只用一个变量来存即可
	 * 	currSum来存M[i-1]
	 **/
	public int largestSumI(int[] array) {
		int max = array[0];
		int currSum = array[0];
		for (int i=1; i<array.length; i++) {
			currSum = Math.max(array[i], array[i] + currSum);
			max = Math.max(max, currSum);
		}
		return max;
	}

	/**
	 * Variant:
	 * 同时返回left，right boundary
	 */
	public int[] largestSumII(int[] array) {
		int maxLeft = 0, maxRight = 0;
		int currLeft = 0, currRight = 0;

		int max = array[0];
		int currSum = array[0];
		for (int i=1; i<array.length; i++) {
			// update current
			if (array[i] > array[i] + currSum) {
				currLeft = i;
				currRight = i;
				currSum = array[i];
			} else {
				currRight = i;
				currSum += array[i];
			}
			// update global
			if (currSum > max) {
				maxLeft = currLeft;
				maxRight = currRight;
				max = currSum;
			}
		}
		return new int[]{max, maxLeft, maxRight};
	}

}

