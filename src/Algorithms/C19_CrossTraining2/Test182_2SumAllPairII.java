package Algorithms.C19_CrossTraining2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test182_2SumAllPairII {
	// unsorted array
	// 返回list of number pairs without duplication pairs
	// TC: O(n)
	// SC: O(n)
	public List<List<Integer>> allPairsI(int[] array, int target) {
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

	// sort first then use two pointers
	// TC: O(nlogn)
	// SC: O(1) if use heap sort
	public List<List<Integer>> allPairsII(int[] array, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(array);
		int i = 0, j = array.length - 1;
		while (i < j) {
			if (array[i] + array[j] == target) {
				res.add(Arrays.asList(new Integer[]{array[i], array[j]}));
				while (i < j && array[i] == array[i + 1]) i++;
				while (i < j && array[j] == array[j - 1]) j--;
				// now array[i] != array[i + 1] and array[j] != array[j - 1]
				i++; j--;
			} else if (array[i] + array[j] > target) {
				j--;
			} else {
				i++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Test182_2SumAllPairII test = new Test182_2SumAllPairII();
		test.allPairsII(new int[]{3,5,4,1,2}, 6);
	}
}
