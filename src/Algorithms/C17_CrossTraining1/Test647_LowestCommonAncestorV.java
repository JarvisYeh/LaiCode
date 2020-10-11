package Algorithms.C17_CrossTraining1;

import java.util.ArrayList;
import java.util.List;

public class Test647_LowestCommonAncestorV {
	// K叉树的LCA
	public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
		if (root == null || root == a || root == b) {
			return root;
		}

		List<KnaryTreeNode> list = new ArrayList<>();
		for (KnaryTreeNode child : root.children) {
			KnaryTreeNode node = lowestCommonAncestor(child, a, b);
			if (node != null) {
				list.add(node);
			}
		}

		if (list.size() == 0) {
			return null;
		}
		if (list.size() == 1) {
			return list.get(0);
		}
		return root;
	}

	static class KnaryTreeNode {
		int key;
		List<KnaryTreeNode> children;

		public KnaryTreeNode(int key) {
			this.key = key;
			this.children = new ArrayList<>();
		}
	}

	public static void main(String[] args) {
		KnaryTreeNode one = new KnaryTreeNode(54);
		KnaryTreeNode two = new KnaryTreeNode(73);
		KnaryTreeNode three = new KnaryTreeNode(79);
		KnaryTreeNode four = new KnaryTreeNode(96);
		KnaryTreeNode five = new KnaryTreeNode(74);
		one.children.add(two);
		one.children.add(three);
		three.children.add(four);
		four.children.add(five);
		Test647_LowestCommonAncestorV test = new Test647_LowestCommonAncestorV();
		test.lowestCommonAncestor(one, four, five);
	}

}
