package Algorithms.C15_Probability;

import java.util.Random;

public class Test111_Random7UsingRandom5 {
	Random rand = new Random();
	private int random5() {
		return rand.nextInt(5);
	}

	// use random5(): generate 0-4 with equal probabilities
	// to generate random7() method
	public int random7() {
		while (true) {
			// generate 0 - 24 with equal probabilities
			int newRand = random5()*5 + random5();
			// if it's in range [0, 20], e.g. 3*7 - 1 = 20
			// [0, 20] is generate with equal probabilities 1/25
			if (newRand < 21) {
				return newRand % 7;
			}
		}
	}
}
