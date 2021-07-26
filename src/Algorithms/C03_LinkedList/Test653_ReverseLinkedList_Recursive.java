package Algorithms.C03_LinkedList;

import util.ListNode;

public class Test653_ReverseLinkedList_Recursive {
    public ListNode reverse(ListNode head) {
        // base case
        if (head.next == null) {
            return head;
        }

        // recursive call
        ListNode newHead = reverse(head.next);

        // reverse current level
        // n1 →  n2 → ...
        // n1 →  n2 ← ...
        //       ↓
        //      null
        head.next.next = head;
        head.next = null;

        // return newHead to parent
        return newHead;
    }

    public static void main(String[] args) {
        Test653_ReverseLinkedList_Recursive test = new Test653_ReverseLinkedList_Recursive();
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3, 4, 5, 6});
        head = test.reverse(head);
        ListNode.printAll(head);
    }
}
