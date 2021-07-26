package Algorithms.C03_LinkedList;

import util.ListNode;

public class Test306_CheckIfLinkedListIsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1 2 3 2 1 ->
        // one: 1 2 3
        // two: 1 2
        ListNode middleNode = findMiddle(head);
        ListNode one = head;
        ListNode two = reverse(middleNode.next);
        middleNode.next = null;

        while (one != null && two != null) {
            if (one.value != two.value) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return true;
    }

    private ListNode findMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
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
        ListNode input = ListNode.fromArray(new int[]{1, 3, 3, 4, 3, 3, 1});
        Test306_CheckIfLinkedListIsPalindrome test = new Test306_CheckIfLinkedListIsPalindrome();
        System.out.println(test.isPalindrome(input));
    }


}
