package Practice.P22_23_Concurrency;

public class Test {
	public static void main(String[] args) throws Exception {
		System.out.println("Hello 1");
		Thread t = new Thread(){
			@Override
			public void run() {
				System.out.println("Hello 2");
			}
		};

		t.start();
		System.out.println("Hello 3");
		t.join();
		System.out.println("Hello 4");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello Word");
			}
		});
	}
}
