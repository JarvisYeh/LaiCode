package Practice.P8_BinaryTrees2;

import util.TreeNode;

public class Test53_DeleteInBinarySearchTree {
	/**
	 * Case 1: the target node has no children
	 * Case 2: the target node only has left child node
	 * Case 3: the target node only has right child node
	 * Case 4: the target node has both left and right children
	 * Then we need to find the smallest node among the nodes which are larget than the target (Among right subtree)
	 * Case 4.1 target.right has no left child.
	 * Case 4.2 target.right has left child. Find the most left one.
	 **/
	// Recursion
	public TreeNode deleteTreeI(TreeNode root, int target) {
		// base case
		if (root == null) {
			return null;
		}

		// keep searching for target
		if (root.key < target) {
			root.right = deleteTreeI(root.right, target);
			return root;
		} else if (root.key > target) {
			root.left = deleteTreeI(root.left, target);
			return root;
		}

		// target found, root.key == target
		// case 1:
		if (root.left == null && root.right == null) {
			return null;
			// case 2:
		} else if (root.left == null) {
			return root.right;
			// case 3:
		} else if (root.right == null) {
			return root.left;
			// case 4:
		} else {
			// case 4.1
			if (root.right.left == null) {
				root.right.left = root.left;
				return root.right;
			} else {
				TreeNode targetNode = root;
				TreeNode curr = root.right.left;
				TreeNode prev = root.right;
				while (curr.left != null) {
					prev = curr;
					curr = curr.left;
				}
				prev.left = curr.right;
				curr.left = targetNode.left;
				curr.right = targetNode.right;
				return curr;
			}
		}
	}

	// Iteration
	public TreeNode deleteTreeII(TreeNode root, int target) {
		// corner case
		if (root == null) {
			return null;
		}

		TreeNode prev = null;
		TreeNode curr = root;
		TreeNode targetNode = null;
		// keep searching for target
		while (curr != null) {
			if (curr.key < target) {
				prev = curr;
				curr = curr.right;
			} else if (curr.key > target) {
				prev = curr;
				curr = curr.left;
			} else {
				targetNode = curr;
				break;
			}
		}

		TreeNode nodeToReplaceTarget;
		// target node not found
		if (targetNode == null) {
			return root;
		}
		// target node found
		// case 1, case 3:
		if (targetNode.left == null) {
			nodeToReplaceTarget = targetNode.right;
			// case 2:
		} else if (targetNode.right == null) {
			nodeToReplaceTarget = targetNode.left;
			// case 4:
		} else {
			// case 4.1
			if (targetNode.right.left == null) {
				targetNode.right.left = targetNode.left;
				nodeToReplaceTarget = targetNode.right;
			} else {
				TreeNode curr2 = targetNode.right.left;
				TreeNode prev2 = targetNode.right;
				while (curr2.left != null) {
					prev2 = curr2;
					curr2 = curr2.left;
				}
				prev2.left = curr2.right;
				curr2.left = targetNode.left;
				curr2.right = targetNode.right;
				nodeToReplaceTarget = curr2;
			}
		}

		if (prev == null) {
			return nodeToReplaceTarget;
		} else if (prev.left == targetNode) {
			prev.left = nodeToReplaceTarget;
		} else if (prev.right == targetNode) {
			prev.right = nodeToReplaceTarget;
		}
		return root;
	}

	public static void main(String[] args) {
		Test53_DeleteInBinarySearchTree test = new Test53_DeleteInBinarySearchTree();
		TreeNode root = TreeNode.formTreeByLayer(new Integer[] {2,1,3});
		TreeNode.printTree(root);
		TreeNode.printTree(test.deleteTreeII(root, 1));
	}
}