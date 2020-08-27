package C5_BinaryTreeAndBinarySearchTree;

import util.TreeNode;

public class Test48_SymmetricBinaryTree {
	/**
	 * Time Complexity:
	 * 	Tree中的2个node变为recursion tree中的个node
	 * 	O(n/2) = O(n)
	 * Space Complexity: O(height)
	 **/
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isSymmetric(root.left, root.right);
	}

	private boolean isSymmetric(TreeNode left, TreeNode right) {
		// base case:
		// 1. both null, return true
		if (left == null && right == null) {
			return true;
		}
		// 2. one null, return false
		if (left == null || right == null) {
			return false;
		}

		// this level
		if (left.key != right.key) {
			return false;
		}

		// return to parent
		return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
	}

}
