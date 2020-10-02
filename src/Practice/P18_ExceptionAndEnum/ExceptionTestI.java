package Practice.P18_ExceptionAndEnum;

public class ExceptionTestI {
	int count = 0 ;
	void A() throws  Exception {
		try {
			count++; // 1
			try {
				count++; // 2
				try {
					count++; // 3
					throw new Exception();
				} catch (Exception e) {
					count++; // 4
					throw new Exception();
				}
//				count++; 因为前面有throw exception，不会进行到这行代码
			} catch (Exception e) {
				count++; // 5
			}
		} catch (Exception e) {
			count++; // 已经被上层catch处理，这里的catch没有catch到任何exception
		}
		count++; // 6 因为没有throw出的exception已经被handle，当前层没有throw exception，正常执行
	}

	public static void main (String[] args) throws Exception{
		ExceptionTestI test = new ExceptionTestI();
		test.A();
		System.out.println(test.count);
	}
}
