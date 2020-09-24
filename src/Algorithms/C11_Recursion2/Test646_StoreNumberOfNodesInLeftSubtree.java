package Algorithms.C11_Recursion2;

public class Test646_StoreNumberOfNodesInLeftSubtree {
	public void numNodesLeft(TreeNodeLeft root) {
		setNumNodesLeft(root);
	}

	private int setNumNodesLeft(TreeNodeLeft root) {
		// base case: leaf node
		if (root == null) {
			return 0;
		}

		int left = setNumNodesLeft(root.left);
		int right = setNumNodesLeft(root.right);

		root.numNodesLeft = left;
		return left + right + 1;
	}

	private static class TreeNodeLeft {
		public int key;
		public TreeNodeLeft left;
		public TreeNodeLeft right;
		public int numNodesLeft;
		public TreeNodeLeft(int key) {
			this.key = key;
		}
	}
}
