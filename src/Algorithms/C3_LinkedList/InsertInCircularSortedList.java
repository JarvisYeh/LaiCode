package Algorithms.C3_LinkedList;

import util.ListNode;

public class InsertInCircularSortedList {
	// TC: O(n)
	// SC: O(1)
	public ListNode insert(ListNode head, int insertVal) {
		ListNode newNode = new ListNode(insertVal);

		// corner case, no value in linked list
		if (head == null) {
			newNode.next = newNode;
			return newNode;
		}

		// one pass to find the max&min node
		ListNode max = head;
		while (max.next != head) {
			if (max.next.value < max.value) break;
			max = max.next;
		}
		ListNode min = max.next;

		// if insertVal is less than min or larger than max
		// insert as ... max -> x -> min ...
		if (insertVal < min.value || insertVal > max.value) {
			newNode.next = min;
			max.next = newNode;
			return newNode;
		}

		// else insert as min ... -> x -> ... max
		// stop when
		// x < curr.next.val || curr is the last position
		ListNode curr = min;
		while (curr.next != min && curr.next.value < insertVal) {
			curr = curr.next;
		}
		newNode.next = curr.next;
		curr.next = newNode;
		return head;
	}

}
