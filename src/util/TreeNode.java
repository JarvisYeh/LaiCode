package util;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
	public int key;
	public TreeNode left;
	public TreeNode right;

	// Constructor1
	public TreeNode(int key) {
		this.key = key;
		left = null;
		right = null;
	}
	// Constrcutor2
	public TreeNode() {};
	
	/**
	 * form a tree in the order as shown in the input array
	 * @param arr
	 * @return the root node of the tree
	 */
	public static TreeNode formCompleteTreeInOrder(Integer[] arr) {
		TreeNode[] nodes = new TreeNode[arr.length];
		for (int i=0; i!=arr.length; i++) {
			if (arr[i] != null) {
				nodes[i] = new TreeNode(arr[i]);
			}
		}
		for (int i=0; i!=arr.length; i++) {
			if (i*2 + 1 < arr.length) {
				nodes[i].left = nodes[i*2 + 1];
			}
			if (i*2 + 2 < arr.length) {
				nodes[i].right = nodes[i*2 + 2];
			} else {
				break;
			}
		}
		return nodes[0];
	}
	
	/**
	 * Use a queue to form an array to a binary tree, not necessarily complete binary tree
	 * Layer by layer consideration
	 * @param arr
	 * @return
	 */
	public static TreeNode formTreeByLayer(Integer[] arr) {
		TreeNode[] nodes = new TreeNode[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				nodes[i] = new TreeNode(arr[i]);
			}
		}
		
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(nodes[0]);
		int index = 1;
		while (index < arr.length) {
			int count = queue.size();
			for (int i = 0; i < count; i++) {
				TreeNode temp = queue.poll();
				if (temp == null) {
					continue;
				}
				if (index < arr.length) {
					temp.left = nodes[index++];
				}
				if (index < arr.length) {
					temp.right = nodes[index++];
				}
				if (temp.left != null) {
					queue.offer(temp.left);
				}
				if (temp.right != null) {
					queue.offer(temp.right);
				}
			}
		}
		
		return nodes[0];
	}
	
	/**
	 * Print the tree with root node root by level
	 * @param root
	 */
	public static void printTree(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// now that queue has all the next level elements to be printed
			// counter is set to the size of the queue
			int counter = queue.size();
			for (int i = 0; i < counter; i++) {
				TreeNode cur = queue.poll();
				if (cur == null) {
					System.out.print("# ");					
				} else {
					System.out.print(cur.key + " ");
					if (cur.left != null) {
						queue.offer(cur.left);
					} else {
						queue.offer(null);
					}
					if (cur.right != null) {
						queue.offer(cur.right);
					} else {
						queue.offer(null);
					}
				}
			}
			// print empty line
			System.out.println("");
		}
	}
}
