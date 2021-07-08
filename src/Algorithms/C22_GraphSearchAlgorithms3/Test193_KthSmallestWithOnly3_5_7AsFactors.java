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
	public long kthI(int k) {
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

	/**
	 * Use an array to record each ugly number
	 * each ugly number is calculated based on a previous one
	 * use three pointers which all starts at 0
	 * index pointer 'a': arr[a] is the current base for factor 3
	 * index pointer 'b': arr[b] is the current base for factor 5
	 * index pointer 'c': arr[c] is the current base for factor 7
	 * every ugly number is the min(arr[a]*3, arr[b]*5, arr[c]*7)
	 * if current ugly number is calculated based on 2, pointer a++
	 * note that if arr[a]*3 == arr[b]*5, increase both 'a' and 'b'
	 * otherwise same ugly number will be calculated for next round
	 */
	public long kthII(int k) {
		int[] DP = new int[k];  // DP[i] is the i-th ugly number
		DP[0] = 3*5*7;
		int a = 0, b = 0, c = 0;
		for (int i = 1; i < k; i++) {
			DP[i] = Math.min(DP[a]*3, Math.min(DP[b]*5, DP[c]*7));
			if (DP[i] == DP[a]*3) a++;
			if (DP[i] == DP[b]*5) b++;
			if (DP[i] == DP[c]*7) c++;
		}
		return DP[k - 1];
	}
}
