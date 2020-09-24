package Algorithms.C11_Recursion2;

import util.TreeNode;

public class Test126_LowestCommonAncestorI {
	/**
	 * Main method to include the condition that a and b are not in the same tree
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	public TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
		TreeNode resNode = lowestCommonAncestor(root, a, b);

		// Case 1: 返回的是null，即a和b都不在root tree上
		if (resNode == null) {
			return null;
		// Case 2: 返回的是c节点，即为LCA(a, b)
		} else if (resNode != a && resNode != b){
			return resNode;
		}

		// Case 3: 返回a或b，无法确定a，b都在root tree上，进一步判断
		if (resNode == a) {
			// 在a为root的subtree上找b，找到则说明a确实是LCA
			return lowestCommonAncestor(a, b, b) == null ? null : a;
		} else {
			// 在b为root的subtree上找a，找到则说明确实是LCA
			return lowestCommonAncestor(b, a, a) == null ? null : b;
		}

	}


	/**
	 * base case
	 * Case 1: leaf node，返回null
	 * Case 2: 找到a或b，返回找到的a或b
	 *
	 * recursive rule
	 * Case 1: 左右返回的都是null，返回null
	 * Case 2: 左右返回的都不是null，返回current节点，即为target LCA
	 * Case 3: 左右返回的只有一个不是null， 返回该non-null节点
	 **/
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
		// base case
		if (root == null || root == a || root == b) {
			return root;
		}

		// ask for children
		TreeNode left = lowestCommonAncestor(root.left, a, b);
		TreeNode right = lowestCommonAncestor(root.right, a, b);

		// return to parent
		if (left == null && right == null) {
			return null;
		} else if (left != null && right != null) {
			return root;
		} else {
			return left == null ? right : left;
		}
	}

}
