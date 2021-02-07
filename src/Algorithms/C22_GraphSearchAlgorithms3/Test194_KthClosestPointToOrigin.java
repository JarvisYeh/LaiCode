package Algorithms.C22_GraphSearchAlgorithms3;


import java.util.*;

public class Test194_KthClosestPointToOrigin {
public List<Integer> closest(int[] a, int[] b, int[] c, int k) {
	// corner case
	if (a.length == 0 || b.length == 0 || c.length == 0) {
		return null;
	}

	List<Integer> res = new ArrayList<>();

	// initialize minHeap and offer the point that is closest to origin
	PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(new Comparator<List<Integer>>(){
		@Override
		public int compare(List<Integer> p1, List<Integer> p2) {
			long d1 = getDistanceSquare(p1, a, b, c);
			long d2 = getDistanceSquare(p2, a, b, c);
			if (d1 == d2) {
				return 0;
			}
			return d1 < d2 ? -1 : 1;
		}
	});

	// Arrays.asList create an fixed sized abstract list
	List<Integer> curr = Arrays.asList(0, 0, 0);
	HashSet<List<Integer>> generated = new HashSet<>();
	minHeap.offer(curr);
	generated.add(curr);

	// start of expand and generate process
	int i = 0;
	while (!minHeap.isEmpty()) {
		// expand
		curr = minHeap.poll();
		if (i == k - 1) {
			res = Arrays.asList(a[curr.get(0)], b[curr.get(1)], c[curr.get(2)]);
			break;
		}
		// generate
		if (curr.get(0) + 1 < a.length && generated.add(Arrays.asList(curr.get(0) + 1, curr.get(1), curr.get(2)))) {
			minHeap.offer(Arrays.asList(curr.get(0) + 1, curr.get(1), curr.get(2)));
		}
		if (curr.get(1) + 1 < b.length && generated.add(Arrays.asList(curr.get(0), curr.get(1) + 1, curr.get(2)))) {
			minHeap.offer(Arrays.asList(curr.get(0), curr.get(1) + 1, curr.get(2)));
		}
		if (curr.get(2) + 1 < c.length && generated.add(Arrays.asList(curr.get(0), curr.get(1), curr.get(2) + 1))) {
			minHeap.offer(Arrays.asList(curr.get(0), curr.get(1), curr.get(2) + 1));
		}

		i++;
	}
	return res;
}

private long getDistanceSquare(List<Integer> point, int[] a, int[] b, int[] c) {
	long res = 0;
	res += a[point.get(0)] * a[point.get(0)];
	res += b[point.get(1)] * b[point.get(1)];
	res += c[point.get(2)] * c[point.get(2)];
	return res;
}


	public static void main(String[] args) {
		Test194_KthClosestPointToOrigin test = new Test194_KthClosestPointToOrigin();
		System.out.println(test.closest(new int[]{1,3}, new int[]{2,3}, new int[]{2,4}, 8));
	}
}
