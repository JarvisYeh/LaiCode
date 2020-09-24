package Algorithms.C9_String2;

public class Test84_ReverseWordsInASentenceI {
	/**
	 * i point to the start of a word
	 * j point to the next position of the end of the word
	 **/
	public String reverseWords(String input) {
		char[] charArray = input.toCharArray();
		int i = 0, j = 0;
		while (i < charArray.length) {
			// let j stop at the array.length, one position after the end of the sentence
			while (j < charArray.length && charArray[j] != ' ') {
				j++;
			}
			reverse(charArray, i, j - 1);
			j = j+1;
			i = j;
		}
		reverse(charArray, 0, charArray.length - 1);
		return new String(charArray);
	}

	private void reverse(char[] input, int start, int end) {
		int i = start, j = end;
		while (i <= j) {
			char tmp = input[i];
			input[i++] = input[j];
			input[j--] = tmp;
		}
	}

}
