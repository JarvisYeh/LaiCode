package Algorithms.C7_HashTableAndString1;
import java.util.*;

public class Test67_TopKFrequentWords {
	public String[] topKFrequent(String[] combo, int k) {
		if (combo == null || k <= 0) {
			return null;
		}
		HashMap<String, Integer> map = getMap(combo);
		return topK(map, k);
	}

	private HashMap<String, Integer> getMap(String[] combo) {
		HashMap<String, Integer> map = new HashMap<>();
		for (String word : combo) {
			if (map.containsKey(word)) {
				map.put(word, map.get(word) + 1);
			} else {
				map.put(word, 1);
			}
		}
		return map;
	}

	private String[] topK(HashMap<String, Integer> map, int k) {

		Queue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
				if (e1.getValue() == e2.getValue()) {
					return 0;
				}
				return e1.getValue() < e2.getValue() ? -1 : 1;
			}
		});
		for (Map.Entry<String, Integer> e : map.entrySet()) {
			if (minHeap.size() < k) {
				minHeap.offer(e);
			} else if (minHeap.peek().getValue() < e.getValue()) {
				minHeap.poll();
				minHeap.offer(e);
			}
		}

		String[] result = new String[minHeap.size()];
		for (int i=minHeap.size()-1; i>=0; i--) {
			result[i] = minHeap.poll().getKey();
		}

		return result;
	}
}
