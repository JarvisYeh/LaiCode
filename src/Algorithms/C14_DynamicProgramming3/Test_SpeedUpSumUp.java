package Algorithms.C14_DynamicProgramming3;

public class Test_SpeedUpSumUp {
	/**
	 * Given an array contains integers
	 * speed up the responds to queries for asking the sum of subarray from A[i] to A[j]
	 **/
	public int sumOfSubarray(int[] input, int i, int j) {
		// pre-processing, O(n)
		int[] M = new int[input.length];
		M[0] = input[0];

		for (int k = 0; k < input.length; k++) {
			M[k] = M[k - 1] + input[k];
		}

		// if get request, could return in O(1)
		return M[j] - M[i] + input[i];

	}

}

