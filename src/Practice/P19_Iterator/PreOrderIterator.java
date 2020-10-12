package Practice.P19_Iterator;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class PreOrderIterator implements Iterator<TreeNode> {
	Deque<TreeNode> stack;
	public PreOrderIterator(TreeNode root) {
		stack = new ArrayDeque<>();
		if (root != null) {
			stack.offerFirst(root);
		}
	}

	@Override
	public boolean hasNext() {
		return stack.size() != 0;
	}

	@Override
	public TreeNode next() {
		if (hasNext()) {
			TreeNode next = stack.pollFirst();
			if (next.right != null) {
				stack.offerFirst(next.right);
			}
			if (next.left != null) {
				stack.offerFirst(next.left);
			}
			return next;
		}
		return null;
	}
}
