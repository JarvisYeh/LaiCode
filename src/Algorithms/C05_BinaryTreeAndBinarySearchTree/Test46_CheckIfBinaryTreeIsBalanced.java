package Algorithms.C05_BinaryTreeAndBinarySearchTree;

import util.TreeNode;

public class Test46_CheckIfBinaryTreeIsBalanced {
	/**
	 * Solution 1: use getHeight()
	 * Time Complexity: O(n^2)
	 * Space Complexity: O(n)
	 * Balance condition:
	 * 1. left subtree is balanced
	 * 2. right subtree is balanced
	 * 3. difference between height of left subtree and height of right subtree <= 1
	 **/
	public boolean isBalancedI(TreeNode root) {
		if (root == null) {
			return true;
		}

		boolean left = isBalancedI(root.left);
		boolean right = isBalancedI(root.right);
		int diff = Math.abs(getHeight(root.left) - getHeight(root.right));
		return diff <= 1 && left && right;
	}

	private int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return getHeight(root.left) + getHeight(root.right) + 1;
	}

	/**
	 * Solution 2: use getHeight(), 提前减枝
	 * Time Complexity:
	 * 	Worst case → balanced tree
	 *	一共log n层，每层O(n)
	 * 	→ O(nlog n)
	 * Space Complexity: O(n)
	 **/

	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}

		int left = getHeight(root.left);
		int right = getHeight(root.right);

		// 提前减枝
		if (Math.abs(left - right) > 1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
	}

	/**
	 * Solution 3:
	 * if balanced, return height, else return -1
	 * Time Complexity: O(n)
	 * Space Complexity: O(height)
	 * Balance condition:
	 * 1. left subtree is balanced
	 * 	it returns height
	 * 2. right subtree is balanced
	 *	it returns height
	 * 3. difference between height of left subtree and height of right subtree <= 1
	 *	this level returns [max(leftHeight, rightHeight) + 1] or [-1]
	 **/
	public boolean isBalancedIII(TreeNode root) {
		if (helper(root) != -1) {
			return true;
		} else {
			return false;
		}
	}

	private int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = helper(root.left);
		int right = helper(root.right);

		// if left subtree is not balanced
		// or right subtree is not balanced
		// or this root as tree is not balanced
		if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}

		// if this root as tree is balanced, return height itself
		return Math.max(left, right) + 1;
	}


}
