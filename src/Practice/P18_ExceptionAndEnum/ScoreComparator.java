package Practice.P18_ExceptionAndEnum;

import java.util.Comparator;
import java.util.function.BiFunction;

public enum ScoreComparator implements Comparator<Student> {
	MathComparator {
		@Override
		public int compare(Student o1, Student o2) {
			return Integer.valueOf(o1.math).compareTo(Integer.valueOf(o2.math));
		}
	},
	EnglishComparator {
		@Override
		public int compare(Student o1, Student o2) {
			return Integer.valueOf(o1.english).compareTo(Integer.valueOf(o2.english));
		}
	},
	ChineseComparator {
		@Override
		public int compare(Student o1, Student o2) {
			return Integer.valueOf(o1.chinese).compareTo(Integer.valueOf(o2.chinese));
		}
	};
}

// with lambda expression together with bi-function
enum LambdaScoreComparator implements Comparator<Student> {
	// lambda 1
	MathComparator ((Student s1, Student s2) -> Integer.compare(s1.math, s2.math)),
	// lambda 2
	EnglishComparator(
			(Student s1, Student s2) -> {
				return Integer.compare(s1.english, s2.english);
			}),
	ChineseComparator(
			(Student s1, Student s2) -> {
				return Integer.compare(s1.chinese, s2.chinese);
			});

	// function with 2 parameter and 1 return type is BiFunction class
	private final BiFunction<Student, Student, Integer> func;

	LambdaScoreComparator(BiFunction<Student, Student, Integer> func) {
		this.func = func;
	}
	@Override
	public int compare(Student s1, Student s2) {
		return func.apply(s1, s2);
	}
}

class Student{
	String name;
	int math;
	int english;
	int chinese;

	public Student(String name, int math, int english, int chinese) {
		this.name = name;
		this.math = math;
		this.english = english;
		this.chinese = chinese;
	}
}
