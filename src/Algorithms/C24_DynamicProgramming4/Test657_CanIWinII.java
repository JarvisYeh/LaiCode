package Algorithms.C24_DynamicProgramming4;

public class Test657_CanIWinII {
	// method 1: recursion
	// TC: O(2^n)
	// SC: O(n)
	public int canWinI(int[] nums) {
		int[] max = {0};
		helper(nums, 0, nums.length - 1, 0, max);
		return max[0];
	}

	private void helper(int[] nums, int l, int r, int curr, int[] max) {
		if (l >= r) {
			if (l == r)
				max[0] = Math.max(max[0], curr + nums[l]);
			else
				max[0] = Math.max(max[0], curr);
			return;
		}
		// eat left
		if (nums[l + 1] > nums[r]) {
			helper(nums, l + 2, r, curr + nums[l], max);
		} else {
			helper(nums, l + 1, r - 1, curr + nums[l], max);
		}
		// eat right
		if (nums[l] > nums[r - 1]) {
			helper(nums, l + 1, r - 1, curr + nums[r], max);
		} else {
			helper(nums, l, r - 2, curr + nums[r], max);
		}
	}

	// method 2: DP
	// TC: O(n^2)
	// SC: O(n^2)
	public int canWin(int[] nums) {
		int[][] max = new int[nums.length][nums.length];
		for (int i = nums.length - 1; i >= 0; i--) {
			for (int j = i; j < nums.length; j++) {
				if (i == j) {           // base case 1: slice left
					max[i][j] = nums[i];
				} else if (i == j - 1) {// base case 2: slices left, pick larger one
					max[i][j] = Math.max(nums[i], nums[j]);
				} else {
					// case 1: pick nums[i]
					int pickLeft = nums[i];
					pickLeft += (nums[i + 1] > nums[j] ? max[i + 2][j] : max[i + 1][j - 1]);
					// case 2: pick nums[j]
					int pickRight = nums[j];
					pickRight += (nums[i] > nums[j - 1] ? max[i + 1][j - 1] : max[i][j - 2]);
					max[i][j] = Math.max(pickLeft, pickRight);
				}
			}
		}
		return max[0][nums.length - 1];
	}
}
