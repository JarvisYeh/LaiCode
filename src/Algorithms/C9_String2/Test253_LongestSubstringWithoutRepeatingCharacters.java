package Algorithms.C9_String2;

import java.util.HashSet;

public class Test253_LongestSubstringWithoutRepeatingCharacters {
/**
 * [left, right] 为sliding window
 * 每次检查right + 1
 * case 1: 没有duplicate
 * 	right++，将input[right+1]加入hashset
 * 	更新max_size
 * case 2：有duplicate
 *  left++，将input[left]踢出hashset
 *  此时window大小一定变小，不需要更新max_size
 */
public int longest(String input) {
	if (input == null || input.length() == 0) {
		return 0;
	}

	int max_size = 0;
	int left = 0, right = 0;
	HashSet<Character> set = new HashSet<>();
	set.add(input.charAt(left));
	while (right + 1 < input.length()) {
		if (!set.contains(input.charAt(right + 1))) {
			set.add(input.charAt(right + 1));
			right++;
			max_size = max_size > (right - left + 1) ? max_size : (right - left + 1);
		} else {
			set.remove(input.charAt(left));
			left++;
		}
	}
	return max_size;
}
}
