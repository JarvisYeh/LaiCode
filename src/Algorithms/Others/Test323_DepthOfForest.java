package Algorithms.Others;

public class Test323_DepthOfForest {
	public int depth(int[] forest) {
		int n = forest.length;
		int[] depth = new int[forest.length];
		int maxDepth = 0;
		for (int i = 0; i < n; i++) {
			if (depth[i] == 0) {
				if (findDepth(i, forest, depth, new boolean[n]) == -1) return -1;
			}
			maxDepth = Math.max(maxDepth, depth[i]);
		}
		return maxDepth;
	}

	// return depth[i] of current tree node
	private int findDepth(int i, int[] forest, int[] depth, boolean[] visited) {
		// first check if node i is visited before
		if (visited[i]) return -1;
		// check if its depth is alreay calculated, if so, return directly
		if (depth[i] != 0) return depth[i];
		// base case, root node
		if (forest[i] == -1) {
			depth[i] = 1;
			return depth[i];
		}

		// mark node i as visited
		visited[i] = true;
		// current node depth is parent node depth + 1
		// if parent node depth returns -1, meaning there is loop, return -1 directly
		int depthOfParent = findDepth(forest[i], forest, depth, visited);
		if (depthOfParent == -1) return -1;
		depth[i] = depthOfParent + 1;
		return depth[i];
	}
}
