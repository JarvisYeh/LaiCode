package Algorithms.C3_LinkedList;

import util.ListNode;

public class Test41_ReOrderLinkedList {
    public ListNode reOrderLinkedList(util.ListNode head) {
        // find middle node and split the list
        ListNode middleNode = findMiddle(head);
        ListNode one = head;
        ListNode two = middleNode.next;
        middleNode.next = null;

        // reverse the second half
        two = reverseLinkedList(two);

        // merge two list and return new head;
        return merge(one, two);
    }


    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    private ListNode reverseLinkedList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode curr = head, prev = null;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;
        while (one != null && two != null) {
            curr.next = one;
            one = one.next;
            curr = curr.next;

            curr.next = two;
            two = two.next;
            curr = curr.next;
        }
        if (one != null) {
            curr.next = one;
        }
        if (two != null) {
            curr.next = two;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Test41_ReOrderLinkedList test = new Test41_ReOrderLinkedList();
        ListNode input = ListNode.fromArray(new int[]{1});
        ListNode.printAll(input);
        ListNode.printAll(test.reOrderLinkedList(input));
    }
}
