package Algorithms.C24_DP4;

import java.util.Arrays;
import java.util.Comparator;


// TC: O(nlog n + n^2)
// SC: O(n)
public class Test217_LargestSetOfPointsWithPositiveSlope {
	public int largest(Point[] points) {
		Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return Integer.compare(p1.x, p2.x);
			}
		});

		int max = 0;
		int[] DP = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			DP[i] = 1;
			for (int j = i - 1; j >= 0; j--) {
				// even though x is sorted, still need to consider x
				// because when i.x = j.x, it is a straight line with no slope
				if (points[j].x < points[i].x && points[j].y < points[i].y) {
					// update DP[i] if necessary
					DP[i] = Math.max(DP[i], DP[j] + 1);
				}
			}
			// update max
			max = Math.max(DP[i], max);
		}
		return max == 1 ? 0 : max;
	}

	static class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Test217_LargestSetOfPointsWithPositiveSlope t = new Test217_LargestSetOfPointsWithPositiveSlope();
		System.out.println(t.largest(new Point[] {
				new Point(1, 5),
				new Point(1, 3),
				new Point(0, 10)
		}));
	}

}
