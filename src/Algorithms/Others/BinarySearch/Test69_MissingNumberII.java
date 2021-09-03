package Algorithms.Others.BinarySearch;

public class Test69_MissingNumberII {
	// sorted array, use binary search
	// TC: O(log n)
	// SC: O(1)
	public int missing(int[] array) {
		// corner cases
		if (array.length == 0) return 1;

		int l = 0, r = array.length - 1;
		// at least 3 elements in search range
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			// in the left half
			if (mid == array[mid] - 1) {
				l = mid;
			}
			// in the right half
			else {
				r = mid;
			}
		}

		// missing number is in between l and r
		if (array[l] == array[r] - 2) return array[l] + 1;
		// missing number is before l
		if (l == 0 && array[0] == 2) return 1;
		// missing number is after r
		return array.length + 1;
	}
}
