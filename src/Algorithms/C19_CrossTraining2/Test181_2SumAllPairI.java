package Algorithms.C19_CrossTraining2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test181_2SumAllPairI {
	// unsorted array
	// 返回list of index pairs
	// 因为是list of index pair，实际上的数字可能会重复，比如<2, 4> <2, 4>中的2可能是不同的index，所以是两个结果
	public List<List<Integer>> allPairs(int[] array, int target) {
		// key = number value, value = list of index that number
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
