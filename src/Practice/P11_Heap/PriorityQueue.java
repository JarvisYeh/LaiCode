package Practice.P11_Heap;

import java.util.Comparator;

public class PriorityQueue<E> {
	private Object[] queue;
	private int size;
	private int capacity;
	private Comparator<E> comparator;

	// two constructors
	public PriorityQueue(int capacity, Comparator<E> comparator) {
		this.queue = new Object[capacity];
		this.capacity = capacity;
		this.comparator = comparator;
	}

	public PriorityQueue(Comparator<E> comparator) {
		queue = new Object[capacity];
		this.capacity = 11;
		this.comparator = comparator;
	}

	public PriorityQueue(Object[] arr, Comparator<E> comparator) {
		this.queue = arr;
		this.comparator = comparator;
		this.size = arr.length;
		this.capacity = arr.length;
		heapify(arr);
	}

	public boolean push(E e) {
		// if try to push null, reutrn false
		if (e == null) {
			return false;
		}

		// if pq is full, expand the pq
		if (size == capacity) {
			expandHeap();
		}
		queue[size] = e;
		percolateUp(size);
		size++;
		return true;
	}


	public E pop() {
		if (size == 0) {
			return null;
		}
		Object popped = queue[0];
		queue[0] = queue[size - 1];
		percolateDown(0);
		size--;
		return (E)popped;
	}

	public E peek() {
		if (size == 0) {
			return null;
		}
		return (E)queue[0];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Expand the capacity of the heap by 1.5 times
	 */
	private void expandHeap() {
		capacity *= 1.5;
		Object[] newQueue = new Object[capacity];
		for (int i = 0; i < queue.length; i++) {
			queue[i] = newQueue[i];
		}
		queue = newQueue;
	}

	private void percolateUp(int index) {
		while (index > 0) {
			int pIndex = (index - 1)/2;
			if (comparator.compare((E)queue[index], (E)queue[pIndex]) >= 0) {
				return;
			} else {
				Object tmp = queue[index];
				queue[index] = queue[pIndex];
				queue[pIndex] = tmp;
				index = pIndex;
			}
		}
	}

	private void percolateDown(int index) {
		while (index < size - 1) {
			Object candidate = queue[index];
			Object leftChild = null;
			Object rightChild = null;

			if (index*2 + 1 < size && queue[index *2 + 1] != null) {
				leftChild = queue[index*2 + 1];
				if (comparator.compare((E)candidate, (E)leftChild) > 0) {
					candidate = leftChild;
				}
			}


			if (index*2 + 2 < size && queue[index *2 + 2] != null) {
				rightChild = queue[index*2 + 2];
				if (comparator.compare((E)candidate, (E)rightChild) > 0) {
					candidate = rightChild;
				}
			}

			if (candidate == queue[index]) {
				return;
			} else if (candidate == leftChild) {
				Object tmp = queue[index*2 + 1];
				queue[index*2 + 1] = queue[index];
				queue[index] = tmp;
				index = index*2 + 1;
			} else {
				Object tmp = queue[index*2 + 2];
				queue[index*2 + 2] = queue[index];
				queue[index] = tmp;
				index = index*2 + 2;
			}
		}
	}

	// heapify the pq
	private void heapify(Object[] arr) {
		for (int i = arr.length - 1; i >= 0; i--) {
			percolateDown(i);
		}
	}


	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Integer[]{1, 9, 10, -5, 11, 91}, (s1, s2) -> {
			return Integer.compare(s1, s2);
		});


		for (int i = 0; i < 10; i++) {
			System.out.println(pq.pop());
		}
	}
}
