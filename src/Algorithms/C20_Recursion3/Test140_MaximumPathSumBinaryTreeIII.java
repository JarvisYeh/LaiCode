package Algorithms.C20_Recursion3;

import Design.D2_ParkingLotExp.Test;
import util.TreeNode;

public class Test140_MaximumPathSumBinaryTreeIII {
	/**
	 * Method 1:
	 * DP with 从上往下传值
	 **/
	public int maxPathSum(TreeNode root) {
		int[] maxSum = new int[1];
		maxSum[0] = Integer.MIN_VALUE;
		// stores the maximum sum of subpath in range from root to current node, including current node
		int prefixSum = 0;

		maxPathSum(root, prefixSum, maxSum);
		return maxSum[0];
	}

	private void maxPathSum(TreeNode root, int prefixSum, int[] maxSum) {
		if (root == null) {
			return;
		}

		prefixSum = (prefixSum < 0 ? 0 : prefixSum) + root.key;
		// update maxSum if necessary
		maxSum[0] = Math.max(maxSum[0], prefixSum);

		maxPathSum(root.left, prefixSum, maxSum);
		maxPathSum(root.right, prefixSum, maxSum);
	}


	/**
	 * Method 2:
	 * DP with 从下往上传值
	 * 传递的值是，以自己为root，向下的path中max sum为多少
	 * 这个sum中必须包括自己
	 */
	public int maxPathSumII(TreeNode root) {
		int[] maxSum = new int[1];
		maxSum[0] = Integer.MIN_VALUE;
		maxPathSumII(root, maxSum);
		return maxSum[0];
	}

	private int maxPathSumII(TreeNode root, int[] maxSum) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}

		int left = maxPathSumII(root.left, maxSum);
		int right = maxPathSumII(root.right, maxSum);
		left = left < 0 ? 0 : left;
		right = right < 0 ? 0 : right;

		maxSum[0] = Math.max(maxSum[0], Math.max(left, right) + root.key);

		return Math.max(left, right) + root.key;
	}

}
