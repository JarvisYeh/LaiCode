package Algorithms.C6_HeapAndGraphSeachAlgorithms1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GeneralGraphBFS {
public void BFSPrintByLevel(List<GraphNode> graph) {
	// corner case
	if (graph == null || graph.size() == 0) {
		return;
	}

	// iterative through different separate graphs
	for (GraphNode startNode : graph) {
		if (!startNode.visited) {
			// for each separate graph, initialize a queue
			Deque<GraphNode> queue = new ArrayDeque<>();
			queue.offer(startNode);
			startNode.visited = true;

			while (queue.size() != 0) {
				GraphNode curr = queue.poll();
				System.out.println(curr.value + " ");
				for (GraphNode nei : curr.neighbours) {
					if (!nei.visited) {
						queue.offer(nei);
						nei.visited = true;
					}
				}
			}
		}
	}
}

}

class GraphNode {
	int value;
	boolean visited;
	List<GraphNode> neighbours;
	public GraphNode(int v) {
		value = v;
		visited = false;
	}
}