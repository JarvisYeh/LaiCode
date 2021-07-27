package Algorithms.C20_Recursion3;

import util.TreeNode;

import java.util.HashMap;

public class Test213_ReconstructBinaryTreeWithPreorderAndInorder {
	/**
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 * @param inOrder
	 * @param preOrder
	 * @return
	 */
	public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
		if (inOrder == null || preOrder == null || inOrder.length == 0 || preOrder.length == 0) {
			return null;
		}

		HashMap<Integer, Integer> indexMap = new HashMap<>();
		// index Map stores <value, index>
		for (int i = 0; i < inOrder.length; i++) {
			indexMap.put(inOrder[i], i);
		}
		return reconstruct(indexMap, 0, inOrder.length - 1, preOrder, 0, preOrder.length - 1);
	}

	private TreeNode reconstruct(HashMap<Integer, Integer> indexMap, int inLeft, int inRight,
								 int[] preOrder, int preLeft, int preRight) {
		// base case
		if (inLeft > inRight) {
			return null;
		}

		// current node
		TreeNode root = new TreeNode(preOrder[preLeft]);
		int rootIndex = indexMap.get(root.key);
		int leftSize = rootIndex - inLeft;

		// recursive call
		root.left = reconstruct(indexMap, inLeft, inLeft + leftSize - 1,
									preOrder, preLeft + 1, preLeft + leftSize);
		root.right = reconstruct(indexMap, inLeft + leftSize + 1, inRight,
									preOrder, preLeft + leftSize + 1, preRight);
		return root;
	}
}
