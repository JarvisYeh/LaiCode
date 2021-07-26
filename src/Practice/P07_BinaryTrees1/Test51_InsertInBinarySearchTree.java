package Practice.P07_BinaryTrees1;

import util.TreeNode;

public class Test51_InsertInBinarySearchTree {
	// Solution 1: recursion
	// if tree already has that key, do nothing
	// always return the root of the new tree
	public TreeNode insertI(TreeNode root, int key) {
		if (root == null) {
			return new TreeNode(key);
		}

		if (root.key < key) {
			root.right = insertI(root.right, key);
		} else if (root.key > key) {
			root.left = insertI(root.left, key);
		}
		return root;
	}

	// Solution 2: iteration
	// if tree already has that key, do nothing
	// always return the root of the new tree
	public TreeNode insertII(TreeNode root, int key) {
		if (root == null) {
			return new TreeNode(key);
		}

		TreeNode curr = root;
		TreeNode prev = null;
		while (curr != null) {
			prev = curr;
			if (curr.key < key) {
				curr = curr.right;
			} else if (curr.key > key) {
				curr = curr.left;
			} else {
				return root;
			}
		}
		if (prev.key < key) {
			prev.right = new TreeNode(key);
		} else {
			prev.left = new TreeNode(key);
		}
		return root;
	}

}
