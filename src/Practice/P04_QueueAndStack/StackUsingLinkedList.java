package Practice.P04_QueueAndStack;

import util.ListNode;

public class StackUsingLinkedList {
    private ListNode head;
    private int size;

    public StackUsingLinkedList() {
        head = null;
        size = 0;
    }

    public void push(int value) {
        ListNode newHead = new ListNode(value);
        newHead.next = head;
        head = newHead;
        size++;
    }

    public Integer pop() {
        if (head == null) {
            return null;
        }
        ListNode newHead = head.next;
        ListNode toBeReturn = head;
        toBeReturn.next = null;
        head = newHead;
        size--;
        return toBeReturn.value;
    }

    public Integer peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
