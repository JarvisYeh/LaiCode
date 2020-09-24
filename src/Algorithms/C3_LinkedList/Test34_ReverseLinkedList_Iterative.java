package Algorithms.C3_LinkedList;

import util.ListNode;

public class Test34_ReverseLinkedList_Iterative {
    public ListNode reverse(ListNode head) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null, curr = head;
        while(curr != null) {
            // record next
            ListNode next = curr.next;
            // reverse link
            curr.next = prev;

            // move 1 step forward
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Test34_ReverseLinkedList_Iterative test = new Test34_ReverseLinkedList_Iterative();
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6});
        head = test.reverse(head);
        ListNode.printAll(head);
    }

}
