package Practice.P8_BinaryTrees2;

import util.TreeNode;

import java.util.*;

public class Test44_PreorderTraversalOfBinaryTreeIterative {
	/**
	 * Time Complexity: O(n)
	 * Space Complexity: O(h)
	 * @param root
	 * @return
	 */
	public List<Integer> preOrder(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		while (stack.size() != 0) {
			TreeNode curr = stack.pollFirst();
			result.add(curr.key);
			if (curr.right != null) {
				stack.offerFirst(curr.right);
			}
			if (curr.left != null) {
				stack.offerFirst(curr.left);
			}

		}
		return result;
	}

	public static void main(String[] args) {
		List<Integer> a = new LinkedList<>();
		a.add(1);
		a.add(2);
		System.out.println(a.toString());
	}
}
