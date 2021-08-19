package Algorithms.Others.BST;

import util.TreeNode;

public class Test347_SecondLargestInBinarySearchTree {
	// transfer the find second-largest problems into find the largest problem
	// TC: O(height)
	// SC: O(height)
	public int secondLargest(TreeNode root) {
		if (root == null) return Integer.MIN_VALUE;

		// case 1: target node in left subtree
		if (root.right == null) {
			TreeNode target = findLargest(root.left);
			return target == null ? Integer.MIN_VALUE : target.key;
		}

		// case 2: target node is root
		// right subtree only 1 nodes,
		if (root.right.left == null && root.right.right == null) {
			return root.key;
		}

		// case 3: target node in right subtree
		return secondLargest(root.right);
	}


	private TreeNode findLargest(TreeNode root) {
		if (root == null) return null;
		while (root.right != null) {
			root = root.right;
		}
		return root;
	}
}
