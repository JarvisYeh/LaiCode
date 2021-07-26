package Algorithms.C05_BinaryTreeAndBinarySearchTree;

import util.TreeNode;

public class Test656_PostorderTraversalOfBinaryTreeRecursive {
	public void postOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.println(root.key);
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[] {3, 4, 5, 6, 7});
		TreeNode.printTree(root);
		Test656_PostorderTraversalOfBinaryTreeRecursive test = new Test656_PostorderTraversalOfBinaryTreeRecursive();
		test.postOrder(root);
	}
}
