package Algorithms.C23_CrossTraining4;

public class Test206_MajorityNumberI {
	public int majority(int[] array) {
		int candidate = 0, counter = 0;
		for (int num : array) {
			if (counter == 0) {
				candidate = num;
				counter = 1;
			} else if (num == candidate) {
				counter++;
			} else {
				counter--;
			}
		}
		return candidate;
	}
}
