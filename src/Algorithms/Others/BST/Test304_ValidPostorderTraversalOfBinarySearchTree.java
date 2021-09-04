package Algorithms.Others.BST;

public class Test304_ValidPostorderTraversalOfBinarySearchTree {
	// Method 1
	// TC: O(h*n)
	// SC: O(h)
	public boolean validPostOrderI(int[] post) {
		return checkRange(post, 0, post.length - 1);
	}

	private boolean checkRange(int[] post, int l, int r) {
		if (l >= r) {
			return true;
		}

		// root = post[r];
		// left | right | root
		int startOfRight = l;
		while (post[startOfRight] < post[r]) {
			startOfRight++;
		}

		// check if [startOfRight, r) > root
		for (int i = startOfRight; i < r; i++) {
			if (post[i] < post[r]) {
				return false;
			}
		}

		return checkRange(post, l, startOfRight - 1) && checkRange(post, startOfRight, r - 1);
	}

	// Method 2
	// TC: O(n)
	// SC: O(h)
	public boolean validPostOrderII(int[] post) {
		if (post == null || post.length == 0) return true;
		int[] idx = {post.length - 1};
		return checkValid(post, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}


	private boolean checkValid(int[] post, int[] idx, int min, int max) {
		if (idx[0] < 0) return true;

		// left | right | root
		// 3 5 4, 3 < 4, means that node is no longer in current RIGHT segment
		if (post[idx[0]] < min) return true;

		// max is the constraint for LEFT segment
		// if one is > max, it can not be in that left segment, return false directly
		// 7 3 5 4, 7 can not be larger than 4
		if (post[idx[0]] > max) return false;

		int root = post[idx[0]];
		idx[0]--;
		return checkValid(post, idx, root, max) && checkValid(post, idx, min, root);
	}

	// Method 3
	// TC: O(n)
	// SC: O(h)
	// traverse from post[n - 1] to post[0]
	// check if it traversed all elements. if so, return true
	public boolean validPostOrderIII(int[] post) {
		if (post == null || post.length == 0) return true;
		int[] idx = {post.length - 1};
		traverse(post, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return idx[0] == -1;
	}

	private void traverse(int[] post, int[] idx, int min, int max) {
		if (idx[0] == -1) return;
		if (post[idx[0]] <= min || post[idx[0]] >= max) return;

		int root = post[idx[0]--];
		if (idx[0] >= 0 && post[idx[0]] > root) {
			traverse(post, idx, root, max);
		}
		if (idx[0] >= 0 && post[idx[0]] < root) {
			traverse(post, idx, min, root);
		}
	}

	public static void main(String[] args) {
		Test304_ValidPostorderTraversalOfBinarySearchTree t = new Test304_ValidPostorderTraversalOfBinarySearchTree();
		t.validPostOrderII(new int[]{1,4,5,3,9,2,11,12,10,6});
	}
}
