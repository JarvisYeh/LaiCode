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
				stack.offerFirst(i);
				continue;
			}
			// continue popping the stack until find the first index t where heights[t] < heights[i]
			while (!stack.isEmpty() && array[stack.peekFirst()] >= array[i]) {
				stack.pollFirst();
			}
			if (stack.isEmpty()) {
				leftBoundary[i] = -1;
			} else {
				leftBoundary[i] = stack.peekFirst();
			}
			stack.offerFirst(i);
		}

		stack.clear();
		// construct right boundary array
		for (int i = array.length - 1; i >= 0; i--) {
			if (stack.isEmpty()) {
				rightBoundary[i] = array.length;
				stack.offerFirst(i);
				continue;
			}
			// continue popping the stack until find the first index t where heights[t] < heights[i]
			while (!stack.isEmpty() && array[stack.peekFirst()] >= array[i]) {
				stack.pollFirst();
			}
			if (stack.isEmpty()) {
				rightBoundary[i] = array.length;
			} else {
				rightBoundary[i] = stack.peekFirst();
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

	/**
	 * solution 3
	 * 进一步优化时间
	 * 存储两个array left and right
	 * left/right[i] = look left/right the first idx that heights[idx] < heights[i]
	 * 这样在算left/right[i]时不用linear回头看stack，可以连续jump
	 * TC: O(n)
	 * SC: O(n)
	 */
	public int largestRectangleArea(int[] heights) {
		int n = heights.length;
		int[] leftLessIdxs = new int[n];    // l[i]: the first idx that l[idx] < l[i] when looking left
		leftLessIdxs[0] = -1;
		int[] rightLessIdxs = new int[n];   // r[i]: the first idx that r[idx] < r[i] when looking right
		rightLessIdxs[n - 1] = n;

		// fill left array
		for (int i = 1; i < n; i++) {
			int prev = i - 1;
			while (prev >= 0 && heights[prev] >= heights[i]) {
				// now heights[prev] >= heights[i], first index that is less than heights[prev] is left[prev]
				// so directly update prev to that index: left[prev]
				prev = leftLessIdxs[prev];
			}
			// now prev = -1 || heights[prev] < heights[i]
			leftLessIdxs[i] = prev;
		}

		// fill right array
		for (int i = n - 2; i >= 0; i--) {
			int prev = i + 1;
			while (prev < n && heights[prev] >= heights[i]) {
				// update prev to first idx that is less than heights[prev]
				prev = rightLessIdxs[prev];
			}
			// now prev = n || heights[prev] < heights[i]
			rightLessIdxs[i] = prev;
		}

		// find max area
		int max = 0;
		for (int i = 0; i < n; i++) {
			int area = (rightLessIdxs[i] - leftLessIdxs[i] - 1)*heights[i];
			max = Math.max(area, max);
		}
		return max;
	}

	public static void main(String[] args) {
		Test198_LargestRectangleInHistogram test = new Test198_LargestRectangleInHistogram();
		test.largestII(new int[]{1,2,1});
	}
}
