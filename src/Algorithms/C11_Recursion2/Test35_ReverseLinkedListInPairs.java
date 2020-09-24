package Algorithms.C11_Recursion2;

import util.ListNode;

public class Test35_ReverseLinkedListInPairs {
	// 1 -> 2 -> 3 -> 4 -> 5 =>
	// 2 -> 1 -> 4 -> 3 -> 5
	// 1 -> 2 -> 3 -> 4 =>
	// 2 -> 1 -> 4 -> 3

	/**
	 * Method 1:
	 * Recursion
	 */
	public ListNode reverseInPairsI(ListNode head) {
		// base case只剩一个节点或者不剩节点了
		if (head == null || head.next == null) {
			return head;
		}

		// obtain the head of the next chunk
		ListNode next = reverseInPairsI(head.next.next);

		// head -> head.next -> next;
		// head.next -> head -> next并return head.next
		ListNode newHead = head.next;
		newHead.next = head;
		head.next = next;
		return newHead;
	}

	/**
	 * Method 2:
	 * Iteration
	 * 每次反转curr和curr.next
	 **/
	public ListNode reverseInPairsII(ListNode head) {
		// 一个或零个节点
		if (head == null || head.next == null) {
			return head;
		}

		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		ListNode curr = head;
		ListNode prev = dummyHead;

		// prev -> curr -> curr.next -> …
		// prev -> curr.next -> curr -> …

		// curr == null表明不剩节点，curr.next == null表明还剩一个节点
		// 当还剩至少两个节点的时候，继续循环
		while (curr != null || curr.next != null) {
			ListNode next = curr.next.next;
			prev.next = curr.next;
			curr.next.next = curr;
			curr.next = next;
			// update reference
			prev = curr;
			curr = next;
		}
		return dummyHead.next;
	}

	public static void main(String[] args) {
		Test35_ReverseLinkedListInPairs test = new Test35_ReverseLinkedListInPairs();
		ListNode head = ListNode.fromArray(new int[]{1,2,3});
		test.reverseInPairsII(head);
	}


}
