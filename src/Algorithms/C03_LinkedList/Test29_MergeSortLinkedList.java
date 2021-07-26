package Algorithms.C03_LinkedList;

import util.ListNode;

public class Test29_MergeSortLinkedList {
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middleNode = findMiddle(head);
        ListNode two = middleNode.next;
        middleNode.next = null;

        return merge(mergeSort(head), mergeSort(two));
    }

    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;
        while (one != null && two != null) {
            if (one.value < two.value) {
                curr.next = one;
                one = one.next;
            } else {
                curr.next = two;
                two = two.next;
            }
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

    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }
        return s;
    }

    public static void main(String[] args) {
        Test29_MergeSortLinkedList test = new Test29_MergeSortLinkedList();
        ListNode head = ListNode.fromArray(new int[]{1, 2, 3});
        ListNode.printAll(test.mergeSort(head));
    }

}
