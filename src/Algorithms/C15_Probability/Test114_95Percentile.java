package Algorithms.C15_Probability;

import java.util.List;

public class Test114_95Percentile {
	public int percentile95(List<Integer> lengths) {
		// record count[len(url)]
		int[] urlsCount = new int[4097];
		for (int length : lengths) {
			urlsCount[length]++;
		}

		int countSum = 0;
		int L = 4096;
		while (countSum <= lengths.size() * 0.05) {
			countSum += urlsCount[L--];
		}
		// after the loop, countSum > total count of urls * 5%
		return L + 1;
	}
}
