package Algorithms.C19_CrossTraining2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test188_4Sum {
	// return true/false
	// step 1: 生成map，key = arr[i]+arr[j]，value = <i, j>
	// step 2: 遍历所有i, j
	// 	如果target - arr[i] - arr[j]在map中出现
	// 	检查每个pairs中的index和i,j是否有重复，没有重复直接return true
	public boolean exist(int[] array, int target) {
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

}
