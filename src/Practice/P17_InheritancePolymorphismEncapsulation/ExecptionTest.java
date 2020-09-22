package Practice.P17_InheritancePolymorphismEncapsulation;

public class ExecptionTest {
	public static void excute(String a) throws MyException {
		System.out.println("execute...");
		if ("true".equals(a)) {
			throw new MyException("it can be true");
		}
	}

	public static void main(String[] args) throws MyException{
		excute("true");
	}
}
