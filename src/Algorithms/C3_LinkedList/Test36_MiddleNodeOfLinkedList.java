package Algorithms.C3_LinkedList;

import util.ListNode;

public class Test36_MiddleNodeOfLinkedList {
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     **/
    public ListNode middleNode(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }

        // 1 2 3s 4 5f null
        // 1 2 3s 4 5f 6 null
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

}
