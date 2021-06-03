package Algorithms.C9_String2;

public class Test1470_ShuffleTheArray {
	// not in place
	// SC: O(n)
	public int[] shuffleI(int[] nums, int n) {
		int[] res = new int[nums.length];
		int j = 0;
		for (int i = 0; i < n; i++) {
			res[j] = nums[i];
			res[j + 1] = nums[n + i];
			j += 2;
		}
		return res;
	}

	// in place
	// SC: O(log n)
	public int[] shuffleII(int[] array, int n) {
		shuffle(array, 0, array.length - 1);
		return array;
	}

	private void shuffle(int[] arr, int l, int r) {
		// divide current range into 4 chunks
		// if the range can not be divided into 4 chunks anymore, return
		if (r - l <= 1) { // e.g. len(l ~ r) <= 3
			return;
		}

		// l lm m rm m => [l, lm) [lm, m) [m, rm) [rm, r]
		// 0 1 | 2 3 | 4 5 | 6 7, size = 8, mid = 4
		// A B | C D | 1 2 | 3 4
		// 0 1 | 2 3 4 | 5 6 | 7 8 9, size = 10, mid = 5
		// A B | C D E | 1 2 | 3 4 5
		int size = r - l + 1;
		int mid = l + size/2;
		int lm = l + size/2/2;
		int rm = mid + size/2/2;
		// reverse [lm, rm)
		reverse(arr, lm, rm - 1);
		// reverse [lm, m)
		reverse(arr, lm, mid - 1);
		// reverse [m, rm)
		reverse(arr, mid, rm - 1);

		// after reverse:
		// A B C D E 1 2 3 4 5 => A B 1 2 | C D E 3 4 5
		// A B C D 1 2 3 4 => A B 1 2 | C D 3 4
		shuffle(arr, l, l + size/4*2 - 1);
		shuffle(arr, l + size/4*2, r);
	}

	private void reverse(int[] nums, int i, int j) {
		while (i < j) {
			int tmp = nums[i];
			nums[i++] = nums[j];
			nums[j--] = tmp;
		}
	}

	public static void main(String[] args) {
		Test1470_ShuffleTheArray t = new Test1470_ShuffleTheArray();
		for (int i : t.shuffleII(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5)) {
			System.out.print(i + " ");
		}
	}

}

