package Algorithms.C21_CrossTraining3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Test644_CommonElementsInKSortedLists {
	// Method 1:
	// iterative reduction
	// TC : O(kn)
	// SC : O(n)
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

	// Method 1:
	// binary reduction
	// TC: O(kn)
	// SC: O(n + logk)
	public List<Integer> commonElementsInKSortedArraysII(List<List<Integer>> input) {
		if (input == null || input.size() == 0) return new ArrayList<>();
		if (input.size() == 1) return input.get(0);
		int mid = input.size()/2;
		List<Integer> left = commonElementsInKSortedArraysII(input.subList(0, mid));
		List<Integer> right = commonElementsInKSortedArraysII(input.subList(mid, input.size()));
		return merge(left, right);
	}

	private List<Integer> merge(List<Integer> l1, List<Integer> l2) {
		List<Integer> common = new ArrayList<>();
		int i = 0, j = 0;
		while (i < l1.size() && j < l2.size()) {
			if (l1.get(i) < l2.get(j)) {
				i++;
			} else if (l1.get(i) > l2.get(j)) {
				j++;
			} else {
				common.add(l1.get(i));
				i++;
				j++;
			}
		}
		return common;
	}

	// Method 3:
	// Use minHeap and currMax
	// Maintain max value simultaneously
	// if min == max, one common value found
	// TC : worst case: O(kn*log k)
	//		every element pushed and popped from minHeap once
	// SC : O(k) for minHeap
	public List<Integer> commonElementsInKSortedArraysIII(List<List<Integer>> input) {
		List<Integer> res = new ArrayList<>();
		PriorityQueue<Tuple> minHeap = new PriorityQueue<>((t1, t2) -> Integer.compare(t1.val, t2.val));

		// initialize minHeap and currMax
		int currMax = Integer.MIN_VALUE;
		for (int i = 0; i < input.size(); i++) {
			if (input.get(i) != null && input.get(i).size() > 0) {
				minHeap.offer(new Tuple(i, 0, input.get(i).get(0)));
				currMax = Math.max(currMax, input.get(i).get(0));
			}
		}

		// while minHeap has the same size as list amount
		// which means all list have not come to end
		while (minHeap.size() == input.size()) {
			// if min == max, one common value found
			if (minHeap.peek().val == currMax) {
				res.add(currMax);
				currMax = refresh(minHeap, input);
			} else {
				Tuple curr = minHeap.poll();
				int i = curr.i, j = curr.j;
				if (j + 1 < input.get(i).size()) {
					minHeap.offer(new Tuple(i, j + 1, input.get(i).get(j + 1)));
					currMax = Math.max(currMax, input.get(i).get(j + 1));
				}
			}
		}
		return res;
	}

	private int refresh(PriorityQueue<Tuple> minHeap, List<List<Integer>> input) {
		int currMax = Integer.MIN_VALUE;
		List<Tuple> tmpList = new ArrayList<>();
		// first pop out all pointers from the heap
		while (!minHeap.isEmpty()) {
			tmpList.add(minHeap.poll());
		}

		// then push all next pointers to heap if exist
		for (Tuple t : tmpList) {
			int i = t.i, j = t.j;
			if (j + 1 < input.get(i).size()) {
				minHeap.offer(new Tuple(i, j + 1, input.get(i).get(j + 1)));
				currMax = Math.max(currMax, input.get(i).get(j + 1));
			}
		}
		return currMax;
	}

	static private class Tuple {
		private int i;
		private int j;
		private int val;

		public Tuple(int i, int j, int val) {
			this.i = i;
			this.j = j;
			this.val = val;
		}
	}

	public static void main(String[] args) {
		Test644_CommonElementsInKSortedLists test = new Test644_CommonElementsInKSortedLists();
		int[][] tmp = new int[][]{
				{1,1,1,1,2,4,5,7,9,11,13,14,16,16,17,19,21,22},
				{1,1,1,1,2,4,11,100},
				{1,1,1,1,2,3,4,6,8,8,9,9,10,10,11,13,14},
				{1,1,1,1,2,4,5,6,6,6,8,11}};
		List<List<Integer>> input = new ArrayList<>();
		for (int[] arr : tmp) {
			List<Integer> list = new ArrayList<>();
			for (int i : arr) {
				list.add(i);
			}
			input.add(list);
		}
		System.out.println(test.commonElementsInKSortedArraysIII(input).toString());
	}

}
