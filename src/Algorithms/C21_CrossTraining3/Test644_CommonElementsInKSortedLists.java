package Algorithms.C21_CrossTraining3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Test644_CommonElementsInKSortedLists {
	// Method 1:
	// 两两求common
	// TC : O(kn)
	// SC : O(1)
	public List<Integer> commonElementsInKSortedArraysI(List<List<Integer>> input) {
		if (input == null || input.size() == 0) {
			return new ArrayList<>();
		}
		List<Integer> common = input.get(0);
		for (List<Integer> list : input) {
			common = getCommonOfTwoSortedArrs(common, list);
		}
		return common;
	}

	private List<Integer> getCommonOfTwoSortedArrs(List<Integer> one, List<Integer> two) {
		List<Integer> common = new ArrayList<>();
		int i = 0, j = 0;
		while (i < one.size() && j < two.size()) {
			if (one.get(i) < two.get(j)) {
				i++;
			} else if (one.get(i) > two.get(j)) {
				j++;
			} else {
				common.add(one.get(i));
				i++;
				j++;
			}
		}
		return common;
	}

	// Method 2:
	// Use minHeap
	// Maintain max value simultaneously
	// if min == max, one common value found
	// TC : worst case: O(kn*log k)
	// SC : O(n)
	public List<Integer> commonElementsInKSortedArraysII(List<List<Integer>> input) {
		List<Integer> res = new ArrayList<>();
		PriorityQueue<MyEntry> minHeap = new PriorityQueue<>();
		int currMax = Integer.MIN_VALUE;
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i) != null && input.get(i).size() > 0) {
				minHeap.offer(new MyEntry(i, 0, input.get(i).get(0)));
				currMax = Math.max(currMax, input.get(i).get(0));
			}
		}

		// while minHeap has the same size as list amount
		// which means all list have not come to end
		while (minHeap.size() == input.size()) {
			// if min == max, one common value found
			if (minHeap.peek().value == currMax) {
				res.add(minHeap.peek().value);
				currMax = refresh(minHeap, input);
			} else {
				MyEntry curr = minHeap.poll();
				if (curr.y + 1 < input.get(curr.x).size()) {
					MyEntry next = new MyEntry(curr.x, curr.y + 1, input.get(curr.x).get(curr.y + 1));
					minHeap.offer(next);
					currMax = Math.max(currMax, next.value);
				}
			}
		}
		return res;
	}

	private int refresh(PriorityQueue<MyEntry> minHeap, List<List<Integer>> input) {
		int currMax = Integer.MIN_VALUE;
		for (int i = 0; i < minHeap.size(); i++) {
			MyEntry curr = minHeap.poll();
			if (curr.y + 1 < input.get(curr.x).size()) {
				MyEntry next = new MyEntry(curr.x, curr.y + 1, input.get(curr.x).get(curr.y + 1));
				minHeap.offer(next);
				currMax = Math.max(currMax, next.value);
			}
		}
		return currMax;
	}

	static private class MyEntry implements Comparable<MyEntry>{
		private int x;
		private int y;
		private int value;

		private MyEntry(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public int compareTo(MyEntry o) {
			return Integer.compare(this.value, o.value);
		}
	}

	public static void main(String[] args) {
		Test644_CommonElementsInKSortedLists test = new Test644_CommonElementsInKSortedLists();
		int[][] tmp = new int[][]{{2,4,5,7,9,11,13,14,16,16,17,19,21,22},{2,3,4,6,8,8,9,9,10,10,11,13,14},{0,1,1,2,4,5,6,6,6,8}};
		List<List<Integer>> input = new ArrayList<>();
		for (int[] arr : tmp) {
			List<Integer> list = new ArrayList<>();
			for (int i : arr) {
				list.add(i);
			}
			input.add(list);
		}
		test.commonElementsInKSortedArraysII(input);
	}

}
