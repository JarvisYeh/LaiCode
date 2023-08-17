package Algorithms.C06_HeapAndGraphSeachAlgorithms1;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Test566_PrintTreeInLevelOrder {
	private TreeNode curr;

	public List<Integer> bfs(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Queue<TreeNode> q = new ArrayDeque<>();
		q.offer(root);
		while (!q.isEmpty()) {
			TreeNode curr = q.poll();
			res.add(curr.key);
			if (curr.left != null) q.offer(curr.left);
			if (curr.right != null) q.offer(curr.right);

		}
		return res;
	}
}
