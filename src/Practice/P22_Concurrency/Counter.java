package Practice.P22_Concurrency;

public class Counter {
	private static int value = 1;
	public Counter(int value) {
		this.value = value;
	}

	// synchronized(object)
	public void increase() {
		// lock the instance
		// when instance.increase() is called
		synchronized (Counter.class){
			
		}
		synchronized(this) {
			value++;
		}
	}

	// synchronized in method signature
	// all code inside method is critical section
	public synchronized void decrease() {
		value--;
	}

	public synchronized int getCount() {
		return value;
	}
}
