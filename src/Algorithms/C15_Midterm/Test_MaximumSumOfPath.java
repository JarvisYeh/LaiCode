package Algorithms.C15_Midterm;

import util.TreeNode;

public class Test_MaximumSumOfPath {
/**
 * 从一个子节点到另一个子节点路径上所有值的最大和
 **/
public int maxSum(TreeNode root){
	if (root == null) {
		return 0;
	}
	int[] max = new int[1];
	max[0] = Integer.MIN_VALUE;
	maxSum(root, max);

	// 如果没有更新过最大值，说明所有的节点都只有一个孩子
	// 也就是tree中不存在两个leaf node
	// 返回的是最开始的特殊值，Integer.MIN_VALUE
	return max[0];
}

private int maxSum(TreeNode root, int[] max) {
	// base case
	if (root == null) {
		return 0;
	}

	int left = maxSum(root.left, max);
	int right = maxSum(root.right, max);

	// 只有同时有左右孩子的节点才考虑更新max
	if (root.left != null && root.right != null) {
		max[0] = Math.max(max[0], left + right + root.key);
	}

	// 如果左孩子或者说右孩子是null
	// 不再比较左孩子和右孩子的最大值，因为null的值没有意义，直接返回另一孩子的值+root.key
	if (root.left == null) {
		return right + root.key;
	}
	if (root.right == null) {
		return left + root.key;
	}
	return Math.max(left, right) + root.key;
}

}
