package Algorithms.C21_CrossTraining3;

import java.util.ArrayList;
import java.util.List;

public class Test171_CommonElementsInThreeSortedArray {
	public List<Integer> common(int[] a, int[] b, int[] c) {
		int i = 0, j = 0, k = 0;
		List<Integer> res = new ArrayList<>();
		while (i < a.length && j < b.length && k < c.length) {
			if (a[i] == b[j] && b[j] == c[k]) {
				res.add(a[i]);
				i++;
				j++;
				k++;
			} else if (a[i] <= b[j] && a[i] <= c[k]) {
				i++;
			} else if (b[j] <= a[i] && b[j] <= c[k]){
				j++;
			} else {
				k++;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		Test171_CommonElementsInThreeSortedArray test = new Test171_CommonElementsInThreeSortedArray();
		test.common(new int[]{1,2,3,3}, new int[]{2,3,4,4,5}, new int[]{1,1,3,3});
	}
}
