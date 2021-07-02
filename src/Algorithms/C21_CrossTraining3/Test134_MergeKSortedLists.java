package Algorithms.C21_CrossTraining3;

import util.ListNode;

import java.util.*;

public class Test134_MergeKSortedLists {
	/**
	 * 使用minHeap来存每个list的current ListNode
	 * 每次连接从minHeap中poll出的ListNode
	 * Time Complexity: O(kn*logk)
	 * Space Complexity: O(k)
	 **/
	public ListNode mergeI(List<ListNode> listOfLists) {
		Queue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return Integer.compare(o1.value, o2.value);
			}
		});

		// offer each head, now k nodes in minHeap
		for (ListNode node : listOfLists) {
			if (node != null) {
				minHeap.offer(node);
			}
		}

		ListNode dummyHead = new ListNode(-1);
		ListNode curr = dummyHead;
		while (!minHeap.isEmpty()) {
			ListNode next = minHeap.poll();
			if (next.next != null) {
				minHeap.offer(next.next);
			}
			curr.next = next;
			curr = curr.next;
		}
		return dummyHead.next;
	}


	public ListNode mergeII(List<ListNode> listOfLists) {
		if (listOfLists == null || listOfLists.size() == 0) return null;
		if (listOfLists.size() == 1) return listOfLists.get(0);
		int mid = listOfLists.size()/2;
		ListNode left = mergeII(listOfLists.subList(0, mid));
		ListNode right = mergeII(listOfLists.subList(mid, listOfLists.size()));
		return mregeTwoList(left, right);
	}

	private ListNode mregeTwoList(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(-1);
		ListNode prev = dummyHead;
		while (l1 != null && l2 != null) {
			if (l1.value <= l2.value) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}
		if (l1 != null) prev.next = l1;
		if (l2 != null) prev.next = l2;
		return dummyHead.next;
	}

	public static void main(String[] args) {
		Test134_MergeKSortedLists t = new Test134_MergeKSortedLists();
		List<ListNode> lists = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			ListNode tmp = ListNode.fromArray(new int[]{i, i, i});
			lists.add(tmp);
		}
		lists.add(ListNode.fromArray(new int[]{1, 2, 3}));

		ListNode.printAll(t.mergeII(lists));
	}
}
