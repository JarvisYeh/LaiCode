package Algorithms.C21_CrossTraining3;

import java.util.HashMap;
import java.util.HashSet;

class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Test216_MostPointsOnALine {
	// use y = kx + b
	// iterate i
	// for other points j
	// 	use only k as key
	public int mostI(Point[] points) {
		// corner cases
		if (points.length == 0) return 0;
		if (points.length == 1) return 1;

		int maxPoints = 0;
		for (Point p1 : points) {
			int duplicates = 0;
			// create points distribution map for point i
			HashMap<String, HashSet<Point>> lineMap = new HashMap<>();
			for (Point p2 : points) {
				if (p1 == p2) continue;
				int deltaY = p1.y - p2.y;
				int deltaX = p1.x - p2.x;
				// duplicates points
				if (deltaX == 0 && deltaY == 0) {
					duplicates++;
					continue;
				}
				// normalize magnitude
				int gcd = gcd(deltaY, deltaX);
				deltaX /= gcd;
				deltaY /= gcd;
				// normalize sign
				if (deltaX < 0) {
					deltaX = -deltaX;
					deltaY = -deltaY;
				} else if (deltaX == 0 && deltaY < 0) {
					deltaY = -deltaY;
				}
				String key = deltaX + "," + deltaY;
				HashSet<Point> pointsSet = lineMap.getOrDefault(key, new HashSet<Point>());
				pointsSet.add(p2);
				lineMap.put(key, pointsSet);
				// update max
				maxPoints = Math.max(maxPoints, pointsSet.size() + duplicates + 1);
			}
			// special corner case, all points have same values of x and y
			if (duplicates + 1 == points.length) {
				return points.length;
			}
		}
		return maxPoints;
	}

	// y = kx + b, k = (y2 - y1)/(x2 - x1)
	// y = (y2 - y1)/(x2 - x1)x + b => b = (y1x2 - y2x1)/(x2 - x1)
	// y = (y2 - y1)/(x2 - x1)x + (y1x2 - y2x1)/(x2 - x1)
	// at the same time:
	// ax + by + c = 0
	// we could get (y2 - y1)x + (x1 - x2)y + (y1x2 - y2x1)
	// where a = y2 - y1, b = x1 - x2, c = (y1x2 - y2x1)
	// assume gcd(a, b, c) = t
	// (a/t, b/t, c/t) can determine a line as the signature of the line
	public int mostII(Point[] points) {
		// corner cases
		if (points.length == 0) return 0;
		if (points.length == 1) return 1;
		// map[line]points_set
		HashMap<String, HashSet<Point>> lineMap = new HashMap<>();
		int maxPoints = 0;
		for (Point p1 : points) {
			int duplicates = 0;
			for (Point p2 : points) {
				// same point, update duplicates
				if (p1.x == p2.x && p1.y == p2.y) {
					duplicates++;
					continue;
				}
				// ax + by + c = 0
				int a = p2.y - p1.y;
				int b = p1.x - p2.x;
				int c = p1.y*p2.x - p2.y*p1.x;
				// normalize magnitude
				int gcd = gcd(c, gcd(a, b));
				a/=gcd;b/=gcd;c/=gcd;
				// normalize sign
				if (a < 0) {
					a = -a; b = -b; c = -c;
				} else if (a == 0 && b < 0) {
					b = -b; c = -c;
				}
				String line = a + "," + b + "," + c;
				// retrieve point set for corresponding line
				HashSet<Point> pointsSet = lineMap.getOrDefault(line, new HashSet<Point>());
				pointsSet.add(p1);
				pointsSet.add(p2);
				lineMap.put(line, pointsSet);
				// update max
				maxPoints = Math.max(maxPoints, pointsSet.size());
			}
			// special corner case, all points have same values of x and y
			if (duplicates == points.length) {
				return points.length;
			}
		}
		return maxPoints;
	}

	// Euclidean algorithm for gcd(a, b)
	private int gcd(int a, int b) {
		a = Math.abs(a);
		b = Math.abs(b);
		while (b != 0) {
			int tmp = b;
			b = a % b;
			a = tmp;
		}
		return a;
	}

	public static void main(String[] args) {
		Test216_MostPointsOnALine test = new Test216_MostPointsOnALine();
		Point[] points = new Point[] {
				new Point(1, 1),
				new Point(2, 3),
				new Point(1, 1),
				new Point(2, 3),
				new Point(1, 1),
				new Point(2, 2),
				new Point(3, 4),
				new Point(9, 10),
		};
		System.out.println(test.mostI(points));
	}
}