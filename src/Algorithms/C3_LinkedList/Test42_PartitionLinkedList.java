package Algorithms.C3_LinkedList;

import util.ListNode;

public class Test42_PartitionLinkedList {
    public ListNode partitionLinkedList(ListNode head, int target) {
        ListNode dummyOne = new ListNode(-1);
        ListNode dummyTwo = new ListNode(-1);
        ListNode cur1 = dummyOne, cur2 = dummyTwo, curr = head;
        while (curr != null) {
            if (curr.value < target) {
                cur1.next = curr;
                cur1 = cur1.next;
            } else {
                cur2.next = curr;
                cur2 = cur2.next;
            }
            curr = curr.next;
        }

        // connect two lists
        cur1.next = dummyTwo.next;
        cur2.next = null;
        return dummyOne.next;
    }

}
