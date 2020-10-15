package Algorithms.C19_CrossTraining2;

import util.GraphNode;

import java.util.*;

public class Test132_DeepCopyUndirectedGraph {
	// method 1: BFS
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
		for (GraphNode g : graph) {
			map.put(g, new GraphNode(g.key));
			copyList.add(map.get(g));
		}
		for (GraphNode startNode : graph) {
			DFS(startNode, map);
		}
		return copyList;
	}

	private GraphNode DFS(GraphNode g, Map<GraphNode, GraphNode> map) {
		if (!map.containsKey(g)) {
			map.put(g, new GraphNode(g.key));
		}

		for (GraphNode nei : g.neighbors) {
			if (!map.containsKey(nei)) {
				map.get(g).neighbors.add(DFS(nei, map));
			} else {
				map.get(g).neighbors.add(map.get(nei));
			}
		}
		return map.get(g);
	}



}
