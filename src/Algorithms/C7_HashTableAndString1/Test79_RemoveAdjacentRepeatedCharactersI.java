package Algorithms.C7_HashTableAndString1;

public class Test79_RemoveAdjacentRepeatedCharactersI {
	/**
	 * [0, i) is the reversed result
	 * [j, length) is the array to be checked
	 * Case 1. first character to be checked
	 * 	keep it
	 * Case 2. not the same as previous reserved character
	 * 	keep it
	 * Case 3. same as the previous reserved character
	 * 	skip it
	 **/
	public String deDup(String input) {
		char[] charArray = input.toCharArray();
		int i = 0, j = 0;
		while (j < charArray.length) {
			if (i == 0 || charArray[j] != charArray[i - 1]) {
				charArray[i++] = charArray[j++];
			} else {
				j++;
			}
		}
		return new String(charArray, 0, i);
	}


}
