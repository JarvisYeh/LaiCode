package Algorithms.C5_BinaryTreeAndBinarySearchTree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Test55_GetKeysInBinarySearchTreeInGivenRange {
	/**
	 * print all keys in range [min, max]
	 * 剪枝
	 **/
	public List<Integer> getRange(TreeNode root, int min, int max) {
		List<Integer> result = new ArrayList<>();
		getRange(root, min, max, result);
		return result;
	}

	public void getRange(TreeNode root, int min, int max, List<Integer> res) {
		if (root == null) {
			return;
		}

		// if root <= min
		// root.left < root <= min, no need to traversal root.left
		if (root.key > min) {
			getRange(root.left, min, max, res);
		}

		if (root.key >= min && root.key <= max) {
			res.add(root.key);
		}

		// if root >= max
		// root.right > root >= max, no need to traversal root.right
		if (root.key < max) {
			getRange(root.right, min, max, res);
		}
	}
}
