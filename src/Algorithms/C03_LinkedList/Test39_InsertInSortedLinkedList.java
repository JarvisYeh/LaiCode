package Algorithms.C03_LinkedList;

import util.ListNode;

import java.util.ArrayList;

public class Test39_InsertInSortedLinkedList {
    // without dummyHead
    public ListNode insertIntoLinkedList(ListNode head, int target) {
        ArrayList<Integer> a = new ArrayList<>();
        ListNode tmp = new ListNode(target);
        // case 1: head will change
        if (head == null || head.value >= target) {
            tmp.next = head;
            return tmp;
        }

        // case 2: head remains unchanged
        ListNode prev = head;
        while (prev.next != null && prev.next.value < target) {
            prev = prev.next;
        }
        tmp.next = prev.next;
        prev.next = tmp;
        return tmp;
    }

    // with dummyHead
    public ListNode insertIntoSortedLinkedListwithDummyHead(ListNode head, int value) {
        ListNode newNode = new ListNode(value);
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        while (curr != null && curr.value < value) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = newNode;
        newNode.next = curr;
        return dummy.next;
    }

}
