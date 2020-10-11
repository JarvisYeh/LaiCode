package Algorithms.C17_CrossTraining1;

import java.util.ArrayList;
import java.util.List;

public class Test648_LowestCommonAncestorVI {
	public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, List<KnaryTreeNode> nodes) {
		if (root == null || nodes.contains(root)) {
			return root;
		}

		List<KnaryTreeNode> list = new ArrayList<>();
		for (KnaryTreeNode child : root.children) {
			KnaryTreeNode node = lowestCommonAncestor(child, nodes);
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
}
