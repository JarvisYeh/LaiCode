package Algorithms.C15_Probability;

import java.util.*;

public class Test110_GeneralizedReservoirSampling {
	private final int k;
	private int count;
	private List<Integer> sample;
	private Random rand;

	public Test110_GeneralizedReservoirSampling(int k) {
		this.k = k;
		count = 0;
		sample = new ArrayList<Integer>();
		rand = new Random();
	}

	public void read(int value) {
		count++;
		if (count <= k) {
			sample.add(value);
		} else {
			int r = rand.nextInt(count);
			// new value has k/n possibility to be the sample
			// old sample has k/(n - 1)*[(n - k)/n + k/n*(k - 1)/k] = k/n to be the sample
			// in n-th (n > k) rounds, every sample has k/n probability to be the sample
			if (r < k) {
				sample.set(r, value);
			}
		}
	}

	public List<Integer> sample() {
		return sample;
	}

	public static void main(String[] args) {
		int[] countFreq = new int[50];
		int[] arr = new int[50];
		for (int j = 0; j < arr.length; j++) {
			arr[j] = j;
		}

		// repeat experiments for 10,000 times
		// record the last round sample for same input stream every time
		// since in last round, all numbers in input stream has P = 1/n to be samples
		// the count will roughly be the same for all input stream numbers
		for (int i = 0; i < 10000; i++) {
			Test110_GeneralizedReservoirSampling t = new Test110_GeneralizedReservoirSampling(5);
			Iterator<Integer> it = Arrays.stream(arr).iterator();
			while (it.hasNext()) {
				t.read(it.next());
			}
			for (int num : t.sample()) {
				countFreq[num]++;
			}
		}

		// print the sample distribution
		System.out.println(Arrays.toString(countFreq));
	}
}
