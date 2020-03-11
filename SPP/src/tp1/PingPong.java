package tp1;

import java.util.concurrent.locks.ReentrantLock;

public class PingPong {
	public final static ReentrantLock l1 = new ReentrantLock();
	public final static ReentrantLock l2 = new ReentrantLock();

	static class Thread1 extends Thread {
		public void run() {
			for (int i = 1; i <= 1000; i++) {
				l1.lock();
				System.out.println("ping");
				l2.unlock();
			}
		}
	}

	static class Thread2 extends Thread {
		public void run() {
			for (int i = 1; i <= 1000; i++) {
				l2.lock();
				System.out.println("pong");
				l1.unlock();
			}
		}
	}

	public static void main(String[] args) {
		//l2.lock();
		new Thread1().start();
		new Thread2().start();
	} // EndClass PingPong

}
