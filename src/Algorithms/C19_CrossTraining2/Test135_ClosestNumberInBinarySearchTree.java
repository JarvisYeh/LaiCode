package Algorithms.C19_CrossTraining2;

import util.TreeNode;

public class Test135_ClosestNumberInBinarySearchTree {
	public int closest(TreeNode root, int target) {
		int solution = Integer.MAX_VALUE;
		while (root != null) {
			// update solution if necessary
			if (Math.abs(root.key - target) < Math.abs(solution - target)) {
				solution = root.key;
			}

			// iterate left/right depend on the root value
			if (root.key < target) {
				root = root.right;
			} else if (root.key > target) {
				root = root.left;
			} else {
				solution = root.key;
				break;
			}
		}
		return solution;
	}
}
