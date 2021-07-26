package Algorithms.C06_HeapAndGraphSeachAlgorithms1;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
	 * return false
	 **/
	public boolean isCompletedI(TreeNode root) {
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

	// level order traversal
	// first met null, can not meet null again
	// need to use linkedList as queue
	public boolean isCompletedII(TreeNode root) {
		if (root == null) return true;
		boolean flag = false;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (queue.peek() != null) {
			TreeNode curr = queue.poll();
			queue.offer(curr.left);
			queue.offer(curr.right);
		}
		// found the first null
		// if the leftover in queue are all null, then it's complete tree
		while (!queue.isEmpty()) {
			if (queue.poll() != null) {
				return false;
			}
		}
		return true;
	}


	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{3, null, 2});
		Test47_CheckIfBinaryTreeIsCompleted test = new Test47_CheckIfBinaryTreeIsCompleted();

		System.out.println(test.isCompletedII(root));
	}

}
