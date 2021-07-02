package Algorithms.C21_CrossTraining3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Test650_CommonNumbersOfTwoArraysI {
	// TC: O(m + n)
	// SC: O(min(m, n))
	public List<Integer> common(int[] a, int[] b) {
		List<Integer> res = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();

		// alawyas set b as the smaller size array
		if (a.length < b.length) {
			int[] tmp = a;
			a = b;
			b = tmp;
		}
		// always put smaller size array into hashset
		for (int i : b) {
			set.add(i);
		}
		for (int i : a) {
			if (set.contains(i)){
				res.add(i);
			}
		}
		res.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});
		return res;
	}
}
