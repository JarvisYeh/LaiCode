package Practice;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import util.ListNode;

class MyLinkedList {
    private ListNode head;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode curr = this.head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode tmp = new ListNode(val);
        tmp.next = this.head;
        this.size++;
        this.head = tmp;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        if (size == 0) {
            this.head = new ListNode(val);
        } else {
            ListNode curr = head;
            for (int i = 0; i < this.size - 1; i++) {
                curr = curr.next;
            }
            curr.next = new ListNode(val);
        }
        this.size++;

    }

    /**
     * Add a node of value val before the index-th node in the linked list.
     * If index equals to the length of linked list, the node will be appended to the end of linked list.
     * If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.size) {
            return;
        } else if (index == this.size) {
            addAtTail(val);
        } else if (index == 0) {
            addAtHead(val);
        } else {
            ListNode curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            ListNode tmp = new ListNode(val);
            tmp.next = curr.next;
            curr.next = tmp;
            this.size++;
        }
    }


    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        if (index == 0) {
            this.head = head.next;
        } else {
            ListNode curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
        }
        this.size--;
    }

    public void printAll() {
        ListNode curr = head;
        while(curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();

        obj.addAtHead(1);
        obj.printAll();

        obj.addAtTail(3);
        obj.printAll();

        obj.addAtIndex(1, 2);
        obj.printAll();

        System.out.println(obj.get(1));

        obj.deleteAtIndex(1);
        obj.printAll();

        System.out.println(obj.get(1));
    }
}
