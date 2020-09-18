package C11_Recursion2;

import util.TreeNode;

public class Test_MaxDifferenceOfLeftAndRightSubtree {
	/**
	 * Find the node of the tree which has the max difference of left subtree and right subtree
	 * @param root
	 * @return
	 */
	public TreeNode maxDiff(TreeNode root) {
		TreeNode[] solution = new TreeNode[1];
		maxDiff(root, new int[]{0}, solution);
		return solution[0];
	}

	private int maxDiff(TreeNode root, int[] globalMax, TreeNode[] solution) {
		// base case
		if (root == null) {
			return 0;
		}

		// step 1: ask for children
		int left = maxDiff(root.left, globalMax, solution);
		int right = maxDiff(root.right, globalMax, solution);

		// step 3: operation in current layer
		// compare current diff with diff_max, to determine whether to alter solution node
		if (Math.abs(left - right) > globalMax[0]) {
			globalMax[0] = Math.abs(left - right);
			solution[0] = root;
		}

		// step 2: return to parents
		return left + right + 1;
	}

}
