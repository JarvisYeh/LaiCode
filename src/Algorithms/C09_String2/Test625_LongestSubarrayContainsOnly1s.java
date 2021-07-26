package Algorithms.C09_String2;

public class Test625_LongestSubarrayContainsOnly1s {
	/**
	 * returns the length of the longest substring contains only 1s
	 * after flipping at most k 0s
	 *
	 * [left, right) is the range of sliding window
	 * right - left is the current size
	 **/
	public int longestConsecutiveOnes(int[] nums, int k) {
		// corner case
		if (nums == null || nums.length == 0) {
			return 0;
		}

		// initialization
		int left = 0, right = 0;
		int countZeros = 0, maxSize = 0;

		while (right < nums.length) {
			if (nums[right] == 1) {
				right++;
				maxSize = Math.max(maxSize, right - left);
			} else {
				if (countZeros < k) {
					countZeros++;
					right++;
					maxSize = Math.max(maxSize, right - left);
				} else {
					left++;
					if (nums[left - 1] == 0){
						countZeros--;
					}
				}
			}
		}
		return maxSize;
	}



	public static void main(String[] args) {
		Test625_LongestSubarrayContainsOnly1s test = new Test625_LongestSubarrayContainsOnly1s();
		System.out.println(test.longestConsecutiveOnes(new int[]{0, 1, 1, 1}, 0));
	}
}
