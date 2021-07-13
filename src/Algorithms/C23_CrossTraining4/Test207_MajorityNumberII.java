package Algorithms.C23_CrossTraining4;

import java.util.ArrayList;
import java.util.List;

public class Test207_MajorityNumberII {
	public List<Integer> majority(int[] array) {
		List<Integer> res = new ArrayList<>();
		// 2 candidates, 2 counters
		int cand1 = 0, cand2 = 0, count1 = 0, count2 = 0;
		// for each number
		for (int num : array) {
			if (num == cand1) {
				count1++;
			} else if (num == cand2) {
				count2++;
			} else if (count1 == 0) {
				cand1 = num;
				count1++;
			} else if (count2 == 0) {
				cand2 = num;
				count2++;
			} else {
				// if num not equal to any candidate, and NO count = 0
				// both counter decrease by 1
				count1--;
				count2--;
			}
		}

		// post processing
		// iterate through array again, record the frequency of each candidate
		count1 = 0;
		count2 = 0;
		for (int num : array) {
			if (num == cand1) count1++;
			if (num == cand2) count2++;
		}
		// check if a candidate has count > n/3
		// also it could be possible that two candidates are same, avoid duplicate candidate
		if (count1 > array.length / 3) res.add(cand1);
		if (cand1 != cand2 && count2 > array.length / 2) res.add(cand2);
		return res;
	}

	public static void main(String[] args) {
		Test207_MajorityNumberII t = new Test207_MajorityNumberII();
		System.out.println(t.majority(new int[]{2, 1, 1, 3, 1, 4, 5, 6}));
	}
}
