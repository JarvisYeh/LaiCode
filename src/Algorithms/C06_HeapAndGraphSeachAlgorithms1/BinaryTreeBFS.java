package Algorithms.C06_HeapAndGraphSeachAlgorithms1;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinaryTreeBFS {
	public void BFSprintAll(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (queue.size() != 0) {
			TreeNode curr = queue.poll();
			System.out.println(curr.key + " ");
			if (curr.left != null) {
				queue.offer(curr.left);
			}
			if (curr.right != null){
				queue.offer(curr.right);
			}
		}
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.formCompleteTreeInOrder(new Integer[]{1,2,3,4});
		BinaryTreeBFS test = new BinaryTreeBFS();
		test.BFSprintAll(root);
	}
}
