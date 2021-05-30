package Algorithms.C8_HashTableAndString1;

public class Test281_RemoveSpaces {
	/**
	 * [0, i) is the reversed result
	 * [j, length) is the array to be checked
	 * 1. if arr[j] is a character, keep it
	 * 2. if arr[j] is a space
	 * 	2.1. if i = 0, it is the first character, remove space
	 * 	2.2. if arr[i - 1] is a character, keep the space
	 *	2.3. if arr[i - 1] is a space, remove space
	 **/
	public String removeSpaces(String input) {
		char[] charArray = input.toCharArray();
		int i = 0, j = 0;
		while (j < charArray.length) {
			// case 1
			if (charArray[j] != ' ') {
				charArray[i++] = charArray[j++];
			}
			// case 2.1 & 2.3
			else if (i == 0 || charArray[i-1] == ' ') {
				j++;
			}
			// case 2.2
			else {
				charArray[i++] = charArray[j++];
			}
		}

		// post-processing
		// remove the last space
		// however need to consider about corner case that input string contains only spaces
		if (i != 0 && charArray[i-1] == ' ') {
			return new String(charArray, 0, i - 1);
		}
		return new String(charArray, 0, i);
	}

}
