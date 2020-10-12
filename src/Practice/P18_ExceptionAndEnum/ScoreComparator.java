package Practice.P18_ExceptionAndEnum;

import java.util.Comparator;

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
