package Algorithms.C19_CrossTraining2;

public class Test276_GetCountArray {
	public int[] countArray(int[] nums) {
		int n = nums.length;
		// indexArr[i]: stores i-th element's index in nums array
		// act as map
		int[] indexArr = new int[n];
		for (int i = 0; i < n; i++) indexArr[i] = i;
		// countArr initialize as all 0s
		int[] countArr = new int[n];

		// helper array for merge sort
		int[] helper = new int[n];
		mergeSort(nums, indexArr, countArr, helper, 0, n - 1);

		// after mergeSort, countArr stores the final result
		return countArr;
	}

	// want to store indexArr based on nums[indexArr[i]]
	private void mergeSort(int[] nums, int[] indexArr, int[] countArr, int[] helper, int l, int r) {
		if (l >= r) return;

		int mid = l + ((r - l) >> 1);
		mergeSort(nums, indexArr, countArr, helper, l, mid);
		mergeSort(nums, indexArr, countArr, helper, mid + 1, r);
		merge(nums, indexArr, countArr, helper, l, mid, r);
	}

	private void merge(int[] nums, int[] indexArr, int[] countArr, int[] helper, int l, int mid, int r) {
		for (int i = l; i <= r; i++) {
			helper[i] = indexArr[i];
		}
		int i = l, j = mid + 1, res = l;
		while (i <= mid && j <= r) {
			// left <= right, 都选left中的数字
			if (nums[helper[i]] <= nums[helper[j]]) {
				indexArr[res] = helper[i];
				countArr[indexArr[res]] += (j - mid - 1);
				res++;
				i++;
			} else {
				indexArr[res++] = helper[j++];
			}
		}

		while (i <= mid) {
			indexArr[res] = helper[i];
			countArr[indexArr[res]] += (j - mid - 1);
			res++;
			i++;
		}
	}

	public static void main(String[] args) {
		Test276_GetCountArray t = new Test276_GetCountArray();
		t.countArray(new int[]{1,2,3,4,5});
	}

}
