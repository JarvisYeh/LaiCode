package Algorithms.C7_DFS;

import java.util.ArrayList;
import java.util.List;

public class Test62_AllSubsetsI {
	/**
	 * Each level has states represents [add/not add] one letter
	 * Level amount = letter amount
	 * index represents the level
	 * Time Complexity: O(2^n)
	 * Space Complexity: Stack: O(n)
	 **/
	public List<String> subSets(String set) {
		List<String> result = new ArrayList<>();
		if (set == null) {
			return result;
		}
		char[] charArray = set.toCharArray();
		StringBuilder solutionPrefix = new StringBuilder();
		findSubSet(charArray, result, solutionPrefix, 0);
		return result;
	}

	private void findSubSet(char[] charArray, List<String> result, StringBuilder solutionPrefix, int index) {
		if (index == charArray.length) {
			result.add(solutionPrefix.toString());
			return;
		}

		// add this level letter
		solutionPrefix.append(charArray[index]);
		findSubSet(charArray, result, solutionPrefix, index + 1);

		// don’t add this level letter
		solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
		findSubSet(charArray, result, solutionPrefix, index + 1);
	}


	/**
	 * Method 2: bit manipulation
	 * @param set
	 * @return
	 */
	public List<String> subSetsII(String set) {
		List<String> res = new ArrayList<>();
		int total = 1 << set.length();
		for (int i = 0; i < total; i++) { // len(nums) = 2 => i = 00, 01, 10, 11
			StringBuilder sb = new StringBuilder();
			// check each position of nums
			// len(nums) = 2 => j = position 0, position 1
			for (int j = 0; j < set.length(); j++) {
				// if j-th bit is 1, meaning position j is in this specific subset
				// add set[j] into this subset
				if ((i >> j & 1) == 1) {
					sb.append(set.charAt(j));
				}
			}
			res.add(sb.toString());
		}
		return res;
	}

	public static void main(String[] args) {
		Test62_AllSubsetsI test = new Test62_AllSubsetsI();
		List<String> res = test.subSets("abc");
		System.out.println(res);

	}

}
