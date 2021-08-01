package Algorithms.C26_Final;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test_DetermineCousin {
	// method 1: recursion
	// cousin node are nodes in same level but with different parents
	public boolean isCousinI(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null || a == null || b == null) {
			return false;
		}
		int depthA = getDepth(root, a);
		int depthB = getDepth(root, b);
		// not in same root or not in same level, return false
		if (depthA == -1 || depthB == -1 || depthA != depthB) {
			return false;
		}

		// check if they have same parent
		// return true if not same parent
		return !checkSameParent(root, a, b);
	}

	// return the depth of node target in root tree
	public int getDepth(TreeNode root, TreeNode target) {
		if (root == null) {
			return -1;
		}

		if (root == target) {
			return 0;
		}

		int left = getDepth(root.left, target);
		int right = getDepth(root.right, target);
		if (left == -1 && right == -1) {
			return -1;
		}
		return Math.max(left, right) + 1;
	}


	private boolean checkSameParent(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) {
			return false;
		}

		if (root.left == a && root.right == b) {
			return true;
		}

		boolean left = checkSameParent(root.left, a, b);
		boolean right = checkSameParent(root.right, a, b);
		return left || right;
	}

	// method 2: recursion with one time traversal
	// TC: O(2^n)
	public boolean isCousinII(TreeNode root, TreeNode a, TreeNode b) {
		boolean[] res = {false};
		if (root == null || a == null || b == null) {
			return false;
		}
		helperII(root, a, b, 0, res);
		return res[0];
	}

	// return the a or b's level closer to root
	private int helperII(TreeNode root, TreeNode a, TreeNode b, int level, boolean[] res) {
		// base case 1
		if (root == null) {
			return -1;
		}
		// base case 2
		if (root == a || root == b) {
			return level;
		}

		int left = helperII(root.left, a, b, level + 1, res);
		int right = helperII(root.right, a, b, level + 1, res);
		// if left and right return a and b's level
		// which are same
		// and their level - curLevel > 1, i.e. curr is not their parent
		// set res[0] to true
		if (left == right && left - level > 1) {
			res[0] = true;
		}
		return left == -1 ? right : left;
	}


	// method 3: BFS
	// TC: O(2^n)
	// SC: O(2^n), last level nodes
	public boolean isCousinIII(TreeNode root, TreeNode a, TreeNode b) {
		// if a and b are children of root, return false directly
		if ((root.left == a && root.right == b) || (root.left == b && root.right == a)) {
			return false;
		}
		// init queue
		Queue<TreeNode> q = new ArrayDeque<>();
		q.offer(root);

		while (!q.isEmpty()) {
			boolean foundA = false, foundB = false;
			for (int i = q.size(); i > 0; i--) {
				TreeNode curr = q.poll();
				if (curr == a) foundA = true;
				if (curr == b) foundB = true;
				// if found A and B in same (this) level, return true directly
				if (foundA && foundB) return true;
				// check if they are children of curr (A and B in next level with same parent = curr)
				// if so, directly return false
				if ((curr.left == a && curr.right == b) || (curr.left == b && curr.right == a)) {
					return false;
				}
				if (curr.left != null) {
					q.offer(curr.left);
				}
				if (curr.right != null) {
					q.offer(curr.right);
				}
			}
		}
		return false;
	}

}
