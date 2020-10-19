package Algorithms.C20_Recursion3;

import util.TreeNode;

public class Test139_MaximumPathSumBinaryTreeII {
	// maximum sum from any node to any node
	public int maxPathSum(TreeNode root) {
		int[] maxSum = new int[1];
		maxSum[0] = Integer.MIN_VALUE;
		maxPathSum(root, maxSum);
		return maxSum[0];
	}

	private int maxPathSum(TreeNode root, int[] maxSum) {
		if (root == null) {
			return 0;
		}

		int left = maxPathSum(root.left, maxSum);
		int right = maxPathSum(root.right, maxSum);

		// left < 0, 舍弃left sub tree
		left = left > 0 ? left : 0;
		// right < 0，舍弃right sub tree
		right = right > 0 ? right : 0;

		maxSum[0] = Math.max(maxSum[0], left + right + root.key);
		return Math.max(left, right) + root.key;
	}

}
