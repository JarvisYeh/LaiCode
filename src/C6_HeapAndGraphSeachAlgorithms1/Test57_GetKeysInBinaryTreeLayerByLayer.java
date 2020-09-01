package C6_HeapAndGraphSeachAlgorithms1;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test57_GetKeysInBinaryTreeLayerByLayer {
	/**
	 * After processing one level
	 * The remaining elements in the queue is the next level elements
	 * Therefore record the queue size and poll for size time
	 * @param root
	 * @return
	 */
	public List<List<Integer>> layerByLayer(TreeNode root) {
		// corner case
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		Deque<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		while (queue.size() != 0) {
			int elementAmounts = queue.size();
			List<Integer> list = new ArrayList<>();
			for (int i=0; i<elementAmounts; i++) {
				TreeNode curr = queue.poll();
				list.add(curr.key);
				if (curr.left != null) {
					queue.offer(curr.left);
				}
				if (curr.right != null) {
					queue.offer(curr.right);
				}
			}
			res.add(list);
		}
		return res;
	}

}
