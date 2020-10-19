package Algorithms.C20_Recursion3;

import util.TreeNode;

public class Test523_FlattenBinaryTreetoLinkedList {
	/**
	 * flatten a tree into tree linked list with only right child
	 * with preOrder traversal
	 * @param root
	 * @return
	 */
	public TreeNode flatten(TreeNode root) {
		TreeNode[] prev = new TreeNode[1];
		helper(root, prev);
		// root不变
		return root;
	}

	private void helper(TreeNode root, TreeNode[] prev) {
		if (root == null) {
			return;
		}

		// 记录root.left和root.right
		TreeNode left = root.left;
		TreeNode right = root.right;

		// 连接root和prev[0]，使root.left = null
		// 并更新prev[0]
		if (prev[0] != null) {
			prev[0].right = root;
		}
		root.left = null;
		prev[0] = root;

		// recursive call，因为之前保存了root.left，和root.right，在这里使用保存的reference
		helper(left, prev);
		helper(right, prev);
	}


	/**
	 * flatten a tree into tree linked list with only right child
	 * with inOrder traversal
	 */
	public TreeNode flattenIntoInorder(TreeNode root) {
		TreeNode[] prevAndNewRoot = new TreeNode[2];
		inOrder(root, prevAndNewRoot);
		return prevAndNewRoot[1];
	}

	private void inOrder(TreeNode root, TreeNode[] prevAndNewRoot) {
		if (root == null) {
			return;
		}

		inOrder(root.left, prevAndNewRoot);

		if (prevAndNewRoot[0] == null) {
			prevAndNewRoot[1] = root;
		} else {
			prevAndNewRoot[0].right = root;
		}
		root.left = null;
		prevAndNewRoot[0] = root;
		inOrder(root.right, prevAndNewRoot);
	}

	public static void main(String[] args) {
		Test523_FlattenBinaryTreetoLinkedList test = new Test523_FlattenBinaryTreetoLinkedList();
		TreeNode res = test.flattenIntoInorder(TreeNode.formTreeByLayer(new Integer[]{1, 2, 3, 4}));
		TreeNode.printTree(res);
	}
}
