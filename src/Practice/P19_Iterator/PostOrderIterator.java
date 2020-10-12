package Practice.P19_Iterator;

import apple.laf.JRSUIUtils;
import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class PostOrderIterator implements Iterator<TreeNode> {
	TreeNode prev;
	Deque<TreeNode> stack;
	PostOrderIterator(TreeNode root) {
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
		while (hasNext()) {
			TreeNode curr = stack.peekFirst();
			if (prev == null || prev.left == curr || prev.right == curr) {
				if (curr.left != null) {
					stack.offerFirst(curr.left);
				} else if (curr.right != null) {
					stack.offerFirst(curr.right);
				} else {
					stack.pollFirst();
					prev = curr;
					return curr;
				}
			} else if (curr.left == prev) {
				if (curr.right == null) {
					stack.pollFirst();
					prev = curr;
					return curr;
				} else {
					stack.offerFirst(curr.right);
					prev = curr;
				}
			} else {
				stack.pollFirst();
				prev = curr;
				return curr;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{1, 2, 3, 4, 5, null, 8, 9, 10, null, null, null, null, 11, null, null, null});
		PostOrderIterator it = new PostOrderIterator(root);
		while (it.hasNext()) {
			System.out.println(it.next().key);
		}
	}
}
