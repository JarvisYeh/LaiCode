package Algorithms.C19_CrossTraining2;

import java.util.HashSet;

public class Test180_2Sum {
	// 2 sum for unsorted array
	// Hashset 存[0, i)的元素防止重复
	public boolean existSumI(int[] array, int target) {
		//
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < array.length; i++) {
			if (set.contains(target - array[i])){
				return true;
			}
			set.add(array[i]);
		}
		return false;
	}

	// 2 sum for sorted array
	// use two pointer, i at 0, j at arr.length - 1
	public boolean existSumII(int[] array, int target) {
		int i = 0, j = array.length - 1;
		while (i < j) {
			if (array[i] + array[j] < target) {
				i++;
			} else if (array[i] + array[j] > target){
				j--;
			} else {
				return true;
			}
		}
		return false;
	}


}
