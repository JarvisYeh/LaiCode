package Practice.P18_ExceptionAndEnum;

import java.util.Collections;
import java.util.Comparator;

public class TestScoreComparator {

	// compare use enum defined comparator
	public static void compare(Student s1, Student s2, String comparator) {
		// retrieve specific instance
		Comparator<Student> mathComp = ScoreComparator.valueOf(comparator);
		System.out.println(mathComp.compare(s1, s2));
	}

	// compare with enum
	// and Collections.reverseOrder()
	public static void compareWithOrder(Student s1, Student s2, String comparator, boolean desc) {
		// retrieve specific instance
		Comparator<Student> mathComp = ScoreComparator.valueOf(comparator);
		if (desc) {
			mathComp = Collections.reverseOrder(mathComp);
		}
		System.out.println(mathComp.compare(s1, s2));
	}

	public static void main(String[] args) {
		Student s1 = new Student("abc", 100, 2, 3);
		Student s2 = new Student("cde", 2, 3, 4);
		// compare math
		compare(s1, s2, "MathComparator");
		// compare english
		compare(s1, s2, "EnglishComparator");
		// compare chinese with descending order
		// s1.score > s2.score, print -1
		// s1.score == s2.score, print 0
		// else, print 1
		compareWithOrder(s1, s2, "ChineseComparator", true);
	}
}
