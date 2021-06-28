package Algorithms.C20_Recursion3;

import util.TreeNode;

// max sum of path from root to leaf

public class Test639_MaxPathSumFromLeafToRoot {
	// method 1: 从下往上传值
	public int maxPathSumLeafToRootI(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = maxPathSumLeafToRootI(root.left);
		int right = maxPathSumLeafToRootI(root.right);
		if (root.left == null) {
			return right + root.key;
		}
		if (root.right == null) {
			return left + root.key;
		}
		return Math.max(left, right) + root.key;
	}

	// method 2: 从上往下传值
	public int maxPathSumLeafToRootII(TreeNode root) {
		int prefixSum = 0;
		int[] maxSum = new int[1];
		maxSum[0] = Integer.MIN_VALUE;
		maxPathSumLeafToRootII(root, prefixSum, maxSum);
		return maxSum[0];
	}
	private void maxPathSumLeafToRootII(TreeNode root, int prefixSum, int[] maxSum) {
		if (root == null) {
			return;
		}

		// update maxSum at leaf node
		if (root.left == null && root.right == null) {
			maxSum[0] = Math.max(maxSum[0], prefixSum + root.key);
			return;
		}

		// pass the prefixSum to lower level
		maxPathSumLeafToRootII(root.left, prefixSum + root.key, maxSum);
		maxPathSumLeafToRootII(root.right, prefixSum + root.key, maxSum);
	}


}
