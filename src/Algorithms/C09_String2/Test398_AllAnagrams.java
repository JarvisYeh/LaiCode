package Algorithms.C09_String2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Test398_AllAnagrams {
	/**
	 * 找到s1中的sub string
	 * 使该sub string和s2中的字母构成一致（类型，数量）
	 **/
	/**
	 * Solution 1
	 * 使用size = s2.length()的sliding window
	 * [left, right]为sliding window的范围
	 * hashmap中存<Character, Integer>代表sliding window中的字母种类和对应的数量
	 * Case 1: sliding window和s2的字母构成一致
	 * 讲left存入list，left++，right++，对应调整hashmap
	 * Case 2: 不一致
	 * left++, right++, 对应调整hashmap
	 * Time Complexity:
	 *  O(m*n) 每次都要checkEquals
	 * Sapce Complexity：
	 * 	O(2m) = O(m) 两个hashMap
	 **/
	public List<Integer> allAnagramsI(String target, String source) {
		List<Integer> res = new ArrayList<>();
		if (source == null || target == null) {
			return res;
		}
		if (source.length() == 0 || target.length() == 0 || source.length() < target.length()) {
			return res;
		}
		HashMap<Character, Integer> targetMap = new HashMap<>();
		HashMap<Character, Integer> windowMap = new HashMap<>();

		// obtain hashMap for sliding window and target string
		for (int i = 0; i < target.length(); i++) {
			checkAndUpdate(target.charAt(i), targetMap);
			checkAndUpdate(source.charAt(i), windowMap);
		}
		int left = 0, right = target.length() - 1;
		while (right < source.length()) {
			if (checkEquals(targetMap, windowMap)) {
				res.add(left);
			}
			left++;
			right++;

			// delete source[left - 1] and add source[right] into window hashmap
			// need to check if right is out of index
			if (right != source.length()) {
				windowMap.put(source.charAt(left - 1), windowMap.get(source.charAt(left - 1)) - 1);
				checkAndUpdate(source.charAt(right), windowMap);
			}

		}
		return res;
	}

	/**
	 * Update hashmap with new coming character c
	 * @param c
	 * @param map
	 */
	private void checkAndUpdate(Character c, HashMap<Character, Integer> map) {
		if (map.containsKey(c)) {
			map.put(c, map.get(c) + 1);
		} else {
			map.put(c, 1);
		}
	}

	/**
	 * Check if two hashmap contains same <key, value> pairs
	 * @param map1
	 * @param map2
	 * @return
	 */
	private boolean checkEquals(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
		Set<Character> set = map1.keySet();
		for (Character c : set) {
			if (map1.get(c) != map2.get(c)) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Solution 2
	 *
	 * 使用size = s2.length()的sliding window
	 * [left, right]为sliding window的范围
	 * hashmap中存<Character, Integer>代表sliding window中还需要的字母频率
	 * match存frequency符合s2的字母数量
	 *
	 * 当sliding window还没有走到尽头
	 * 	Case 1: s1[right] 是hashMap的key
	 * 		map.get(s1[right]) == 0
	 * 			match--并更新hashMap
	 * 		map.get(s1[right]) == 1
	 * 			match++并更新hashMap
	 * 	Case 2: s1[right] 不是hashmap的key
	 * 		skip
	 * 	Case 3: s1[left-1] 是hashMap的key
	 * 		map.get(s1[left-1]) == 0
	 * 			match--并更新hashMap
	 * 		map.get(s1[left-1]) == -1
	 * 			match++并更新hashMap
	 * 	Case 4: s1[left-1] 不是hashMap的key
	 * 		skip
	 * 	check match == HashMap.size()
	 * 	left++ right++
	 *
	 * Time Complexity:
	 *  O(n + m) checkMatch复杂度为O(1)
	 *  	一开始要讲s2中的frequency加入hashmap中并并且根据sliding window的初始元素更新hashMap
	 * Sapce Complexity：
	 * 	O(m) 一个hashMap
	 **/
	public List<Integer> allAnagramsII(String target, String source) {
		List<Integer> res = new ArrayList<>();
		if (target == null || source == null) {
			return res;
		}
		if (target.length() > source.length() || target.length() == 0) {
			return res;
		}

		int match = 0;
		HashMap<Character, Integer> map = new HashMap<>();

		// add all character frequency of s2 to hashMap
		for (int i=0; i<target.length(); i++) {
			Character c = target.charAt(i);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}

		// update character frequency w.r.t. initial sliding window
		for (int i=0; i<target.length(); i++) {
			if (map.containsKey(source.charAt(i))) {
				int count = map.get(source.charAt(i));
				if (count == 1) {
					match++;
				} else if (count == 0) {
					match--;
				}
				map.put(source.charAt(i), count - 1);
			}
		}

		// check if initial state matches s2
		if (match == map.size()) {
			res.add(0);
		}

		// move to next sliding window
		int left = 1, right = target.length();
		// loop start
		while (right < source.length()) {
			// if source[right] is in map
			if (map.containsKey(source.charAt(right))) {
				int count = map.get(source.charAt(right));
				map.put(source.charAt(right), --count);
				if (count == 0) {
					match++;
				}
				if (count == -1) {
					match--;
				}
			}

			// if source[left - 1] is in map
			if (map.containsKey(source.charAt(left - 1))) {
				int count = map.get(source.charAt(left - 1));
				map.put(source.charAt(left - 1), ++count);
				if (count == 0) {
					match++;
				}
				if (count == 1) {
					match--;
				}
			}

			// check match
			if (match == map.size()) {
				res.add(left);
			}

			// move window forward
			left++;
			right++;
		}
		return res;
	}

	public static void main(String[] args) {
		Test398_AllAnagrams test = new Test398_AllAnagrams();
		test.allAnagramsII("aa", "baaaa");
	}
}
