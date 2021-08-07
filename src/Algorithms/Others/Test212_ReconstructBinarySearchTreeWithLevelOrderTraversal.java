package Algorithms.Others;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Test212_ReconstructBinarySearchTreeWithLevelOrderTraversal {
	public TreeNode reconstruct(int[] level) {
		List<Integer> list = new ArrayList<>();
		for (int i: level) {
			list.add(i);
		}
		return helper(list);
	}

	private TreeNode helper(List<Integer> level) {
		// base case
		if (level.size() == 0) return null;
		// current level
		TreeNode root = new TreeNode(level.get(0));
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		for (int i : level) {
			if (i < root.key) {
				left.add(i);
			} else if (i > root.key){
				right.add(i);
			} else {
				continue;
			}
		}
		// recursive rule
		root.left = helper(left);
		root.right = helper(right);
		return root;
	}
}
