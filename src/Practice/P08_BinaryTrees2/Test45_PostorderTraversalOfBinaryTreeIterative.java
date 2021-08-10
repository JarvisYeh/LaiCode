package Practice.P08_BinaryTrees2;

import util.TreeNode;

import java.util.*;

public class Test45_PostorderTraversalOfBinaryTreeIterative {
	/**
	 * Method 1: 后续遍历结果reverse一下，左→右→根 => 根→右→左
	 * 即为preOrder稍微修改
	 **/
	public List<Integer> postOrderI(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		while (stack.size() != 0) {
			TreeNode curr = stack.pollFirst();
			result.add(curr.key);
			if (curr.left != null) {
				stack.offerFirst(curr.left);
			}
			if (curr.right != null) {
				stack.offerFirst(curr.right);
			}
		}
		Collections.reverse(result);
		return result;
	}

	/**
	 * Method 2:
	 * 使用一个prev和curr
	 * 当prev是curr的parent时候，表明在向下遍历
	 * 	有左子树向左子树遍历，没有左子树向右子树遍历，都没有表明是leaf node，pop打印
	 * 当prev是curr的left child的时候，表明左子树遍历完毕
	 * 	如还有右子树则去遍历右子树，没有则直接打印
	 * 当prev是curr的right child的时候，表明右子树也遍历完毕
	 * 	左右子树都遍历完毕直接打印
	 *
	 **/
	public List<Integer> postOrderII(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		List<Integer> result = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();

		stack.offerFirst(root);
		TreeNode prev = null;
		while (stack.size() != 0) {
			TreeNode curr = stack.peekFirst();

			// if curr is root or curr is the children of prev
			if (prev == null || curr == prev.left || curr == prev.right) {
				// if curr has left child, route left
				if (curr.left != null) {
					stack.offerFirst(curr.left);
					// if curr only has right child, route right
				} else if (curr.right != null) {
					stack.offerFirst(curr.right);
					// if curr is the leaf node, pop this node
				} else {
					result.add(stack.pollFirst().key);
				}

			// if prev is the left child of curr
			// meaning the left subtree is already traversed
			} else if (prev == curr.left) {
				// if curr has right child to be routed, route it first
				if (curr.right != null) {
					stack.offerFirst(curr.right);
					// if curr’s left subtree is being traversed and does not have right subtree, pop this node
				} else {
					result.add(stack.pollFirst().key);
				}

			// if prev is the right child of curr
			} else {
				result.add(stack.pollFirst().key);
			}
			prev = curr;
		}
		return result;
	}

	/**
	 * Method 3: concise iteration method
	 */
	public List<Integer> postOrderIII(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Deque<TreeNode> s = new ArrayDeque<>();
		TreeNode prev = null, curr = root;
		while (curr != null || !s.isEmpty()) {
			// push all left nodes first
			// since first traverse left nodes
			while (curr != null) {
				s.offerFirst(curr);
				curr = curr.left;
			}

			// the stack top is the node without left child || left subtree are traversed
			// but still not sure whether its right subtree are traversed
			curr = s.peekFirst();
			// if that curr node doesn't have right child
			// or it's previous traversed node is its right child
			// meaning all its left and right nodes are traversed
			// now traverse this node
			if (curr.right == null || prev == curr.right) {
				s.pollFirst();
				res.add(curr.key);
				prev = curr;
				curr = null;
			} else {
				curr = curr.right;
			}
		}
		return res;
	}


}
