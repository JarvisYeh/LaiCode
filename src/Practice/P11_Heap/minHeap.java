package Practice.P11_Heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class minHeap {
	private int[] array;
	private int size;

	/**
	 * Constructor 1
	 * Create an empty min heap with specific capacity
	 *
	 * @param cap
	 */
	public minHeap(int cap) {
		if (cap <= 0) {
			throw new IllegalArgumentException("Capacity can not be <= 0");
		}
		this.array = new int[cap];
		size = 0;

	}

	/**
	 * Constructor 2
	 * Allows heapify() operation
	 *
	 * @param array
	 */
	public minHeap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("Input array can not be null or empty");
		}
		this.array = array;
		size = array.length;
		heapify();
	}

	private void heapify() {
		int lastEleIndex = size - 1;
		int lastParentIndex = (lastEleIndex - 1)/2;
		for (int i=lastParentIndex; i>=0; i--) {
			percolateDown(array[i]);
		}
	}

	private void percolateDown(int index) {
		// while it's node leaf node, it has at least left child
		// last none leave node index = ((size - 1) - 1)/2 = size/2 - 1
		while (index <= size/2 - 1) {
			int left = 2 * index + 1;
			int right = 2 * index + 2;

			// temporary define the left child as candidate
			int toBeSwap = left;

			// if the node has right child and its value is less
			// change candidate to right child
			if (right < size && array[right] < array[left]) {
				toBeSwap = right;
			}

			// swap
			if (array[toBeSwap] < array[index]) {
				swap(array, toBeSwap, index);
			} else {
				break;
			}

			// update index to child node
			index = toBeSwap;
		}
	}

	private void percolateUp(int index) {
		while (index > 0) {
			int parent = (index - 1)/2;
			if (array[index] < array[parent]) {
				swap(array, index, parent);
			} else {
				break;
			}
			index = parent;
		}
	}

	private void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public void offer(int ele) {
		if (size == array.length) {
			this.array = Arrays.copyOf(array, (int)(array.length*1.5));
		}
		array[size] = ele;
		percolateUp(size);
		size++;
	}

	public Integer poll() {
		if (size == 0) {
			throw new NoSuchElementException("Heap is empty");
		}
		int toBeReturn = array[0];
		array[0] = array[size - 1];
		size--;
		percolateDown(0);
		return toBeReturn;
	}

	public Integer peek() {
		if (size == 0) {
			throw new NoSuchElementException("Heap is empty");
		}
		return array[0];
	}

	public void update(int index, int ele) {
		if (index < 0 || index >= size) {
			return;
		}
		int prev = array[index];
		array[index] = ele;
		if (prev < ele) {
			percolateDown(index);
		} else {
			percolateUp(index);
		}
	}


	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

}
