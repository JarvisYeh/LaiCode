package Algorithms.C02_Recursion1AndSortingAlgorithms;

public class Test328_HeapSort {
	// use max heap
	// TC: O(nlog n)
	// SC: O(1)
	public int[] heapsort(int[] arr) {
		if (arr == null || arr.length == 0) {
			return arr;
		}

		// heapify the tree, O(n) can be prove
		int n = arr.length - 1;	// last element index
		for (int i = (n - 1)/2; i >= 0; i--) {
			// start from the last non-leaf node
			// which is the parent of last element
			percolateDown(arr, arr.length, i);
		}

		// O(nlog n)
		// n times of percolate down
		for (int i = arr.length - 1; i >= 0; i--) {
			// swap the largest element of maxHeap, which is arr[0] to the end of arr
			// which is actually remove that element from the heap
			swap(arr, 0, i);

			// since the arr[end] now swap to arr[0], percolateDown arr[0]
			percolateDown(arr, i,0);
		}
		return arr;
	}

	// move a element in heap down to its right position
	// O(log n)
	private void percolateDown(int[] arr, int n, int index) {
		while (index < n) {
			int leftChild = index*2 + 1;
			int rightChild = index*2 + 2;
			int candidate = index;

			if (leftChild < n && arr[leftChild] > arr[candidate]) {
				candidate = leftChild;
			}
			if (rightChild < n && arr[rightChild] > arr[candidate]) {
				candidate = rightChild;
			}

			if (candidate == index) {
				return;
			} else {
				swap(arr, candidate, index);
				index = candidate;
			}
		}
	}

	// recursion way of implement percolate down
	// O(log n)
	private void percolateDownRecur(int[] arr, int n, int index) {
		int leftChild = index*2 + 1;
		int rightChild = index*2 + 2;
		int candidate = index;

		if (leftChild < n && arr[leftChild] > arr[candidate]) {
			candidate = leftChild;
		}

		if (rightChild < n && arr[rightChild] > arr[candidate]) {
			candidate = rightChild;
		}


		// base case: index is already the largest among three
		if (candidate == index) {
			return;
		}

		swap(arr, index, candidate);
		percolateDownRecur(arr, n, candidate);
	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		Test328_HeapSort t = new Test328_HeapSort();
		int[] nums = {5, 2, 3, 1};
		t.heapsort(nums);
		for (int i : nums) {
			System.out.print(i + "\t");
		}
	}
}
