package Design.D3_DesignPattern;

public class SingletonExample {
	private static final SingletonExample INSTANCE = new SingletonExample();

	private SingletonExample(){} // private constructor

	public static SingletonExample getInstance() { // static method to access this INSTANCE
		return INSTANCE;
	}
}
