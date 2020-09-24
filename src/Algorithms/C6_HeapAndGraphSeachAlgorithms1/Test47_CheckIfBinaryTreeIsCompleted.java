package Algorithms.C6_HeapAndGraphSeachAlgorithms1;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test47_CheckIfBinaryTreeIsCompleted {
	/**
	 * BFS the binary tree
	 * Check a parent node
	 * check left child first
	 * null, set flag to 1
	 * not null
	 * if flag == 1, return false
	 * else continue;
	 * check right child
	 * null, set flag to 1
	 * if flag == 1, return false
	 * else continue;
	 * The next time it meets some parent node whose child is not null
	 * return flase
	 **/
	public boolean isCompleted(TreeNode root) {
		if (root == null) {
			return true;
		}

		int flag = 0;
		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (queue.size() != 0) {
			TreeNode curr = queue.poll();
			if (curr.left != null) {
				if (flag == 1) {
					return false;
				} else {
					queue.offer(curr.left);
				}
			} else {
				flag = 1;
			}
			if (curr.right != null) {
				if (flag == 1) {
					return false;
				} else {
					queue.offer(curr.right);
				}
			} else {
				flag = 1;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{3, null, 2});
		Test47_CheckIfBinaryTreeIsCompleted test = new Test47_CheckIfBinaryTreeIsCompleted();

		System.out.println(test.isCompleted(root));
	}

}
