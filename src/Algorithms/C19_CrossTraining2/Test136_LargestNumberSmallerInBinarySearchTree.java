package Algorithms.C19_CrossTraining2;

import util.TreeNode;

public class Test136_LargestNumberSmallerInBinarySearchTree {
	public int largestSmaller(TreeNode root, int target) {
		int solution = Integer.MIN_VALUE;
		while (root != null) {
			if (root.key >= target) {
				root = root.left;
			} else if (root.key < target) {
				solution = root.key;
				root = root.right;
			}
		}
		return solution;
	}
}
