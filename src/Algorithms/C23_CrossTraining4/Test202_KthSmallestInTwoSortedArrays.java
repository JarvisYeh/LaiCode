package Algorithms.C23_CrossTraining4;

public class Test202_KthSmallestInTwoSortedArrays {
	/**
	 * Method 1
	 * Use two pointer
	 * Time Complexity: O(k)
	 * Space Complexity: O(1)
	 */
	public int kthI(int[] a, int[] b, int k) {
		// both i and j point to the position that haven't being checked yet
		int i = 0;
		int j = 0;
		int res = Integer.MIN_VALUE;

		// in case of array out of bound
		// move the pointer of the smaller one ahead
		while (k > 0 && i < a.length && j < b.length){
			if (a[i] < b[j]) {
				res = a[i++];
				k--;
			} else {
				res = b[j++];
				k--;
			}
		}

		// if one array is out of bound, but k-th smallest is still not found
		// the k-th smallest is in the other array
		if (k > 0) {
			// i < a.length means array a haven't being gone through yet
			// which means array b is begin gone through
			if (i < a.length) {
				res = a[i + k - 1];
			} else {
				res = b[j + k - 1];
			}
		}

		return res;
	}


	/**
	 * Method 2
	 * Use binary search
	 * @param a, b are two input array
	 * @param aLeft, the first element index that still need to be check
	 * @param bLeft, the first element index that still need to be check
	 * @param k, the number of elements left to be checked
	 */
	private int kthII(int[] a, int[] b, int aLeft, int bLeft, int k) {
		// base cases
		// 1. all elements in a are checked to be less than the k-th elements
		if (aLeft >= a.length) {
			return b[bLeft + k - 1];
		}

		// 2. all elements in b are checked to be less than the k-th elements
		if (bLeft >= b.length) {
			return a[aLeft + k - 1];
		}

		// 3. k = 1, which means the next element to check is the k-th smallest element
		if (k == 1) {
			return a[aLeft] > b[bLeft] ? b[bLeft] : a[aLeft];
		}

		// obtain the the check point index
		// using offset size curr_k/2
		int aMid = aLeft + k/2 - 1;
		int bMid = bLeft + k/2 - 1;

		// get the values of check point in both array
		// if the index is out of bound, simply put +infinite
		int aMidValue = aMid < a.length ? a[aMid] : Integer.MAX_VALUE;
		int bMidValue = bMid < b.length ? b[bMid] : Integer.MAX_VALUE;

		// if the check point of a if less than b
		// a[left, left + offset] are all less than kth element, removed then from the uncheck range
		if (aMidValue < bMidValue) {
			return kthII(a, b, aMid + 1, bLeft, k - k/2);
		}
		// if the check point of b if less than a
		// b[left, left + offset] are all less than kth element, removed then from the uncheck range
		else {
			return kthII(a, b, aLeft, bMid + 1, k - k/2);
		}
	}

	/**
	 * Method 3:
	 * binary search target in A
	 * if doesn't exist, binary search target in B
	 * TC: O(2*log k), right boundary can set to k
	 * SC: O(1)
	 */
	public int kthII(int[] a, int[] b, int k) {
		int res = search(a, b, k);
		if (res == -1) {
			res = b[search(b, a, k)];
		} else {
			res = a[res];
		}
		return res;
	}

	private int search(int[] a, int[] b, int k) {
		int left = 0, right = Math.min(a.length - 1, k);
		// do binary search in array a
		while (left <= right) {
			int m = left + (right - left)/2;
			// if a[m] is the k-th smallest elements in two arrays, there must be k - 1 elements before it
			// there are already 'm' elements in a array before a
			// so there must be 'k - 1 - m' elements in b array < a[m]
			// check b[k - m - 1] (in b, [0, k-m-2] has 'k - m - 1' element)
			// so that is to say if b[k-m-2] < a[m] && b[k-m-1] > a[m]
			// then there will be exactly 'k - m - 1' elements in b < a[m]
			// then a[m] will be k-th smallest element
			if (k - m - 1 < 0 || (k - m - 1 < b.length && a[m] > b[k - m - 1])) {
				// we need <= 0 elements
				// or will be more than 'k - m - 1' elements in b > a[m]
				// that is to say target is at the left of 'm'
				// search left
				right = m - 1;
			} else if (k - m - 2 >= b.length || (k - m - 2 >= 0 && a[m] < b[k - m - 2])) {
				// there are less than 'k - m - 1' elements in b
				// or there are less than 'k - m - 1' elements in b < a[m]
				// search right
				left = m + 1;
			} else {
				//b[k - m - 2] < a[m] && a[m] < b[k - m - 1]
				return m;
			}
		}
		// that could be the k-th element in b array instead of a
		// which will exhausted out search range and reach final and return -1
		return -1;
	}

	public static void main(String[] args) {
		Test202_KthSmallestInTwoSortedArrays t = new Test202_KthSmallestInTwoSortedArrays();
		t.kthII(new int[]{1}, new int[]{2,3,4,5,6}, 1);
	}
}
