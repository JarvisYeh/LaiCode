package Algorithms.C24_DynamicProgramming4;

import java.util.ArrayList;
import java.util.List;

public class Test1_LongestAscendingSubsequence {
	// TC: O(n^2)
	// SC: O(n)
	public int longestI(int[] nums) {
		// DP[i]: longest length of increasing subsequence ending at nums[i]
		int[] DP = new int[nums.length];
		int max = 1;
		for (int i = 0; i < nums.length; i++) {
			// init DP[i] = 1, which is the minimal possible, nums[i] itself as a subsequence
			DP[i] = 1;
			// check j in [0, i), if nums[j] < nums[i]
			// nums[j] is a candidate of the number in subsequence before nums[i]
			// update DP[i] if necessary
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i])
					DP[i] = Math.max(DP[i], DP[j] + 1);
			}
			// update max when DP[i] is settled
			max = Math.max(max, DP[i]);
		}
		return max;
	}

	// TC: O(nlogn)
	// SC: O(n)
	public int longestII(int[] nums) {
		List<Integer> lowestEnd = new ArrayList<>();;
		lowestEnd.add(-1);
		for (int i = 0; i < nums.length; i++) {
			int curr = nums[i];
			int l = 1, r = lowestEnd.size() - 1;
			// binary search for smallest among > curr numbers
			while (l <= r) {
				int mid = l + (r - l)/2;
				if (lowestEnd.get(mid) < curr) {
					l = mid + 1;
				}
				// now list[mid] >= curr
				// if list[mid - 1] also >= curr
				// mid is not the target
				else if (mid != 1 && lowestEnd.get(mid - 1) >= curr){
					r = mid - 1;
				}
				// now list[mid] >= curr && (mid is first number || list[mid - 1] < curr)
				else {
					l = mid;
					break;
				}
			}

			if (l <= r) {   // early break, target found
				lowestEnd.set(l, curr);
			} else {		// search range < 0, target not found, all number <= curr
				lowestEnd.add(curr);
			}
		}
		return lowestEnd.size() - 1;
	}


	public static void main(String[] args) {
		Test1_LongestAscendingSubsequence t = new Test1_LongestAscendingSubsequence();
		t.longestII(new int[]{9,32,16,21,21});
	}
}
