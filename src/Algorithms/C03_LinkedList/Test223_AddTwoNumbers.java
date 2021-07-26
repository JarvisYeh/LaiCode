package Algorithms.C03_LinkedList;

import util.ListNode;

public class Test223_AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode one, ListNode two) {
        // step 1: reverse two linked list
        one = reverse(one);
        two = reverse(two);
        ListNode dummyHead = new ListNode(-1);
        ListNode curr = dummyHead;

        // step 2: add corresponding position number with carry
        int carry = 0;
        while (one != null || two != null || carry > 0) {
            int value = carry;
            if (one != null) {
                value += one.value;
                one = one.next;
            }
            if (two != null) {
                value += two.value;
                two = two.next;
            }
            carry = value/10;
            curr.next = new ListNode(value % 10);
            curr = curr.next;
        }
        return reverse(dummyHead.next);
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode one = ListNode.fromArray(new int[]{1, 2, 6});
        ListNode two = ListNode.fromArray(new int[]{6, 3});
        Test223_AddTwoNumbers test = new Test223_AddTwoNumbers();
        ListNode.printAll(test.addTwoNumbers(one, two));
    }
}
