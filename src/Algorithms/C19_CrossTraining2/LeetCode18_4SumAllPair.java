package Algorithms.C19_CrossTraining2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode18_4SumAllPair {
	// 4 sum return all paris without duplication
	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (i != 0 && nums[i] == nums[i - 1]) continue;
			// do sorted 3 sum for (i + 1, len)
			for (int j = i + 1; j < n; j++) {
				if (j != i + 1 && nums[j - 1] == nums[j]) continue;
				// do sorted 2 sum for (j + 1, len)
				int x = j + 1, y = n - 1;
				while (x < y) {
					if (nums[x] + nums[y] > target - nums[i] - nums[j]) {
						y--;
					} else if (nums[x] + nums[y] < target - nums[i] - nums[j]) {
						x++;
					} else {
						res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[x], nums[y])));
						// skip duplicates
						while (x < y && nums[x] == nums[x + 1]) x++;
						while (x < y && nums[y] == nums[y - 1]) y--;
						x++;
						y--;
					}
				}
			}
		}
		return res;
	}

	// generalize to k-sum w.r.t. sorted array
	// return all combination without duplications
	public List<List<Integer>> kSum(int[] nums, int l, int r, int target, int k) {
		if (k == 2) {
			List<List<Integer>> twoSumPairs = new ArrayList<>();
			while (l < r) {
				if (nums[l] + nums[r] > target) {
					r--;
				} else if (nums[l] + nums[r] < target) {
					l++;
				} else {
					twoSumPairs.add(new ArrayList<>(Arrays.asList(nums[l], nums[r])));
					// skip duplicates
					while (l < r && nums[l] == nums[l + 1]) l++;
					while (l < r && nums[r] == nums[r - 1]) r--;
					l++;
					r--;
				}
			}
			return twoSumPairs;
		} else {
			List<List<Integer>> res = new ArrayList<>();
			for (int i = l; i <= r; i++) {
				// skip duplicates
				if (i > l && nums[i] == nums[i - 1]) continue;
				for (List<Integer> tmp : kSum(nums, i + 1, r, target - nums[i], k - 1)) {
					tmp.add(nums[i]);
					res.add(tmp);
				}
			}
			return res;
		}
	}

	public static void main(String[] args) {
		LeetCode18_4SumAllPair t = new LeetCode18_4SumAllPair();
		t.fourSum(new int[]{2,2,2,2,2}, 8);
	}
}
