package Algorithms.C21_CrossTraining3;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test133_MergeKSortedArray {
	public int[] merge(int[][] arrayOfArrays) {
		Queue<MyEntry>  minHeap = new PriorityQueue<>(new Comparator<MyEntry>() {
			@Override
			public int compare(MyEntry o1, MyEntry o2) {
				return Integer.compare(o1.value, o2.value);
			}
		});

		int size = 0;
		for (int i = 0; i < arrayOfArrays.length; i++) {
			if (arrayOfArrays[i] != null && arrayOfArrays[i].length > 0) {
				minHeap.offer(new MyEntry(i, 0, arrayOfArrays[i][0]));
				size += arrayOfArrays[i].length;
			}
		}

		int[] res = new int[size];
		for (int i = 0; i < res.length; i++) {
			MyEntry curr = minHeap.poll();
			res[i] = arrayOfArrays[curr.x][curr.y];
			if (curr.y + 1 < arrayOfArrays[curr.x].length) {
				minHeap.offer(new MyEntry(curr.x, curr.y + 1, arrayOfArrays[curr.x][curr.y + 1]));
			}
		}
		return res;
	}

	static private class MyEntry {
		private int x;
		private int y;
		private int value;

		private MyEntry(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
}
