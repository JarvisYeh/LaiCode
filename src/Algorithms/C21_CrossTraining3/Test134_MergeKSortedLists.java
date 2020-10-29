package Algorithms.C21_CrossTraining3;

import util.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test134_MergeKSortedLists {
	/**
	 * 使用minHeap来存每个list的current ListNode
	 * 每次连接从minHeap中poll出的ListNode
	 * Time Complexity: O(kn*logk)
	 * Space Complexity: O(k)
	 **/
	public ListNode merge(List<ListNode> listOfLists) {
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

}
