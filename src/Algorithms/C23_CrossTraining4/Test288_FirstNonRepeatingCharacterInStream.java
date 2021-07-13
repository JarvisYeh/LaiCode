package Algorithms.C23_CrossTraining4;

import java.util.HashMap;

public class Test288_FirstNonRepeatingCharacterInStream {
	HashMap<Character, ListNode> occuredMap;
	ListNode head;
	ListNode tail;

	public Test288_FirstNonRepeatingCharacterInStream() {
		occuredMap = new HashMap<>();
	}

	// read char ch from stream
	public void read(char ch) {
		// if ch occurs before
		if (occuredMap.containsKey(ch)) {
			// if map[ch] = null, doesn't need any operation, return directly
			if (occuredMap.get(ch) == null) return;
			// if it still has a corresponding node in linked list, remove that node
			remove(occuredMap.get(ch));
			// update map[ch] = null, indicating ch has occured before
			occuredMap.put(ch, null);
		}
		// if ch first time occurs
		else {
			ListNode newNode = new ListNode(ch);
			occuredMap.put(ch, newNode);
			appendTail(newNode);
		}
	}

	// return the current first non-repeating character
	public Character firstNonRepeating() {
		return head == null ? null : head.val;
	}

	// remove a list node from linked list
	private void remove(ListNode n) {
		ListNode prev = n.prev;
		ListNode next = n.next;
		if (prev == null && next == null) { // remove the only node
			head = tail = null;
		} else if (prev == null) {  // remove head
			next.prev = null;
			head = next;
		} else if (next == null) {  // remove tail
			prev.next = null;
			tail = prev;
		} else {
			prev.next = next;
			next.prev = prev;
		}
		n.next = null;
		n.prev = null;
	}

	// append the node to tail
	private void appendTail(ListNode node) {
		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
	}

	static class ListNode {
		char val;
		ListNode prev;
		ListNode next;

		public ListNode(char c) {
			val = c;
		}
	}
}
