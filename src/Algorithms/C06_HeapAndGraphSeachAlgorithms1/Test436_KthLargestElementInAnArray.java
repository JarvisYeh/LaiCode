package Algorithms.C06_HeapAndGraphSeachAlgorithms1;

public class Test436_KthLargestElementInAnArray {
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || k <= 0) return Integer.MIN_VALUE;
		quickSelect(nums, 0, nums.length - 1, k);
		return nums[k - 1];
	}

	private void quickSelect(int[] nums, int l, int r, int k) {
		if (k <= 0) return;

		int pivotIndex = partition(nums, l, r);
		int count = pivotIndex - l + 1;
		if (count < k) {
			quickSelect(nums, pivotIndex + 1, r, k - count);
		} else if (count > k) {
			quickSelect(nums, l, pivotIndex - 1, k);
		}
	}

	private int partition(int[] nums, int left, int right) {
		// [l, r] are the range of unclassified elements
		int l = left, r = right - 1;
		int pivot = nums[right];
		while (l <= r) {
			if (nums[l] >= pivot) {
				l++;
			} else {
				swap(nums, l, r--);
			}
		}
		// swap pivot back to its position
		swap(nums, l, right);
		return l;
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		Test436_KthLargestElementInAnArray t = new Test436_KthLargestElementInAnArray();
		t.findKthLargest(new int[]{1,2,2,3,1,3,4,4,3,2}, 6);
	}
}
