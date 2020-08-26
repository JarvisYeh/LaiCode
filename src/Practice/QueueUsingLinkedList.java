package Practice;

import util.ListNode;

public class QueueUsingLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;

    public QueueUsingLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void offer(int value) {
        ListNode newTail = new ListNode(value);
        if (size == 0) {
            head = newTail;
            tail = newTail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }
        size++;
    }

    public Integer poll() {
        if (size == 0) {
            return null;
        }

        ListNode toBeReturn = head;
        head = head.next;
        // if previous there is only one node in Queue
        if (head == tail) {
            tail = null;
        }
        toBeReturn.next = null;
        size--;
        return toBeReturn.value;
    }

    public Integer peek() {
        if (size == 0) {
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
