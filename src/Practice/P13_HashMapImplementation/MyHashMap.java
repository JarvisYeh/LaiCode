package Practice.P13_HashMapImplementation;

public class MyHashMap<K, V> {
	private int capacity;
	private int size;
	private float loadFactor;
	private Entry<K, V>[] array;
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	// default constructor
	public MyHashMap() {
		this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
	}

	public MyHashMap(int capacity, float loadFactor) {
		this.capacity = capacity;
		this.size = 0;
		this.loadFactor = loadFactor;
		this.array = new Entry[capacity];
	}


	public V get(K key) {
		// find the head Entry
		int index = getIndex(key);
		Entry<K, V> head = array[index];
		while (head != null) {
			if (checkKeyEquals(head.getKey(), key)) {
				return head.getValue();
			}
			head = head.next;
		}
		return null;
	}


	/**
	 * Returns the previous value before put
	 **/
	public V put(K key, V value) {
		int index = getIndex(key);

		// find the head Entry
		Entry<K, V> head = array[index];
		while (head != null) {
			if (checkKeyEquals(head.getKey(), key)) {
				V toBeReturn = head.getValue();
				head.value = value;
				return toBeReturn;
			}
			head = head.next;
		}

		// if not found, add the new Entry to the head of linked list
		Entry<K, V> newHead = new Entry(key, value);
		newHead.next = array[index];
		array[index] = newHead;
		size++;
		checkAndRehash(size);
		return null;
	}

	/**
	 * Return the removed entry value
	 */
	public V remove(K key) {
		int index = getIndex(key);
		Entry<K, V> curr = array[index];
		Entry<K, V> prev = null;

		// corner case: if the head is the to be removed Entry
		if (checkKeyEquals(curr.getKey(), key)) {
			array[index] = curr.next;
			size--;
			return curr.getValue();
		}

		while (curr != null) {
			if (checkKeyEquals(curr.getKey(), key)) {
				prev.next = curr.next;
				size--;
				return curr.getValue();
			}
			curr = curr.next;
			prev = curr;
		}

		return null;
	}

	/**
	 * handle key is null, use '=='
	 * handle key is not null, use 'equals()'
	 * before using equals, make sure k1.getKey() are not null
	 */
	private boolean checkKeyEquals(K key1, K key2) {
		return key1 == key2 || key1 != null && key1.equals(key2);
	}

	/**
	 * if key == null, entry are store in array[0]
	 **/
	private int getIndex(K key) {
		if (key == null) {
			return 0;
		}
		// make sure LHS is 0 so that hash number is a positive number
		int hashNumber = key.hashCode() & 0x7FFFFFFF;
		return hashNumber % this.capacity;
	}

	/**
	 * check if hashmap need rehash
	 * if needed, double the capacity and rehash
	 **/
	private void checkAndRehash(int size) {
		if (size < (int)(capacity * loadFactor)) {
			return;
		}
		capacity *= 2;
		Entry[] newArray = new Entry[capacity];
		// rehash

		// go through head entry in array
		for (Entry<K, V> head : array) {
			// go through the linked list w.r.t. head
			while (head != null) {
				// insert each new Entry to the head of newArray[newIndex]
				int newIndex = getIndex(head.getKey());
				Entry<K, V> newEntry = new Entry(head.getKey(), head.getValue());
				newEntry.next = newArray[newIndex];
				newArray[newIndex] = newEntry;
				head = head.next;
			}
		}

		// update array
		array = newArray;
	}

	public int size() {
		return size;
	}

	public static class Entry<K, V> {
		private final K key;
		V value;
		Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
	}
}
