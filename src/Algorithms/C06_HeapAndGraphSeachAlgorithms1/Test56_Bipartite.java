package Algorithms.C06_HeapAndGraphSeachAlgorithms1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class Test56_Bipartite {
	/**
	 * Use BFS
	 * @param graph
	 * @return
	 */
	public boolean isBipartiteI(List<GraphNode> graph) {
		HashMap<GraphNode, Integer> expanded = new HashMap<>();
		for (GraphNode startNode : graph) {
			if (!BFS(startNode, expanded)) {
				return false;
			}
		}
		return true;
	}

	private boolean BFS(GraphNode startNode, HashMap<GraphNode, Integer> generated) {
		// if this sub-graph is expanded, directly returns true
		if (generated.containsKey(startNode)) {
			return true;
		}

		Deque<GraphNode> queue = new ArrayDeque<>();
		queue.offer(startNode);
		// set startNode to group 0
		generated.put(startNode, 0);

		// BFS sub-graph
		while (queue.size() != 0) {
			GraphNode curr = queue.poll();
			int groupNum = generated.get(curr);

			// iterate through the neighbors of current node
			for (GraphNode nei : curr.neighbours) {
				// if that neighbor is not expanded previously
				if (!generated.containsKey(nei)) {
					queue.offer(nei);
					generated.put(nei, groupNum == 0 ? 1 : 0);
					// if that neighbor
				} else {
					if (generated.get(nei) == groupNum) {
						return false;
					} else {
						continue;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Use DFS
	 * @param graph
	 * @return
	 */
	public boolean isBipartiteII(List<GraphNode> graph) {
		HashMap<GraphNode, Integer> map = new HashMap<>();
		for (GraphNode n : graph) {
			if (map.containsKey(n)) {
				continue;
			} else if (!validNode(n, 1, map)) {
				return false;
			}
		}
		return true;
	}

	private boolean validNode(GraphNode node, int supposedColor, HashMap<GraphNode, Integer> map) {
		// base case
		if (map.containsKey(node)) {
			return map.get(node) == supposedColor;
		}

		// add current node into map, current node is traversed
		map.put(node, supposedColor);

		// check all its neighbors, one invalid return false
		for (GraphNode nei : node.neighbours) {
			if (!validNode(nei, - supposedColor, map)) {
				return false;
			}
		}
		return true;
	}
}
