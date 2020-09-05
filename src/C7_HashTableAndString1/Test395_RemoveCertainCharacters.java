package C7_HashTableAndString1;

import java.util.HashSet;

public class Test395_RemoveCertainCharacters {
	/**
	 * In place opertaion
	 * [0, i) reserved result
	 * [j, length) to be checked
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 **/
	public String remove(String input, String t) {
		char[] charArray = input.toCharArray();
		HashSet<Character> set = new HashSet<>();
		for (int i=0; i<t.length(); i++) {
			set.add(t.charAt(i));
		}

		int i = 0;
		int j = 0;
		while (j < charArray.length) {
			if (!set.contains(charArray[j])) {
				charArray[i++] = charArray[j++];
			} else {
				j++;
			}
		}
		// return the result subString
		return new String(charArray, 0, i);
	}

}
