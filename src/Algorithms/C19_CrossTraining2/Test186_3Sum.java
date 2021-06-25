package Algorithms.C19_CrossTraining2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test186_3Sum {
// 返回所有three-sum tuples
// unsorted array
public List<List<Integer>> allTriples(int[] array, int target) {
	List<List<Integer>> res = new ArrayList<>();
	// sort first
	Arrays.sort(array);
	for (int i = 0; i < array.length; i++) {
		// skip duplicate elements
		if (i - 1 >= 0 && array[i] == array[i - 1]) {
			continue;
		}
		// do two sum for range (i, end)
		int j = i + 1, k = array.length - 1;
		int innerTarget = target - array[i];
		while (j < k) {
			if (array[j] + array[k] < innerTarget) {
				j++;
			} else if (array[j] + array[k] > innerTarget) {
				k--;
			} else {
				res.add(Arrays.asList(array[i], array[j], array[k]));
				j++;
				k--;
				// skip all duplicate j, it is ok to skip k as well but no need
				while (j < k && array[j] == array[j - 1]) {
					j++;
				}
			}
		}
	}
	return res;
}

	public static void main(String[] args) {
		Test186_3Sum test = new Test186_3Sum();
		test.allTriples(new int[]{1,1,1,1,1,1,1,1,1,1,1}, 3);
	}
}
