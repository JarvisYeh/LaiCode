package Algorithms.C5_BinaryTreeAndBinarySearchTree;

import util.TreeNode;

public class Test60_HeightofBinaryTree {
	/**
	 * parent 向两边的子节点索要高度
	 * 并向parent返回自己作为root的高度
	 **/
	public int findHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = findHeight(root.left);
		int right = findHeight(root.right);
		return Math.max(left, right) + 1;
	}

}
