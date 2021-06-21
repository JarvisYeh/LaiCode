package Practice.P19_Iterator;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class InOrderIterator implements Iterator<TreeNode> {
	Deque<TreeNode> stack;

	// push all left path until null
	public InOrderIterator(TreeNode root) {
		stack = new ArrayDeque<>();
		while (root != null) {
			stack.offerFirst(root);
			root = root.left;
		}
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public TreeNode next() {
		if (hasNext()) {
			TreeNode curr = stack.pollFirst();
			if (curr.right != null) {
				TreeNode tmp = curr.right;
				while (tmp != null) {
					stack.offerFirst(tmp);
					tmp = tmp.left;
				}
			}
			return curr;
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
