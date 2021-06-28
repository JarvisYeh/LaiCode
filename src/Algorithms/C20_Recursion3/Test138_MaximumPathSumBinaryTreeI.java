package Algorithms.C20_Recursion3;

import util.TreeNode;

// max sum of path from one leaf to another leaf
public class Test138_MaximumPathSumBinaryTreeI {
	public int maxPathSum(TreeNode root) {
		int[] maxRes = new int[1];
		maxRes[0] = Integer.MIN_VALUE;
		helper(root, maxRes);
		return maxRes[0];
	}

	private int helper(TreeNode root, int[] maxRes) {
		if (root == null) {
			return 0;
		}

		int left = helper(root.left, maxRes);
		int right = helper(root.right, maxRes);
		if (root.left != null && root.right != null) {
			maxRes[0] = Math.max(maxRes[0], left + right + root.key);
		}

		if (root.left == null) {
			return right + root.key;
		}

		if (root.right == null) {
			return left + root.key;
		}

		return Math.max(left, right) + root.key;
	}
}
