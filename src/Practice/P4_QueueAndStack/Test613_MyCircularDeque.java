package Practice.P4_QueueAndStack;

public class Test613_MyCircularDeque {
	private int[] array;
	private int size;
	private int head;
	private int tail;

	/**
	 * Initialize your data structure here. Set the size of the deque to be k.
	 */
	public Test613_MyCircularDeque(int k) {
		array = new int[k];
		size = 0;
	}

	/**
	 * Adds an item at the front of Deque. Return true if the operation is successful.
	 */
	public boolean insertFront(int value) {
		if (array.length == size) {
			return false;
		}
		if (head == 0) {
			head = array.length - 1;
		} else {
			head--;
		}
		array[head] = value;
		size++;
		return true;
	}

	/**
	 * Adds an item at the rear of Deque. Return true if the operation is successful.
	 */
	public boolean insertLast(int value) {
		if (array.length == size) {
			return false;
		}
		array[tail] = value;
		tail = (tail + 1) % array.length;
		size++;
		return true;
	}

	/**
	 * Deletes an item from the front of Deque. Return true if the operation is successful.
	 */
	public boolean deleteFront() {
		if (size == 0) {
			return false;
		}
		head = (head + 1) % array.length;
		size--;
		return true;
	}

	/**
	 * Deletes an item from the rear of Deque. Return true if the operation is successful.
	 */
	public boolean deleteLast() {
		if (array.length == 0) {
			return false;
		}

		if (tail == 0) {
			tail = array.length - 1;
		} else {
			tail--;
		}
		size--;
		return true;
	}

	/**
	 * Get the front item from the deque.
	 */
	public int getFront() {
		if (size == 0) {
			return -1;
		}
		return array[head];
	}

	/**
	 * Get the last item from the deque.
	 */
	public int getRear() {
		if (size == 0) {
			return -1;
		}
		if (tail == 0) {
			return array[array.length - 1];
		} else {
			return array[tail - 1];
		}
	}

	/**
	 * Checks whether the circular deque is empty or not.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Checks whether the circular deque is full or not.
	 */
	public boolean isFull() {
		return size == array.length;
	}

	public static void main(String[] args) {
		Test613_MyCircularDeque circularDeque = new Test613_MyCircularDeque(10);
		circularDeque.insertLast(1);
		circularDeque.insertLast(2);
		circularDeque.insertLast(3);
		circularDeque.deleteFront();
		circularDeque.deleteFront();
		circularDeque.deleteFront();
		circularDeque.insertLast(4);
		circularDeque.isEmpty();
		circularDeque.getFront();

	}
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */