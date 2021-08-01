package Algorithms.C26_Final;

public class Test_LoopAroundDiningTable {
	// input array of String
	// return if it's possible to have a loop so that
	// the last letter is previous word is the same as first letter in next word
	// TC: O(n!)
	// SC: O(n)
	public boolean findMatch(String[] input) {
		if (input == null || input.length == 0) {
			return true;
		}

		return findPermutation(input, 0);
	}

	private boolean findPermutation(String[] arr, int idx) {
		// base case
		if (idx == arr.length) {
			int lenLast = arr[idx - 1].length();
			return arr[0].charAt(0) == arr[idx - 1].charAt(lenLast - 1);
		}

		for (int i = idx; i < arr.length; i++) {
			// every word can be the first in array
			if (idx == 0 || arr[idx - 1].charAt(arr[idx - 1].length() - 1) == arr[i].charAt(0)) {
				swap(arr, idx, i);
				if (findPermutation(arr, idx + 1)) {
					return true;
				}
				swap(arr, idx, i);
			}
		}
		return false;
	}

	private void swap(String[] arr, int i, int j) {
		String tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		Test_LoopAroundDiningTable t = new Test_LoopAroundDiningTable();
		String[] arr = new String[]{"ALICE", "ERIC", "CHARLES", "SOPHIA"};
		System.out.println(t.findMatch(arr));
		for (String s : arr) {
			System.out.println(s);
		}
	}

}
