package C3_LinkedList;

import util.ListNode;

import java.util.List;

public class Test40_MergeTwoSortedLinkedLists {
    /**
     * Use dummy head since the head is not certain, therefore there will be various of corner case
     **/
    public ListNode mergeTwoSortedList(ListNode one, ListNode two) {
        // corner case
        // actually could be deal as well, no need for corner case
        if (one == null) {
            return two;
        }
        if (two == null) {
            return one;
        }

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

    public static void main(String[] args) {
        ListNode one = ListNode.fromArray(new int[]{1,3,5,7,9});
        ListNode two = ListNode.fromArray(new int[]{2,4,6,8,10});
        Test40_MergeTwoSortedLinkedLists test = new Test40_MergeTwoSortedLinkedLists();
        ListNode.printAll(test.mergeTwoSortedList(one, two));
    }

}
