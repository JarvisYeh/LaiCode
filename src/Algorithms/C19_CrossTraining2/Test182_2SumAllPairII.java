package Algorithms.C19_CrossTraining2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test182_2SumAllPairII {
	// unsorted array
	// 返回list of number pairs without duplication pairs
	public List<List<Integer>> allPairs(int[] array, int target) {
		// Hashmap stores number counts from [0, i)
		HashMap<Integer, Integer> countMap = new HashMap<>();
		List<List<Integer>> res= new ArrayList<>();

		for (int i = 0; i < array.length; i++) {
			int num = array[i];
			// number array[i] occurs previously
			if (countMap.containsKey(num)) {
				if (num * 2 == target && countMap.get(num) == 1) {
					res.add(Arrays.asList(num, num));
				}
				countMap.put(num, countMap.get(num) + 1);
			}
			// number array[i] first occurs, and target - array[i] exist in [0, i)
			else if (countMap.containsKey(target - num)) {
				res.add(Arrays.asList(num, target - num));
				countMap.put(num, 1);
			}
			// other cases: array[i] first occurs
			else {
				countMap.put(num, 1);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Test182_2SumAllPairII test = new Test182_2SumAllPairII();
		test.allPairs(new int[]{3,5,4,1,2}, 6);
	}
}
