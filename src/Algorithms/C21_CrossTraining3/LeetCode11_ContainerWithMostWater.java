package Algorithms.C21_CrossTraining3;

public class LeetCode11_ContainerWithMostWater {
	// TC : O(n)
	// SC : O(1)
	public int maxArea(int[] height) {
		// start with max width
		int i = 0, j = height.length - 1;
		int max = 0;
		while (i < j) {
			int h = Math.min(height[i], height[j]);
			max = Math.max(max, h*(j - i));
			// since h = min(h[i], h[j]) and width will only be decreasing
			// move smaller height one from i and j is the only possible way ito increase height
			if (height[i] < height[j]) {
				i++;
			} else {
				j--;
			}
		}
		return max;
	}
}
