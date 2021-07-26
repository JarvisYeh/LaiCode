package Practice.P04_QueueAndStack;

public class DequeUsingArray {
	private int[] arr;
	private int head;
	private int tail;
	private int size;
	private int capacity;

	public DequeUsingArray(int capacity) {
		this.arr = new int[capacity];
		this.size = 0;
		this.head = tail = -1;
		this.capacity = capacity;
	}

	public void offerFirst(int val) {
		if (size == capacity) {
			expandArray();
		}
		if (size == 0) {
			head = tail = 0;
			arr[head] = val;
		} else if (head == 0) {
			arr[capacity - 1] = val;
			head = capacity - 1;
		} else {
			arr[head - 1] = val;
			head = head - 1;
		}
		size++;
	}

	public void offerLast(int val) {
		if (size == capacity) {
			expandArray();
		}
		if (size == 0) {
			head = tail = 0;
			arr[tail] = val;
		} else {
			arr[(tail + 1) % arr.length] = val;
			tail = (tail + 1) % arr.length;
		}
		size++;
	}

	public Integer pollFirst() {
		if (size == 0) {
			return null;
		}
		int popped = arr[head];
		head = (head + 1) % arr.length;
		size--;
		return popped;
	}

	public Integer pollLast() {
		if (size == 0) {
			return null;
		}
		int popped = arr[tail];
		if (tail == 0) {
			tail = arr.length;
		} else {
			tail--;
		}
		size--;
		return popped;
	}

	public Integer peekFirst() {
		if (size == 0) {
			return null;
		}
		return arr[head];
	}

	public Integer peekLast() {
		if (size == 0) {
			return null;
		}
		return arr[tail];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	private void expandArray() {
		this.capacity = (int)(capacity * 1.5);
		int[] newArr = new int[capacity];
		int i = 0;
		for (i = 0; i < arr.length; i++) {
			newArr[i] = arr[(head + i) % arr.length];
		}
		arr = newArr;
		head = 0;
		tail = i - 1;
	}

	private void printDeque() {
		if (size == 0) {
			System.out.println("Empty Deque!");
		} else {
			for (int i = 0; i < size - 1; i++) {
				System.out.print(arr[(head + i)%arr.length] + "->");
			}
			System.out.println(arr[tail]);
		}
	}

	public static void main(String[] args) {
		DequeUsingArray d = new DequeUsingArray(3);
		d.offerFirst(2);
		d.offerLast(3);
		d.printDeque();

		d.pollFirst();
		d.pollLast();
		d.printDeque();

		for (int i = 1; i < 10; i++) {
			d.offerFirst(i);
			d.offerLast(i*2);
		}
		d.printDeque();
		for (int i = 0; i < 15; i++) {
			d.pollLast();
			d.pollFirst();
			d.printDeque();
		}
	}
}
