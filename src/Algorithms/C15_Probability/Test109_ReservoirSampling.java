package Algorithms.C15_Probability;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

// read(int num) read number from a stream, one number at a time
// sample() returns a number from all the numbers read yet
// with equal probability
public class Test109_ReservoirSampling {
	int count;
	Integer sample;
	Random rand = new Random();

	public Test109_ReservoirSampling() {
		this.count = 0;
		this.sample = null;
	}

	public void read(int value) {
		count++;
		// nexInt(count) returns [0, count) with equal probabilities
		// therefore P[nextInt(count) = 0] = 1 / count = 1 / real time n
		// 			 P[sample = input val] = 1 / n
		//
		// thus, P[kept some previous round sample] = 1 - 1/n = (n - 1)/n
		// in last round, P[sample = x] = 1/(n - 1)
		// in this round, P[sample = x] = 1/(n - 1) * (n-1)/n = 1/n = P[sample = input val]
		// therefore, all numbers in i-th round, has P = 1/i to be chosen as sample
		if (rand.nextInt(count) == 0) {
			sample = value;
		}
	}

	public Integer sample() {
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
			Test109_ReservoirSampling t = new Test109_ReservoirSampling();
			Iterator<Integer> it = Arrays.stream(arr).iterator();
			while (it.hasNext()) {
				t.read(it.next());
			}
			countFreq[t.sample()]++;
		}

		// print the sample distribution
		System.out.println(Arrays.toString(countFreq));
	}
}
