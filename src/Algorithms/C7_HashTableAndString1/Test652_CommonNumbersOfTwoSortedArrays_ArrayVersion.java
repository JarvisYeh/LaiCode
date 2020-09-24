package Algorithms.C7_HashTableAndString1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Test652_CommonNumbersOfTwoSortedArrays_ArrayVersion {
	/**
	 * Find all numbers that appear in both of two sorted arrays
	 * the two arrays are all sorted in ascending order
	 **/

	/**
	 * Solution 1: Hashset
	 * 缺点：Space complexity大，没有用到sorted这一条件
	 * Time Complexity: O(m + n)
	 * Space Complexity: O(min(m, n))
	 **/
	public List<Integer> commonI(int[] A, int[] B) {
		if (A == null || B == null) {
			return null;
		}
		List<Integer> result = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();

		// add all elements from shorter array to hashset
		// to save space complexity
		int[] shorter = A.length < B.length ? A : B;
		int[] longer = A.length >= B.length ? A : B;

		for (int i : shorter) {
			set.add(i);
		}

		for (int i: longer) {
			if (set.contains(i)) {
				result.add(i);
			}
		}
		return result;
	}

	/**
	 * Solution 2: 谁小移谁，使用2个指针
	 * 缺点：如果一个array很大，时间复杂度很高
	 * Time Complexity: O(m + n)
	 * Space Complexity: O(1)
	 **/
	public List<Integer> commonII(int[] A, int[] B) {
		if (A == null || B == null) {
			return null;
		}
		List<Integer> result = new ArrayList<>();
		int i=0, j=0;
		while (i < A.length && j < B.length) {
			if (A[i] == B[j]) {
				result.add(A[i]);
				i++;
				j++;
			} else if (A[i] < B[j]) {
				i++;
			} else {
				j++;
			}
		}
		return result;
	}

	/**
	 * Solution 3: Binary Search
	 * 在长度更长的array中找元素少的array中的元素
	 * Time Complexity: O(max(m, n)*log(min(m, n)))
	 * Space Complexity: O(1)
	 **/
	public List<Integer> commonIII(int[] A, int[] B) {
		if (A == null || B == null) {
			return null;
		}

		List<Integer> result = new ArrayList<>();

		// search in longer array
		int[] elements = A.length < B.length ? A : B;
		int[] range = A.length >= B.length ? A : B;

		for (int i : elements) {
			if (binarySearch(i, range)) {
				result.add(i);
			}
		}
		return result;
	}

	private boolean binarySearch(int target, int[] arr) {
		int l=0, r=arr.length - 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (arr[mid] < target) {
				l = mid + 1;
			} else if (arr[mid] > arr[target]) {
				r = mid - 1;
			} else {
				return true;
			}
		}
		return false;
	}

}