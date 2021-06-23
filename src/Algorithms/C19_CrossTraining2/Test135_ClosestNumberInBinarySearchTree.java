package Algorithms.C19_CrossTraining2;

import util.TreeNode;

public class Test135_ClosestNumberInBinarySearchTree {
	// iteration
	public int closestI(TreeNode root, int target) {
		int solution = root.key;
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
				return root.key;
			}
		}
		return solution;
	}

	// recursion
	// recursion function returns the value of node that is closest to target in subtree with root = root
	public int closestII(TreeNode root, int target) {
		if (root == null) return Integer.MAX_VALUE;

		// ask for left and right subtree for their closest result
		// note for prune here
		// if target is already smaller than root, no need to check right subtree
		// vice versa for left subtree
		int left = root.key;
		int right = root.key;
		if (root.key > target) {
			right = closestII(root.left, target);
		}
		if (root.key < target) {
			left = closestII(root.right, target);
		}

		// return left, right, root.val中最接近target的值
		int candidate = right;
		if (Math.abs(left - target) < Math.abs(right - target)) {
			candidate = left;
		}
		if (Math.abs(root.key - target) < Math.abs(candidate - target)) {
			candidate = root.key;
		}
		return candidate;
	}
}
