package util;

import java.util.List;

public class ListNode {
	public int value;
	public ListNode next;

	public ListNode(int value) {
		this.value = value;
		next = null;
	}
	
	// form an linked list from an array
	public static ListNode fromArray(int[] arr) {
		ListNode dummyhead = new ListNode(0);
		ListNode cur = dummyhead;
		for(int i: arr) {
			cur.next = new ListNode(i);
			cur = cur.next;
		}
		return dummyhead.next;
	}
	
	// print every element in an linked list
	public static void printAll(ListNode head) {
		if(head == null) {
			System.out.println("The list is empty");
			return;
		}
		while(head != null) {
			if (head.next == null) {
				System.out.print(head.value);
			} else {
				System.out.print(head.value + "->");
			}
			head = head.next;
		}
		System.out.println("");
	}
	
	// get the i-th node of the linked list
	public static ListNode getIndex (ListNode head, int index) {
		while (index > 0 && head != null) {
			head = head.next;
			index--;
		}
		return head;
	}
	
	// get the length of the linked list
	public static int length(ListNode head) {
		int length = 0;
		ListNode curr = head;
		while(curr != null) {
			length++;
			curr = curr.next;
		}
		return length;
	}
	
	// append a new node at the start of the list with specific value
	public static ListNode appendHead(ListNode head, int val) {
		ListNode newHead = new ListNode(val);
		newHead.next = head;
		return newHead;
	}
	
	// append a new node at the end of the list with specific value
	public static ListNode appendTail(ListNode head, int val) {
		if (head == null) {
			return new ListNode(val);
		}
		ListNode curr = head;
		while(curr.next != null) {
			curr = curr.next;
		}
		curr.next = new ListNode(val);
		return head;
	}

	// remove the node with respect to the index
	public static ListNode removeIndex(ListNode head, int index) {
		// using dummy head
//		ListNode dummyHead = new ListNode(0);
//		dummyHead.next = head;
//		ListNode prev = dummyHead;
//		ListNode curr = head;
//		while (index > 0 && curr != null) {
//			prev = prev.next;
//			curr = curr.next;
//			index--;
//		}
//		if (curr == null) {
//			return null;
//		}
//		prev.next = curr.next;
//		return dummyHead.next;
		
		// not using dummy head
		if (head == null) {
			return null;
		}
		if (index == 0) {
			return head.next;
		}
		ListNode prev = head;
		while (index > 1 && prev.next != null) {
			prev = prev.next;
			index--;
		}
		prev.next = prev.next.next;
		return head;
	}
	
	// remove the node with respect to the value
	public static ListNode removeVal(ListNode head, int val) {
		// head will change
		if (head == null) {
			return head;
		}
		if (head.value == val) {
			return head.next;
		}

		// head remains the same
		ListNode curr = head;
		while (curr.next != null) {
			if(curr.next.value == val) {
				curr.next = curr.next.next;
				return head;
			}
			curr = curr.next;
		}
		return head;
	}

}
