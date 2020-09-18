package C11_Recursion2;

import util.TreeNode;

public class Test178_ReverseBinaryTreeUpsideDown {
	public TreeNode reverse(TreeNode root) {
		// base case: left most node, newRoot
		// corner case: root is null
		if (root == null || root.left == null) {
			return root;
		}

		// obtain the newRoot of the whole tree
		TreeNode newRoot = reverse(root.left);

		// reverse current level
		root.left.left = root.right;
		root.left.right = root;
		root.left = null;
		root.right = null;

		// return newRoot of the whole tree
		return newRoot;
	}

}
