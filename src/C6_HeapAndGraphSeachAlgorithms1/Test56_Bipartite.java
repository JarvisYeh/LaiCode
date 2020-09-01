package C6_HeapAndGraphSeachAlgorithms1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class Test56_Bipartite {
	public boolean isBipartite(List<GraphNode> graph) {
		HashMap<GraphNode, Integer> expanded = new HashMap<>();
		for (GraphNode startNode : graph) {
			if (!BFS(startNode, expanded)) {
				return false;
			}
		}
		return true;
	}

	private boolean BFS(GraphNode startNode, HashMap<GraphNode, Integer> expanded) {
		// if this sub-graph is expanded, directly returns true
		if (expanded.containsKey(startNode)) {
			return true;
		}

		Deque<GraphNode> queue = new ArrayDeque<>();
		queue.offer(startNode);
		// set startNode to group 0
		expanded.put(startNode, 0);

		// BFS sub-graph
		while (queue.size() != 0) {
			GraphNode curr = queue.poll();
			int groupNum = expanded.get(curr);

			// iterate through the neighbors of current node
			for (GraphNode nei : curr.neighbours) {
				// if that neighbor is not expanded previously
				if (!expanded.containsKey(nei)) {
					queue.offer(nei);
					expanded.put(nei, groupNum == 0 ? 1 : 0);
					// if that neighbor
				} else {
					if (expanded.get(nei) == groupNum) {
						return false;
					} else {
						continue;
					}
				}
			}
		}
		return true;
	}
}
