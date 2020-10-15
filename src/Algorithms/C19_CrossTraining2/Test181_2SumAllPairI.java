package Algorithms.C19_CrossTraining2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test181_2SumAllPairI {
	// unsorted array
	// 返回list of index pairs
	public List<List<Integer>> allPairs(int[] array, int target) {
		// indexMap中存[0, i)的数据分布情况
		// key = number value, value = index list of that number
		HashMap<Integer, List<Integer>> indexMap = new HashMap<>();

		// result list
		List<List<Integer>> res = new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			// find possible results for array[i]
			if (indexMap.containsKey(target - array[i])) {
				for (Integer index : indexMap.get(target - array[i])) {
					res.add(Arrays.asList(i, index));
				}
			}

			// update hashmap with array[i]
			if (!indexMap.containsKey(array[i])) {
				indexMap.put(array[i], new ArrayList<>());
			}
			indexMap.get(array[i]).add(i);
		}
		return res;
	}

}
