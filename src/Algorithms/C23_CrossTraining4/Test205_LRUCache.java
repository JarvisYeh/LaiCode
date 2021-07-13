package Algorithms.C23_CrossTraining4;

import java.util.HashMap;

public class Test205_LRUCache<K, V> {
	// limit is the max capacity of the cache
	private HashMap<K, ListNode<K, V>> keyNodeMap;
	private ListNode<K, V> head;
	private ListNode<K, V> tail;
	private int size;
	private int capacity;

	public Test205_LRUCache(int limit) {
		keyNodeMap = new HashMap<>();
		head = null;
		tail = null;
		size = 0;
		capacity = limit;
	}

	public void set(K key, V value) {
		// if the record <K, V> is in cache
		if (keyNodeMap.containsKey(key)) {
			// update v in node
			ListNode<K, V> alterNode = keyNodeMap.get(key);
			alterNode.val = value;
			// update node position, same as calling get(K key)
			get(alterNode.key);
			return;
		} else if (size == capacity) {        // if the record <k, V> is not in cache, and cache is full
			// remove head node
			remove(head);
		}
		// create new node and append it to LRU
		ListNode<K, V> newNode = new ListNode<>(key, value);
		append(newNode);
	}

	// return answer in cache
	// if not in cache, return null
	public V get(K key) {
		// retrieve node in linked list
		ListNode<K, V> node = keyNodeMap.get(key);
		// if it's in linkedList, i.e. in cache
		// update its position in linked list (i.e. remove then append)
		if (node != null) {
			remove(node);
			append(node);
			return node.val;
		}
		return null;
	}

	// remove a node from LRU
	// 1. remove it from map
	// 2. remove it from linked list
	public void remove(ListNode<K, V> node) {
		// remove from map
		keyNodeMap.remove(node.key);
		// remove from linked list
		ListNode<K, V> prev = node.prev;
		ListNode<K, V> next = node.next;
		if (prev == null && next == null) {
			head = tail = null;
		} else if (prev == null) {	// only prev = null, deleting head
			next.prev = null;
			head = next;
		} else if (next == null) {	// only next = null, deleting tail
			prev.next = null;
			tail = prev;
		} else {
			prev.next = next;
			next.prev = prev;
		}
		// safe deletion
		node.prev = null;
		node.next = null;
		size--;
	}

	// append a node to LRU
	// 1. aad to map
	// 2. append to tail of linked list
	public void append(ListNode<K, V> node) {
		// add to map
		keyNodeMap.put(node.key, node);
		// append to tail of linked list
		if (size == 0) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
		size++;
	}


	static class ListNode<K, V> {
		K key;
		V val;
		ListNode<K, V> prev;
		ListNode<K, V> next;
		public ListNode(K k, V v){
			key = k;
			val = v;
		}
	}

	public static void main(String[] args) {
		Test205_LRUCache<Integer, Integer> lru = new Test205_LRUCache<>(2);
		System.out.println(lru.get(1));
		lru.set(1, 1);
		lru.set(2, 2);
		System.out.println(lru.get(2));
		System.out.println(lru.get(1));
		lru.set(3, 3);
		System.out.println(lru.get(1));
		System.out.println(lru.get(2));
		System.out.println(lru.get(3));
	}
}
