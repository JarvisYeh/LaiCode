package Algorithms.C6_HeapAndGraphSeachAlgorithms1;

import java.util.*;

public class Test25_KSmallestInUnsortedArray {
	/**
	 * Method 1: Using max heap
	 * Maintain a max heap with size = k
	 * everytime it comes with a value < top() of the heap
	 * 	pop the top value and insert this value into heap
	 **/
	public int[] kSmallestI(int[] array, int k) {
		Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		for (int i=0; i<k; i++) {
			maxHeap.offer(array[i]);
		}
		for (int i=k; i<array.length; i++) {
			if (array[i] < maxHeap.peek()) {
				maxHeap.poll();
				maxHeap.offer(array[i]);
			}
		}
		int[] res = new int[k];
		for (int i=0; i<k; i++) {
			res[k-1-i] = array[i];
		}
		return res;
	}

	/**
	 * Method 3: Quick Select
	 * every time choose a random pivot, and find its sorted index
	 * if index + 1 < k
	 * 	which means LHS elements (including pivot) are less than k
	 * 	find new pivot in the RHS
	 * if index + 1 > k
	 * 	which means LHS elements (including pivot) are larger than k
	 * 	find new pivot in LHS
	 * if index + 1 = k
	 * 	which means from index 0 to k - 1 are the k smallest elements
	 * 	directly returns
	 **/
	Random rand = new Random();
	public int[] kSmallestIII(int[] array, int k) {
		// corner case
		if (k == 0 || array == null || array.length == 0) {
			return new int[0];
		}

		quickSelect(array, 0, array.length - 1, k);
		return Arrays.copyOf(array, k);
	}

	private void quickSelect(int[] array, int left, int right, int k) {
		int index = partition(array, left, right);

		if (index + 1 < k) {
			quickSelect(array, index + 1, right, k);
		} else if (index + 1 > k) {
			quickSelect(array, left, index - 1, k);
		} else {
			return;
		}
	}

	private int partition(int[] array, int left, int right) {
		// generate random pivot
		int pivot = rand.nextInt(right - left + 1) + left;

		swap(array, right, pivot);
		int i = left, j = right - 1;
		while (i <= j) {
			if (array[i] < array[right]) {
				i++;
			} else {
				swap(array, i, j--);
			}
		}
		swap(array, i, right);
		return i;
	}

	private void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}


}
