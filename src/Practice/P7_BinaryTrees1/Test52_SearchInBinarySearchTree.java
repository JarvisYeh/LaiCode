package Practice.P7_BinaryTrees1;

import util.TreeNode;

public class Test52_SearchInBinarySearchTree {
	// Solution 1: recursion
	public TreeNode searchI(TreeNode root, int key) {
		if (root == null) {
			return null;
		}
		if (root.key < key) {
			return searchI(root.right, key);
		} else if (root.key > key) {
			return searchI(root.left, key);
		} else {
			return root;
		}
	}

	// Solution 2: iteration
	public TreeNode searchII(TreeNode root, int key) {
		while (root != null) {
			if (root.key > key) {
				root = root.left;
			} else if (root.key < key) {
				root = root.right;
			} else {
				return root;
			}
		}
		return root;
	}

}
