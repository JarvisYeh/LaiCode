package Algorithms.C23_CrossTraining4;

import java.util.*;

public class Test204_MaximumValuesOfSizeKSlidingWindows {
	// method 1: brute force
	// for each sliding window, linear scan to find the max
	// TC: O(n*k)
	// SC: O(k) for queue
	public List<Integer> maxWindowsI(int[] array, int k) {
		// initialize of queue and max[0]
		Queue<Integer> q = new ArrayDeque<>();
		List<Integer> res = new ArrayList<>();
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < k; i++) {
			q.offer(array[i]);
			max = Math.max(max, array[i]);
		}
		res.add(max);

		// i is the start place of each sliding windows
		// for loop: O(n)
		for (int i = 1; i <= array.length - k; i++) {
			q.poll();
			q.offer(array[i + k - 1]);
			// iterate current window and find max O(k)
			Iterator<Integer> it = q.iterator();
			max = Integer.MIN_VALUE;
			while (it.hasNext()) {
				max = Math.max(max, it.next());
			}
			res.add(max);
		}
		return res;
	}


	// method 2: use maxHeap
	// maxHeap stores <value, idx> pair in current sliding window
	// idx is the index of value in array
	// so that it's possible to remove specific position value in heap
	// for every sliding window
	// remove pair from maxHeap that leave window
	// add pair into maxHeap comes to window
	// TC: O(n*(k + logk)) = O(nk)
	// SC: O(k) for maxheap
	public List<Integer> maxWindowsII(int[] array, int k) {
		// initialize of maxHeap and max[0]
		Queue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
			// priority based on val in Pair
			public int compare(Pair p1, Pair p2) {
				return Integer.compare(p2.val, p1.val);
			}
		});

		List<Integer> res = new ArrayList<>();
		// i is the start place of each sliding windows
		// for loop: O(n)
		for (int i = 0; i < array.length; i++) {
			// for the first sliding window
			if (i < k) {
				maxHeap.offer(new Pair(array[i], i));
				if (i == k - 1) res.add(maxHeap.peek().val);
				continue;
			}
			// for the following sliding window
			// add i-th pair, remove (i - k)-th pair
			maxHeap.offer(new Pair(array[i], i));   // O(log k)
			maxHeap.remove(new Pair(-1, i - k));    // O(k + log k)
			res.add(maxHeap.peek().val);            // O(1)
		}
		return res;
	}

	// method 3: use maxHeap
	// everytime a new value come, store <value, idx> in maxHeap
	// everytime peek maxHeap top
	// if top Pair is in the range of current window, add to result
	// if top Pair is not in the range of current window
	// 		continue poll until the top is in the range of current window
	// TC: O(nlogn), every pair push/pop at most once
	// SC: O(n), worst case: descending array
	public List<Integer> maxWindowsIII(int[] array, int k) {
		Queue<Pair> maxHeap = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				return Integer.compare(o2.val, o1.val);
			}
		});
		List<Integer> res = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {		// O(n) loop
			// push current element
			maxHeap.offer(new Pair(array[i], i));			// O(log n)
			// if current element is in [0, k - 2]
			// still not fill up first window, no need to check max
			if (i < k - 1) {
				continue;
			}
			// boundary of current sliding window
			int left = i - k + 1, right = i;
			while (maxHeap.peek().idx < left || maxHeap.peek().idx > right) maxHeap.poll();	// O(n*log n) in total
			res.add(maxHeap.peek().val);
		}

		return res;
	}

	// method 4: use deque
	// deque from left to right store some Pair<val, idx> in current window
	// those elements violate the descending order will be discard
	// new element come
	// case 1. curr < deque.right => push to right
	// case 2. curr > deque.right => pop right until curr < deque.right || deque is empty
	// then check if deque.left in range of current window, if not pop until it is
	// TC: O(n), each element will be push/pop at most once
	// SC: O(k), all elements in window are in descending window, need to store a full widnow size
	public List<Integer> maxWindowsIV(int[] array, int k) {
		Deque<Pair> deque = new ArrayDeque<>();
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			// offer new element and keep the monotonically descending order
			while (!deque.isEmpty() && deque.peekLast().val < array[i]) deque.pollLast();
			deque.offer(new Pair(array[i], i));
			// for first k - 1 elements, no need to calculate max
			if (i < k - 1) continue;
			int left = i - k + 1, right = i;
			// if current left of deque is out of range, keep popping
			while (deque.peekFirst().idx < left || deque.peekFirst().idx > right) deque.pollFirst();
			res.add(deque.peekFirst().val);
		}
		return res;
	}

	static class Pair {
		int val;
		int idx;
		public Pair(int val, int idx) {
			this.val = val;
			this.idx = idx;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) return false;
			Pair p = (Pair) o;
			return p.idx == this.idx;
		}

		@Override
		public int hashCode() {
			return Integer.hashCode(idx);
		}
	}

	public static void main(String[] args) {
		Test204_MaximumValuesOfSizeKSlidingWindows t = new Test204_MaximumValuesOfSizeKSlidingWindows();
		t.maxWindowsIII(new int[]{2,4}, 2);
	}

}
