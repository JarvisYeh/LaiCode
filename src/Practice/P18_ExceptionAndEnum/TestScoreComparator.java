package Practice.P18_ExceptionAndEnum;

import java.util.Comparator;

public class TestScoreComparator {
	public static void main(String[] args) {
		Student s1 = new Student("abc", 100, 2, 3);
		Student s2 = new Student("cde", 2, 3, 4);
		Comparator<Student> mathComp = ScoreComparator.valueOf("MathComparator");
		Comparator<Student> engComp = ScoreComparator.valueOf("EnglishComparator");
		Comparator<Student> cnComp = LambdaScoreComparator.valueOf("ChineseComparator");
		System.out.println(mathComp.compare(s1, s2));
		System.out.println(engComp.compare(s1, s2));
		System.out.println(cnComp.compare(s1, s2));
	}
}
