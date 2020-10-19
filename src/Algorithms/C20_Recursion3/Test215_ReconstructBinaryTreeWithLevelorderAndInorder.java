package Algorithms.C20_Recursion3;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test215_ReconstructBinaryTreeWithLevelorderAndInorder {
	public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
		HashMap<Integer, Integer> indexMap = new HashMap<>();
		List<Integer> level = new ArrayList<>();
		for (int i = 0; i < inOrder.length; i++) {
			indexMap.put(inOrder[i], i);
		}
		for (int i : levelOrder) {
			level.add(i);
		}
		return reconstruct(level, indexMap);
	}

	private TreeNode reconstruct(List<Integer> level, HashMap<Integer, Integer> indexMap) {
		if (level.size() == 0) {
			return null;
		}

		TreeNode root = new TreeNode(level.get(0));
		int rootIndex = indexMap.get(root.key);
		List<Integer> levelLeft = new ArrayList<>();
		List<Integer> levelRight = new ArrayList<>();

		for (int i = 0; i < level.size(); i++) {
			int curr = level.get(i);
			// index less than root index in inOrder, meaning belong to left subtree
			if (indexMap.get(curr) < rootIndex) {
				levelLeft.add(curr);
			}
			// index larger than root index in inOrder, meaning belong to right subtree
			else if (indexMap.get(curr) > rootIndex) {
				 levelRight.add(curr);
			}
		}
		root.left = reconstruct(levelLeft, indexMap);
		root.right = reconstruct(levelRight, indexMap);
		return root;
	}
}
