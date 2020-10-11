package Algorithms.C17_CrossTraining1;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test58_GetKeysInBinaryTreeLayerByLayerZigZagOrder {
	public List<Integer> zigZag(TreeNode root) {
		// corner case
		if (root == null) {
			return new ArrayList<>();
		}
		List<Integer> res = new ArrayList<>();

		Deque<TreeNode> deque = new ArrayDeque<>();
		deque.offerFirst(root);
		while (deque.size() != 0) {
			int size = deque.size();
			for (int i = 0; i < size; i++) {
				TreeNode tmp = deque.pollLast();
				res.add(tmp.key);
				if (tmp.right != null) {
					deque.offerFirst(tmp.right);
				}
				if (tmp.left != null) {
					deque.offerFirst(tmp.left);
				}
			}

			size = deque.size();
			for (int i = 0; i < size; i++) {
				TreeNode tmp = deque.pollFirst();
				res.add(tmp.key);
				if (tmp.left != null) {
					deque.offerLast(tmp.left);
				}
				if (tmp.right != null) {
					deque.offerLast(tmp.right);
				}
			}
		}
		return res;
	}

}
