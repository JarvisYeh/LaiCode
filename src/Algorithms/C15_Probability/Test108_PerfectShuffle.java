package Algorithms.C15_Probability;

import java.util.Arrays;
import java.util.Random;

public class Test108_PerfectShuffle {
	Random rand = new Random();
	public void shuffle(int[] array) {
		for (int i = 0; i < array.length; i++) {
			// randIndex from [i, end)
			int randIndex = i + rand.nextInt(array.length - i);
			swap(array, i, randIndex);
		}
	}
	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
