package Algorithms.C05_BinaryTreeAndBinarySearchTree;

import util.TreeNode;

public class Test54_IsBinarySearchTreeOrNot {
	/**
	 * Assume no duplicate elements
	 * Solution 1:
	 * in-order traversal the binary tree
	 * record last seen by storing that value in an array and passing that array during recursion
	 * returns false if this level value is smaller than last seen value
	 **/
	public boolean isBSTI(TreeNode root) {
		int[] lastSeen = new int[1];
		lastSeen[0] = Integer.MIN_VALUE;
		return isBST(root, lastSeen);
	}

	private boolean isBST(TreeNode root, int[] lastSeen) {
		if (root == null) {
			return true;
		}

		// in-order traversal
		boolean left = isBST(root.left, lastSeen);
		if (root.key <= lastSeen[0]) {
			return false;
		}
		// update last seen in this level
		lastSeen[0] = root.key;
		boolean right = isBST(root.right, lastSeen);

		return left && right;
	}

	/**
	 * Solution 2:
	 * value pass from top to bottom
	 * pass appropriate range to children
	 * isBST condition:
	 * 1. left subTree is BST
	 * 2. right subTree is BST
	 * 3. current level value is in the appropriate range (min, max)
	 **/
	public boolean isBSTII(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}

		// if current level value is in appropriate range
		if (root.key < min || root.key > max) {
			return false;
		}

		// if left and right subTree ar BST
		return isBST(root.left, min, root.key) &&
				isBST(root.right, root.key, max);
	}



	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{6, 4, 10, 2, 5, 6, 12});
		TreeNode.printTree(root);
		Test54_IsBinarySearchTreeOrNot test = new Test54_IsBinarySearchTreeOrNot();
		System.out.println(test.isBSTII(root));
	}

}
