package Algorithms.C17_CrossTraining1;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test504_ClosestNumberInBinarySearchTreeII {
	/**
	 * 固定size为K的queue
	 * in-order traversal of the BST
	 * 如果traverse的元素比peek的元素离target更近，poll and offer
	 * 如果traverse的元素比peek的元素离target更远，停止
	 **/
	public int[] closestKValues(TreeNode root, double target, int k) {
		Queue<Integer> queue = new ArrayDeque<>();

		inOrder(root, target, queue, k);

		int[] res = new int[queue.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = queue.poll();
		}
		return res;
	}

	private void inOrder(TreeNode root, double target, Queue<Integer> queue, int k) {
		// base case
		if (root == null) {
			return;
		}

		inOrder(root.left, target, queue, k);

		if (queue.size() < k) {
			queue.offer(root.key);
		}

		if (queue.size() == k) {
			if (Math.abs(root.key - target) < Math.abs(queue.peek() - target)) {
				queue.poll();
				queue.offer(root.key);
			} else {
				// early return;
				return;
			}
		}
		inOrder(root.right, target, queue, k);
	}

}
