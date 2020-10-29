package Algorithms.C21_CrossTraining3;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test198_LargestRectangleInHistogram {
	/**
	 * 找到直方图中面积最大的矩形
	 * Solution 1:
	 * 中心开花
	 * Time Complexity: O(n^2)
	 * Space Complexity: O(1)
	 **/
	public int largestI(int[] array) {
		int maxArea = 0;
		for (int i = 0; i < array.length; i++) {
			int left = i;
			int right = i;
			while (left >= 0 && array[left] >= array[i]) {
				left--;
			}
			while (right < array.length && array[right] >= array[i]) {
				right++;
			}
			maxArea = Math.max(maxArea, (right - left - 1)*array[i]);
		}
		return maxArea;
	}

	/**
	 * 找到直方图中面积最大的矩形
	 * Solution 2:
	 * 使用stack
	 * Time Complexity: O(n)
	 * Space Complexity: O(n)
	 **/
	public int largestII(int[] array) {
		int[] leftBoundary = new int[array.length];
		int[] rightBoundary = new int[array.length];
		Deque<Integer> stack = new ArrayDeque<>();

		// construct left boundary array
		for (int i = 0; i < array.length; i++) {
			if (stack.isEmpty()) {
				leftBoundary[i] = -1;
			} else {
				while (!stack.isEmpty() && array[stack.peekFirst()] >= array[i]) {
					stack.pollFirst();
				}
				if (stack.isEmpty()) {
					leftBoundary[i] = -1;
				} else {
					leftBoundary[i] = stack.peekFirst();
				}
			}
			stack.offerFirst(i);
		}

		stack = new ArrayDeque<>();
		// construct right boundary array
		for (int i = array.length - 1; i >= 0; i--) {
			if (stack.isEmpty()) {
				rightBoundary[i] = array.length;
			} else {
				while (!stack.isEmpty() && array[stack.peekFirst()] >= array[i]) {
					stack.pollFirst();
				}
				if (stack.isEmpty()) {
					rightBoundary[i] = array.length;
				} else {
					rightBoundary[i] = stack.peekFirst();
				}
			}
			stack.offerFirst(i);
		}

		// iterate through each elements to get maxArea
		int maxArea = 0;
		for (int i = 0; i < array.length; i++) {
			maxArea = Math.max(maxArea, (rightBoundary[i] - leftBoundary[i] - 1)*array[i]);
		}

		return maxArea;
	}

	public static void main(String[] args) {
		Test198_LargestRectangleInHistogram test = new Test198_LargestRectangleInHistogram();
		test.largestII(new int[]{1,2,1});
	}
}
