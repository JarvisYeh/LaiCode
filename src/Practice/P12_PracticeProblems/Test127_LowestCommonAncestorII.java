package Practice.P12_PracticeProblems;

public class Test127_LowestCommonAncestorII {
	/**
	 * TreeNodeP is the TreeNode with Parent filed
	 * Step 1: get the depth of two nodes
	 * Step 2: check if they have the same root node
	 * 	1. same root , continue
	 * 	2. not same root, not in the same tree, return null
	 * Step 3: move the deeper one to the same level with shallower one
	 * Step 4: move both node up until meet the same parent
	 * 	return that LCA
	 **/
	public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
		if (one == null || two == null) {
			return null;
		}
		// step 1
		Result r_one = getResult(one);
		Result r_two = getResult(two);

		// step 2
		if (r_one.root != r_two.root) {
			return null;
		}

		// step 3
		int diff = Math.abs(r_one.depth - r_two.depth);
		TreeNodeP shallow = r_one.depth < r_two.depth ? one : two;
		TreeNodeP deep = shallow == one ? two : one;
		for (int i = 0; i < diff; i++) {
			deep = deep.parent;
		}

		// step 4
		while (deep != shallow) {
			shallow = shallow.parent;
			deep = deep.parent;
		}
		return shallow;
	}

	private Result getResult(TreeNodeP node) {
		int depth = 0;
		TreeNodeP root = null;
		while (node != null) {
			if (node.parent == null) {
				root = node;
			}
			node = node.parent;
			depth++;
		}
		return new Result(root, depth);
	}

	private static class Result {
		TreeNodeP root;
		int depth;

		Result(TreeNodeP root, int depth) {
			this.root = root;
			this.depth = depth;
		}
	}


	private static class TreeNodeP {
		public int key;
		public TreeNodeP left;
		public TreeNodeP right;
		public TreeNodeP parent;

		public TreeNodeP(int key, TreeNodeP parent) {
			this.key = key;
			this.parent = parent;
		}
	}
}
