package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.*;

public class Test430_CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		// form the graph structure
		List<List<Integer>> graph = new ArrayList<>();
		int[] incomingEdges = new int[numCourses];
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		for (int[] pair : prerequisites) {
			graph.get(pair[1]).add(pair[0]);
			incomingEdges[pair[0]]++;
		}

		// queue for store the current classes that their prerequisites amount are 0
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < numCourses; i++) {
			if (incomingEdges[i] == 0) {
				queue.add(i);
			}
		}

		int[] result = new int[numCourses];
		int index = 0;
		while (!queue.isEmpty()) {
			// expand from queue
			int c = queue.poll();
			// update result array
			result[index++] = c;

			// get the classes set that could be started after finish class c
			for (Integer i : graph.get(c)) {
				if (--incomingEdges[i] == 0) {
					queue.add(i);
				}
			}
		}

		// if all the classes are being expanded once, return the result array
		if (index == numCourses) {
			return result;
		}
		// if not, which means some classes is not able to be expanded at all
		// or their is loop in the graph, return empty array
		else {
			return new int[]{};
		}
	}

	public static void main(String[] args) {
		Test430_CourseScheduleII t = new Test430_CourseScheduleII();
		t.findOrder(3, new int[][]{{1,0},{0,2},{1,2}});
	}
}
