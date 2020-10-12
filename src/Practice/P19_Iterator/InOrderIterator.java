package Practice.P19_Iterator;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class InOrderIterator implements Iterator<TreeNode> {
	Deque<TreeNode> stack;
	TreeNode curr;
	public InOrderIterator(TreeNode root) {
		stack = new ArrayDeque<>();
		curr = root;
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty() || curr != null;
	}

	@Override
	public TreeNode next() {
		while (hasNext()) {
			if (curr != null) {
				stack.offerFirst(curr);
				curr = curr.left;
			} else {
				TreeNode next = stack.pollFirst();
				curr = next.right;
				return next;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.formTreeByLayer(new Integer[]{1, 2, 3, 4, 5, null, 8, 9, 10, null, null, null, null, 11, null, null, null});
		InOrderIterator it = new InOrderIterator(root);
		while (it.hasNext()) {
			System.out.println(it.next().key);
		}
	}
}
