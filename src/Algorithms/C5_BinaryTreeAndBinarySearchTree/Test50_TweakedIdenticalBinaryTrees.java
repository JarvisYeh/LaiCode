package Algorithms.C5_BinaryTreeAndBinarySearchTree;

import util.TreeNode;

public class Test50_TweakedIdenticalBinaryTrees {
	/**
	 * 两棵树是否旋转等价
	 * 旋转等价的条件
	 * 1. one.key = two.key
	 * 2. one的左子树和two的左子树旋转等价 && one的右子树和two的右子树旋转等价
	 * 	或者
	 * 	one的左子树和two的右子树旋转等价 && one的右子树和two的左子树旋转等价
	 **/
	public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
		if (one == null && two == null) {
			return true;
		}

		if (one == null || two == null) {
			return false;
		}

		if (one.key != two.key) {
			return false;
		}

		return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right) ||
				isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left);
	}


}
