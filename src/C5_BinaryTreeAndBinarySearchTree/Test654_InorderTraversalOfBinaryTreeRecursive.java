package C5_BinaryTreeAndBinarySearchTree;

import util.TreeNode;

public class Test654_InorderTraversalOfBinaryTreeRecursive {
	public void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		System.out.println(root.key);
		inOrder(root.right);
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[] {3, 4, 5, 6, 7});
		TreeNode.printTree(root);
		Test654_InorderTraversalOfBinaryTreeRecursive test = new Test654_InorderTraversalOfBinaryTreeRecursive();
		test.inOrder(root);
	}
}
