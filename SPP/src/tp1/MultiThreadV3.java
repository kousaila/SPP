package tp1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiThreadV3 extends Thread {
	static long count = 0;
	public static ReadWriteLock rwlock = new ReentrantReadWriteLock();
	public static Lock r=rwlock.readLock();
	public static Lock w=rwlock.writeLock();
	public void run() {
		for (int i = 0; i < 1000; i++) {
			w.lock();
		
				try {
					this.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			Multithread.count++;
			
			w.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			Thread t = new MultiThreadV3();
			t.start();

		}
		for (int i = 0; i < 15; i++) {
			Thread t = new ThreadPrinter3();
			t.start();
			t.join();
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

}