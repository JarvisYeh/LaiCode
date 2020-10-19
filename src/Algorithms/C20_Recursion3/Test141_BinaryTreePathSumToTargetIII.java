package Algorithms.C20_Recursion3;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test141_BinaryTreePathSumToTargetIII {
	// 直上直下的路径中是否存在subPath中所有node之和等于target
	/**
	 * Method 1:
	 * 对于每个节点到root的路径，分别计算O(h)次，看是否存在满足target的值
	 * Time Complexity: O(n*height)
	 * Space Complexity: O(h) prefixNodeList
	 * @param root
	 * @param target
	 * @return
	 */
	public boolean exist(TreeNode root, int target) {
		List<TreeNode> prefix = new ArrayList<>();
		return exist(root, prefix, target);
	}

	private boolean exist(TreeNode root, List<TreeNode> prefix, int target) {
		if (root == null) {
			return false;
		}

		prefix.add(root);
		boolean currentExist = checkExist(prefix, target);
		boolean leftExist = exist(root.left, prefix, target);
		boolean rightExist = exist(root.right, prefix, target);
		prefix.remove(prefix.size() - 1);
		return leftExist || rightExist || currentExist;
	}

	private boolean checkExist(List<TreeNode> prefix, int target) {
		int sum = 0;
		for (int i = prefix.size() - 1; i >= 0; i--) {
			sum += prefix.get(i).key;
			if (sum == target) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method 2:
	 * Combine with DP
	 * prefixSum存[root, current]的所有值的sum
	 * hashSet存[root, i]的sum，i range from[root, current]
	 * 让每个节点检查prefixSum - target是否存在hashSet中
	 * @param root
	 * @param target
	 * @return
	 */
	public boolean existII(TreeNode root, int target) {
		int prefixSum = 0;
		HashSet<Integer> prefixSums = new HashSet<>();
		// 首先加入0，如果prefixSum正好等于target，返回true
		prefixSums.add(0);
		return existII(root, prefixSum, prefixSums, target);
	}

	public boolean existII(TreeNode root, int prefixSum, HashSet<Integer> prefixSums, int target) {
		prefixSum += root.key;
		if (root.key == 9) {

		}
		if (prefixSums.contains(prefixSum - target)) {
			return true;
		}

		boolean successAdded = prefixSums.add(prefixSum);

		// if left or right returns false, no action is taken
		// return false at final
		if (root.left != null && existII(root.left, prefixSum, prefixSums, target)) {
			return true;
		}

		if (root.right != null && existII(root.right, prefixSum, prefixSums, target)) {
			return false;
		}
		if (successAdded) {
			prefixSums.remove(prefixSum);
		}
		return false;
	}

	public static void main(String[] args) {
		Test141_BinaryTreePathSumToTargetIII test = new Test141_BinaryTreePathSumToTargetIII();
		test.existII(TreeNode.formTreeByLayer(new Integer[] {3, -8, 9, 4, 10, 2, -5, 1, -2}), 7);
	}



}
