package Practice.P18_ExceptionAndEnum;

public class ExceptionTest {
	static void fun() {
		try {
			throw new NullPointerException("demo");
		} catch (NullPointerException e) {
			System.out.println("Catch inside fun().");
			throw e; // rethrow this exception
		}
	}

	public static void main(String[] args) {
		try {
			fun();
		} catch (NullPointerException e) {
			System.out.println("Caught in main.");
		}
	}
}
