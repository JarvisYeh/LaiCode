package Practice.P04_QueueAndStack;

public class DequeUsingLinkedList {
	private ListNode head;
	private ListNode tail;
	private int size;

	public DequeUsingLinkedList() {
		head = tail = null;
		size = 0;
	}

	public void offerFirst(int val) {
		ListNode newNode = new ListNode(val);
		if (size == 0) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}

	public void offerLast(int val) {
		ListNode newNode = new ListNode(val);
		if (size == 0) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	public Integer pollFirst() {
		if (size == 0) {
			return null;
		}
		if (size == 1) {
			int popped = head.val;
			head = tail = null;
			size--;
			return popped;
		}
		ListNode popped = head;
		head = head.next;

		// update pointer
		head.prev = null;
		popped.next = null;
		size--;
		return popped.val;
	}

	public Integer pollLast() {
		if (size == 0) {
			return null;
		}
		if (size == 1) {
			int popped = tail.val;
			head = tail = null;
			size--;
			return popped;
		}
		ListNode popped = tail;
		tail = tail.prev;

		// update pointer
		tail.next = null;
		popped.prev = null;
		size--;
		return popped.val;
	}

	public Integer peekFirst() {
		if (size == 0) {
			return null;
		}
		return head.val;
	}

	public Integer peekLast() {
		if (size == 0) {
			return null;
		}
		return tail.val;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void printDeque() {
		ListNode curr = head;
		if (curr == null) {
			System.out.println("Empty Deque!");
			return;
		}
		while (curr.next != null) {
			System.out.print(curr.val + "->");
			curr = curr.next;
		}
		System.out.println(curr.val);
	}


	private class ListNode {
		int val;
		ListNode prev;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
			next = null;
			prev = null;
		}
	}

	public static void main(String[] args) {
		DequeUsingLinkedList d = new DequeUsingLinkedList();
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
			d.printDeque();
		}
	}
}

