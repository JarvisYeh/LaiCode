package Algorithms.C22_GraphSearchAlgorithms3;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test193_KthSmallestWithOnly3_5_7AsFactors {
	/**
	 * return the k-th smallest result of f(x, y, z) = 3^x * 5^y * 7^z
	 * 需要一个generated set保证同一个数不会被多次generate
	 * e.g. (3*5*7)*3*5 和 (3*5*7)*5*3
	 * @param k
	 * @return
	 */
	public long kth(int k) {
		Queue<Long> minHeap = new PriorityQueue<>();
		minHeap.offer(3 * 5 * 7L);
		HashSet<Long> set = new HashSet<>();
		long res = 0;
		for (int i = 0; i < k; i++) {
			res = minHeap.poll();
			if (set.add(res * 3)) {
				minHeap.offer(res * 3);
			}
			if (set.add(res * 5)) {
				minHeap.offer(res * 5);
			}
			if (set.add(res * 7)) {
				minHeap.offer(res * 7);
			}
		}
		return res;
	}
}
