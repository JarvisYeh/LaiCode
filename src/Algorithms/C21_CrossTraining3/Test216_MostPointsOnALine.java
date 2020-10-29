package Algorithms.C21_CrossTraining3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Test216_MostPointsOnALine {
	public int most(Point[] points) {
		HashMap<Pair, HashSet<Point>> map = new HashMap<>();
		int maxPoints = 0;
		for (Point p1 : points) {
			for (Point p2 : points) {
				if (p1 != p2) {
					Pair pair = getSlopeAndIntersect(p1, p2);
					if (!map.containsKey(pair)) {
						map.put(pair, new HashSet<>());
					}
					map.get(pair).add(p1);
					map.get(pair).add(p2);
					maxPoints = Math.max(maxPoints, map.get(pair).size());
				}
			}
		}
		return maxPoints;
	}

	private Pair getSlopeAndIntersect(Point p1, Point p2) {
		if (p1.x == p2.x) {
			return new Pair(Double.MAX_VALUE, p1.x);
		}

		double slope = (double)(p1.y - p2.y)/(double)(p1.x - p2.x);
		double intersect = p1.y - slope * p1.x;
		return new Pair(slope, intersect);
	}

	private static class Pair {
		double slope;
		double intersect;

		public Pair(double slope, double intersect) {
			this.slope = slope;
			this.intersect = intersect;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Pair pair = (Pair) o;
			return Double.compare(pair.slope, slope) == 0 &&
					Double.compare(pair.intersect, intersect) == 0;
		}

		@Override
		public int hashCode() {
			return Objects.hash(slope, intersect);
		}
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
		System.out.println(test.most(points));
	}
}

class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
