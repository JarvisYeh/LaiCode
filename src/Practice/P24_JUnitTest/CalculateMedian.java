package Practice.P24_JUnitTest;

import java.util.Arrays;

public class CalculateMedian {
	public double getMedian(int[] array) {
		if (array == null || array.length == 0) return 0;
		int n = array.length;

		Arrays.sort(array);
		if (n % 2 == 0) {
			// for boundary int addition, transfer to double then add
			return ((double)array[n/2 - 1] + array[n/2])/2.0;
		} else {
			return array[n/2];
		}
	}
}
