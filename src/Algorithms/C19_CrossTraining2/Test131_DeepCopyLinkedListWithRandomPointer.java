package Algorithms.C19_CrossTraining2;

import java.util.HashMap;
import java.util.Map;

public class Test131_DeepCopyLinkedListWithRandomPointer {
	public RandomListNode copy(RandomListNode head) {
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode dummyHead = new RandomListNode(-1);
		RandomListNode newCurr = dummyHead;
		while (head != null) {
			if (map.containsKey(head)) {
				newCurr.next = map.get(head);
			} else {
				newCurr.next = new RandomListNode(head.value);
				map.put(head, newCurr.next);
			}

			if (head.random != null) {
				if (map.containsKey(head.random)) {
					newCurr.next.random = map.get(head.random);
				} else {
					newCurr.next.random = new RandomListNode(head.random.value);
					map.put(head.random, newCurr.next.random);
				}
			}
			newCurr = newCurr.next;
			head = head.next;
		}
		return dummyHead.next;
	}



	static class RandomListNode {
		public int value;
		public RandomListNode next;
		public RandomListNode random;

		public RandomListNode(int value) {
			this.value = value;
		}
	}

}
