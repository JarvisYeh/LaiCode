package Algorithms.C17_CrossTraining1;

import util.TreeNode;

public class Test368_LowestCommonAncestorBinarySearchTreeI {
	public TreeNode lca(TreeNode root, int p, int q) {
		int small = Math.min(p, q);
		int large = Math.max(p, q);
		while (root != null) {
			if (root.key < small) {
				root = root.left;
			} else if (root.key > large) {
				root = root.right;
			} else {	//  small <= root.key <= large
				return root;
			}
		}
		return null;
	}
}
