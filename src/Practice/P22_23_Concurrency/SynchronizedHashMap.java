package Practice.P22_23_Concurrency;


import java.util.Objects;

public class SynchronizedHashMap<K, V> {
	private int size;
	private int capacity;
	private float loadFactor;
	private Node<K, V>[] buckets;
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	private static final float DEFAULT_INITIAL_LOAD_FACTOR = 0.75f;

	public SynchronizedHashMap(int capacity, float loadFactor) {
		size = 0;
		this.capacity = capacity;
		this.loadFactor = loadFactor;
		buckets = new Node[capacity];
	}

	public SynchronizedHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_INITIAL_LOAD_FACTOR);
	}

	// read buckets[i]
	public synchronized boolean containsKey(K key) {
		int idx = getIndex(key);
		Node<K, V> head = buckets[idx];
		while (head != null) {
			if (checkKeyEquals(head.key, key)) {
				return true;
			}
			head = head.next;
		}
		return false;
	}


	// read buckets[0,end]
	public synchronized boolean containsValue(V val) {
		for (int i = 0; i < buckets.length; i++) {
			Node<K, V> head = buckets[i];
			while (head != null) {
				if (head.val == val || (val != null && val.equals(head.val))) {
					return true;
				}
				head = head.next;
			}
		}
		return false;
	}

	// read buckets[idx]
	public synchronized V get(K key) {
		int idx = getIndex(key);
		Node<K, V> head = buckets[idx];
		while (head != null) {
			if (checkKeyEquals(head.key, key)) {
				return head.val;
			}
			head = head.next;
		}
		return null;
	}

	// read write bucket[idx]
	// read write size
	public synchronized V put(K key, V val) {
		int idx = getIndex(key);
		Node<K, V> head = buckets[idx];
		while (head != null) {
			if (checkKeyEquals(head.key, key)) {
				V toBeReturn = head.val;
				head.val = val;
				return toBeReturn;
			}
			head = head.next;
		}
		// key not found
		Node<K, V> newHead = new Node<>(key, val);
		newHead.next = buckets[idx];
		buckets[idx] = newHead;
		size++;
		checkAndRehash();
		return null;
	}

	// read write buckets[i]
	// read write size
	public synchronized V remove(K key) {
		int idx = getIndex(key);
		Node<K, V> curr = buckets[idx];
		Node<K, V> dummyHead = new Node<>(null, null);
		Node<K, V> prev = dummyHead;
		prev.next = curr;

		while (curr != null) {
			if (checkKeyEquals(curr.key, key)) {
				prev.next = curr.next;
				curr.next = null; // for safe
				buckets[idx] = dummyHead.next;
				dummyHead.next = null; // for safe
				size--;
				return curr.val;
			}
			prev = curr;
			curr = curr.next;
		}
		return null;
	}

	// read size
	public synchronized boolean isEmpty() {
		return size == 0;
	}

	// read size
	public synchronized int size() {
		return size;
	}

	private int getIndex(K key) {
		if (key == null) {
			return 0;
		}
		int hash = key.hashCode() & (0xffff >>> 1);
		return hash % capacity;
	}

	// return (key1 == key2) || (key1 != null && key1.equals(key2))
	private boolean checkKeyEquals(K key1, K key2) {
		return Objects.equals(key1, key2);
	}

	// read write capacity, size, buckets
	private void checkAndRehash() {
		if (size < (int) capacity*loadFactor) {
			return;
		}

		// need to rehash
		capacity *= 2;
		Node<K, V>[] newBuckets = new Node[capacity];
		// iterate for all head in array
		// iterate all nodes follows the head
		for (Node<K, V> head : buckets) {
			Node<K, V> curr = head;
			while (curr != null) {
				int idx = getIndex(curr.key);
				Node<K, V> next = curr.next;
				curr.next = newBuckets[idx];
				newBuckets[idx] = curr;
				curr = next;
			}
		}
		buckets = newBuckets;
	}


	static class Node<K, V> {
		// key should be final, can not be changed
		private final K key;
		private V val;
		Node next;

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return val;
		}

		public void setValue(V val) {
			this.val = val;
		}
	}
}
