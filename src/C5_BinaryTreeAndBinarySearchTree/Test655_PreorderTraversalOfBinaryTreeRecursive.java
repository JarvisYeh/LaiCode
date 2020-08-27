package C5_BinaryTreeAndBinarySearchTree;

import C3_LinkedList.Test653_ReverseLinkedList_Recursive;
import util.TreeNode;

public class Test655_PreorderTraversalOfBinaryTreeRecursive {
	public void preOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.println(root.key);
		preOrder(root.left);
		preOrder(root.right);
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[] {3, 4, 5, 6, 7});
		TreeNode.printTree(root);
		Test655_PreorderTraversalOfBinaryTreeRecursive test = new Test655_PreorderTraversalOfBinaryTreeRecursive();
		test.preOrder(root);
	}
}
