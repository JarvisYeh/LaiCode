package Practice.P08_BinaryTrees2;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test43_InorderTraversalOfBinaryTreeIterative {
	public List<Integer> inOrder(TreeNode root) {
		List<Integer> result = new ArrayList<>();

		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode curr = root;

		while (stack.size() != 0 || curr != null) {
			if (curr != null) {
				stack.offerFirst(curr);
				curr = curr.left;
			} else {
				TreeNode tmp = stack.pollFirst();
				result.add(tmp.key);
				curr = tmp.right;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Test43_InorderTraversalOfBinaryTreeIterative test = new Test43_InorderTraversalOfBinaryTreeIterative();
		TreeNode root = TreeNode.formTreeByLayer(new Integer[] {12,3,7,4,14} );
		System.out.println(test.inOrder(root).toString());
	}

}
