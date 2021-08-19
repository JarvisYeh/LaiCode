package Algorithms.Others.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test91_ArrayHopperIV {
	public int minJump(int[] array, int index) {
		int n = array.length;
		boolean[] generated = new boolean[n];
		Queue<Integer> q = new ArrayDeque<>();

		generated[index] = true;
		q.offer(index);
		int step = 0;
		while (!q.isEmpty()) {
			for (int c = q.size(); c > 0; c--) {
				int currIdx = q.poll();
				if (currIdx == n - 1) return step;
				for (int i = 1; i <= array[currIdx]; i++) {
					int leftIdx = currIdx - i;
					if (leftIdx >= 0 && !generated[leftIdx]) {
						q.offer(leftIdx);
						generated[leftIdx] = true;
					}
					int rightIdx = currIdx + i;
					if (rightIdx < n && !generated[rightIdx]) {
						q.offer(rightIdx);
						generated[rightIdx] = true;
					}
				}
			}
			step++;
		}
		return -1;
	}
}
