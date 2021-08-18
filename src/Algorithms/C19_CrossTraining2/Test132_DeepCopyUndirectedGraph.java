package Algorithms.C19_CrossTraining2;

import util.GraphNode;

import java.util.*;

public class Test132_DeepCopyUndirectedGraph {
	// Method 1: BFS
	public List<GraphNode> copyI(List<GraphNode> graph) {
		if (graph == null || graph.size() == 0) {
			new ArrayList<>();
		}

		List<GraphNode> copyList = new ArrayList<>();
		Map<GraphNode, GraphNode> map = new HashMap<>();
		Queue<GraphNode> queue = new ArrayDeque<>();

		// initialization
		// copy every start nodes
		// add all starts nodes into queue
		for (GraphNode startNode : graph) {
			GraphNode copyNode = new GraphNode(startNode.key);
			copyList.add(copyNode);
			map.put(startNode, copyNode);
			queue.offer(startNode);
		}

		// BFS to construct all the neighbours relationship
		while (!queue.isEmpty()) {
			GraphNode curr = queue.poll();
			GraphNode newCurr = map.get(curr);
			for (GraphNode nei : curr.neighbors) {
				// the neighbor node does not copied before
				// copy it and enqueue the it
				if (!map.containsKey(nei)) {
					GraphNode copyNode = new GraphNode(nei.key);
					map.put(nei, copyNode);
					queue.offer(nei);
				}
				newCurr.neighbors.add(map.get(nei));
			}
		}

		return copyList;
	}

	// Method 2: DFS
	public List<GraphNode> copyII(List<GraphNode> graph) {
		if (graph == null || graph.size() == 0) {
			return new ArrayList<>();
		}
		List<GraphNode> copyList = new ArrayList<>();
		Map<GraphNode, GraphNode> map = new HashMap<>();

		for (GraphNode startNode : graph) {
			copyList.add(DFS(startNode, map));
		}
		return copyList;
	}

	private GraphNode DFS(GraphNode g, Map<GraphNode, GraphNode> map) {
		// base case, generated and copied before
		if (map.containsKey(g)) {
			return map.get(g);
		}

		// copy that node
		// 1. put <ori, new> into map
		// 2. recursively add neighbors node to neighbors list
		map.put(g, new GraphNode(g.key));
		for (GraphNode nei : g.neighbors) {
			map.get(g).neighbors.add(DFS(nei, map));
		}
		return map.get(g);
	}



}
