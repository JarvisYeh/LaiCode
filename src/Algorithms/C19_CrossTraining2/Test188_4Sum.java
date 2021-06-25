package Algorithms.C19_CrossTraining2;

import java.util.*;

// 4 sum return true/false
// just want true and false, so no need to consider de-duplication
public class Test188_4Sum {

	// for i in [0, len), use 3 sum
	// for sorted use two pointers, for unsorted use hashset
	// here use hashset
	// TC: O(n^3)
	// SC: O(n)
	public boolean existI(int[] array, int target) {
		for (int i = 0; i < array.length; i++) {
			// do 3 sum in [0, i), target = target - array[i]
			for (int j = 0; j < i; j++) {
				// do 2 sum in [0, j), target = target - array[i] - array[j]
				HashSet<Integer> set = new HashSet<>();
				int targetFor2Sum = target - array[i] - array[j];
				for (int k = 0; k < j; k++) {
					if (set.contains(targetFor2Sum - array[k])) {
						return true;
					}
					set.add(array[k]);
				}
			}
		}
		return false;
	}


	// step 1: 生成map，key = arr[i]+arr[j]，value = <i, j> pairs
	// step 2: 遍历所有i, j
	// 	如果target - arr[i] - arr[j]在map中出现
	// 	检查每个pairs中的index和i,j是否有重复，没有重复直接return true
	// TC: O(n^3)
	// SC: worst O(n^2), every two numbers can sum to different values, n^2 pairs
	public boolean existII(int[] array, int target) {
		HashMap<Integer, List<int[]>> sumMap = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				int sum = array[i] + array[j];
				if (!sumMap.containsKey(sum)) {
					sumMap.put(sum, new ArrayList<>());
				}
				sumMap.get(sum).add(new int[]{i, j});
			}
		}

		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				int sum = array[i] + array[j];
				if (sumMap.containsKey(target - sum)) {
					for (int[] pair : sumMap.get(target - sum)) {
						if (pair[0] != i && pair[0] != j && pair[1] != i && pair[1] != j) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	// only keep one pair for each two sum
	// whose <i, j> has the smallest j and i < j
	// TC: O(n^2)
	// SC: worst O(n^2)
	public boolean existIII(int[] array, int target) {
		HashMap<Integer, int[]> map = new HashMap<>();

		// merge two steps together
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				int pairSum = array[i] + array[j];
				// try to find {i2, j2} = map[target - nums[i1] - nums[j1] and make sure j2 < i1
				if (map.containsKey(target - pairSum) && map.get(target - pairSum)[1] < i) {
					return true;
				}

				// if the pair doesn't appears before
				// or the new j is less than old j, update map
				if (!map.containsKey(pairSum) || j < map.get(pairSum)[1]) {
					// since i < j, create {i, j} pairs, so in map pair[0] < pair[1]
					map.put(pairSum, new int[]{i, j});
				}
			}
		}
		return false;
	}
}
