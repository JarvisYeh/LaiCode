package Algorithms.C21_CrossTraining3;

public class Test199_MaxWaterTrappedI {
	/**
	 * Solution 1: DP
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 * @param array
	 * @return
	 */
	public int maxTrappedI(int[] array) {
		int[] leftMax = new int[array.length];
		int[] rightMax = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			if (i == 0 || array[i] > leftMax[i - 1]) {
				leftMax[i] = array[i];
			} else {
				leftMax[i] = leftMax[i - 1];
			}
		}

		for (int i = array.length - 1; i >= 0; i--) {
			if (i == array.length - 1 || array[i] > rightMax[i + 1]) {
				rightMax[i] = array[i];
			} else {
				rightMax[i] = rightMax[i + 1];
			}
		}

		int maxTrapped = 0;
		for (int i = 0; i < array.length; i++) {
			maxTrapped += Math.min(leftMax[i], rightMax[i]) - array[i];
		}
		return maxTrapped;
	}

	/**
	 * Solution 2: Optimized DP
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 * @param array
	 * @return
	 */
	public int maxTrappedII(int[] array) {
		int i = 0, j = array.length - 1;
		int leftMax = 0, rightMax = 0;
		int maxTrapped = 0;
		while (i <= j) {
			leftMax = Math.max(leftMax, array[i]);
			rightMax = Math.max(rightMax, array[j]);
			if (leftMax < rightMax) {
				maxTrapped += leftMax - array[i];
				i++;
			} else {
				maxTrapped += rightMax - array[j];
				j--;
			}
		}
		return maxTrapped;
	}


	public static void main(String[] args) {
		Test199_MaxWaterTrappedI test = new Test199_MaxWaterTrappedI();
		test.maxTrappedII(new int[]{5,3,1,4,6,2,3});
	}
}
