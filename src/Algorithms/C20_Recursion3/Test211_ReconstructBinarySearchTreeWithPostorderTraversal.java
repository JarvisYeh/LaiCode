package Algorithms.C20_Recursion3;

import com.sun.source.tree.Tree;
import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

public class Test211_ReconstructBinarySearchTreeWithPostorderTraversal {
	/**
	 * Method 1:
	 * Sort post order traversal to get inOrder traversal
	 * Using post-order and in-order traversal to reconstruct the tree
	 */
	public TreeNode reconstruct(int[] post) {
		if (post == null || post.length == 0) {
			return null;
		}

		int[] in = post.clone();
		Arrays.sort(in);

		HashMap<Integer, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			indexMap.put(in[i], i);
		}
		return reconstruct(in, 0, in.length - 1, post, 0, post.length - 1, indexMap);
	}

	private TreeNode reconstruct(int[] in, int inLeft, int inRight,
								 int[] post, int postLeft, int postRight,
								 HashMap<Integer, Integer> indexMap) {
		// base case
		if (inLeft > inRight) {
			return null;
		}

		TreeNode root = new TreeNode(post[postRight]);
		int rootIndex = indexMap.get(root.key);
		int leftSize = rootIndex - inLeft;
		root.left = reconstruct(in, inLeft, inLeft + leftSize - 1,
								post, postLeft, postLeft + leftSize - 1,
								indexMap);
		root.right = reconstruct(in, inLeft + leftSize + 1, inRight,
								post, postLeft + leftSize, postRight - 1,
								indexMap);
		return root;
	}

	/**
	 * Method 2:
	 * 利用BST性质
	 * 从post order的末尾开始，每次recursive处理index--的元素
	 * 先构建root.right,在构建root.left
	 * 当前层向右边传入"自己"作为min，如果下层小于该min，说明接下来的index上的值都不属于右子树了，return null
	 * 当前层向左边传入"自己的min"作为min
	 */
	public TreeNode reconstructII(int[] post) {
		if (post == null || post.length == 0) {
			return null;
		}
		int[] index = new int[1];
		index[0] = post.length - 1;
		return reconstructII(post, index, Integer.MIN_VALUE);
	}

	private TreeNode reconstructII(int[] post, int[] index, int min) {
		// all elements in post order is being traversed
		// or the value is less than the supposed min, which means it should not belong to the right subtree
		if (index[0] < 0 || post[index[0]] < min) {
			return null;
		}

		TreeNode root = new TreeNode(post[index[0]]);
		index[0]--;
		root.right = reconstructII(post, index, root.key);
		root.left = reconstructII(post, index, min);
		return root;
	}

	/** Method 3:
	 * simulate method 2 using stack
	 * improve space complexity to O(1)
	 * TC: O(n)
	 * SC: O(1)
	 * post = [4 11 10 9]
	 * 9
	 *  \
	 *  10
	 *    \
	 *    11
	 * when 4 comes, need to determined whether it's 11/10/9's left child
	 */
	public TreeNode reconstructIII(int[] post) {
		if (post == null || post.length == 0) return null;

		// stack stores those left or right child is not determined yet
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode root = new TreeNode(post[post.length - 1]);
		stack.offerFirst(root);
		// start from second last element, since last one is already pushed into stack
		for (int i = post.length - 2; i >= 0; i--) {
			TreeNode curr = new TreeNode(post[i]);
			if (curr.key > stack.peekFirst().key) {
				stack.peekFirst().right = curr;
			} else {
				TreeNode parent = null;
				// pop until curr.key > stack.top.key
				// the previous pop one is the last node with key > curr.key
				// last.left = curr
				while (!stack.isEmpty() && curr.key < stack.peekFirst().key) {
					parent = stack.pollFirst();
				}
				parent.left = curr;
			}
			stack.offerFirst(curr);
		}
		return root;
	}
}
