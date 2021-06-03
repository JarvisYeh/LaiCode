package Algorithms.C9_String2;

import java.util.Comparator;

public class Test_StringShuffling {
	// sort string
	// alphabetic before digit
	public String stringShuffling(String input) {
		char[] arr = input.toCharArray();
		Comparator<Character> comp = new MyComparator();
		return new String(mergeSort(arr, 0, arr.length - 1, comp));
	}

	private char[] mergeSort(char[] arr, int l, int r, Comparator<Character> comp) {
		if (l >= r) {
			return new char[]{arr[l]};
		}
		int mid = l + ((r - l) >> 1);
		char[] left = mergeSort(arr, l, mid, comp);
		char[] right = mergeSort(arr, mid + 1, r, comp);
		return merge(left, right, comp);
	}

	private char[] merge(char[] l, char[] r, Comparator<Character> comp) {
		char[] res = new char[l.length + r.length];
		int i = 0, j = 0, resIndex = 0;
		while (i < l.length && j < r.length) {
			if (comp.compare(l[i], r[j]) < 0) {
				res[resIndex++] = l[i++];
			} else {
				res[resIndex++] = r[j++];
			}
		}

		while (i < l.length) {
			res[resIndex++] = l[i++];
		}
		while (j < r.length) {
			res[resIndex++] = r[j++];
		}
		return res;
	}


	private class MyComparator implements Comparator<Character> {
		@Override
		public int compare(Character o1, Character o2) {
			// both o1 and o2 are alphabetic
			if (Character.isAlphabetic(o1) && Character.isAlphabetic(o2) || Character.isDigit(o1) && Character.isDigit(o2)) {
				return Character.compare(o1, o2);
			}

			// only o1 is alphabetic, o1 has more priority
			if (Character.isAlphabetic(o1)) {
				return -1;
			}

			// only o2 is alphabetic, o2 has more priority
			return 1;
		}
	}

	public static void main(String[] args) {
		Test_StringShuffling t = new Test_StringShuffling();
		System.out.println(t.stringShuffling("a1b2c3d4"));
	}
}
