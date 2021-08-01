package Practice.P22_23_Concurrency;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ProducerConsumer {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue q = new BlockingQueue(20);
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			// add 100 producer threads
			threads.add(new Thread(() -> q.put(1)));
		}

		for (int i = 0; i < 100; i++) {
			// add 100 consumer threads
			threads.add(new Thread(() -> q.take()));
		}

		// start those consumers and producer threads
		for (Thread t : threads) {
			t.start();
		}
	}
}

class BlockingQueue {
	private Queue<Integer> q;
	private final int limit;

	public BlockingQueue(int limit) {
		this.q = new ArrayDeque<>();
		this.limit = limit;
	}

	// producer put one element into queue
	public synchronized void put(Integer ele) {
		// 使用while()的原因是，被notify()并取得lock之后
		// 可能queue仍然是满的
		while (q.size() == limit) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// now q.size() < limit
		if (q.size() == 0) {
			// notify and awake all producers
			this.notifyAll();
		}
		q.offer(ele);
		System.out.println(q.size());
	}

	// consumer take one element from queue
	public synchronized Integer take() {
		while (q.size() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// now q.size() > 0
		if (q.size() == limit) {
			// notify and awake all consumer
			this.notifyAll();
		}
		System.out.println(q.size() - 1);
		return q.poll();
	}
}

