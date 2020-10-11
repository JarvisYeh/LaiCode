package Algorithms.C17_CrossTraining1;

import util.TreeNode;

import java.util.List;

public class Test129_LowestCommonAncestorIV {
	// LCA of K nodes
	// 与两个node的LCA完全相同
	public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
		if (root == null || nodes.contains(root)) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, nodes);
		TreeNode right = lowestCommonAncestor(root.right, nodes);

		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}

}
