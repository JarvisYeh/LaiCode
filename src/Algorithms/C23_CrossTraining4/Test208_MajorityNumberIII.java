package Algorithms.C23_CrossTraining4;

import java.util.*;

public class Test208_MajorityNumberIII {
	public List<Integer> majority(int[] array, int k) {
		HashMap<Integer, Integer> candMap = new HashMap<>();
		// O(n)
		for (int num : array) {
			// if num is same as one of the candidate, map[num]++
			if (candMap.containsKey(num)) {
				candMap.put(num, candMap.get(num) + 1);
				continue;
			}
			// if map size < k - 1
			if (candMap.size() < k - 1) {
				candMap.put(num, 1);
			}
			// map size >= k - 1, decrease all counter and remove entry with count = 0
			else {
				Iterator<Map.Entry<Integer, Integer>> iter = candMap.entrySet().iterator();
				// the total number of decreasing count/delete is at most n despite the outer for loop
				// O(n)
				while (iter.hasNext()) {
					Map.Entry<Integer, Integer> curr = iter.next();
					if (curr.getValue() == 1)
						iter.remove();
					else
						curr.setValue(curr.getValue() - 1);
				}
			}
		}

		// post process: count frequency of each number
		// O(2k + n)
		for (Map.Entry<Integer, Integer> e : candMap.entrySet()) {
			e.setValue(0);
		}
		for (int num : array) {
			if (candMap.containsKey(num))
				candMap.put(num, candMap.get(num) + 1);
		}
		List<Integer> res = new ArrayList<>();
		for (Map.Entry<Integer, Integer> e : candMap.entrySet()) {
			if (e.getValue() > array.length / k)
				res.add(e.getKey());
		}
		return res;
	}
}
