package tp1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MultiThread3 {

	static long count = 0;
	static ReentrantReadWriteLock myLock = new ReentrantReadWriteLock();

	public static Runnable lecture() {
		@SuppressWarnings("static-access")
		Runnable lecture = () -> {
			try {
				for (int i = 0; i < 1000; i++) {

					if (i % 200 == 0) {
						myLock.readLock().lock();
						Thread.currentThread().sleep(1);
						long loc = count;

						System.out.println(Thread.currentThread().getId() + " : " + loc);
						myLock.readLock().unlock();
					}

				}

			} catch (InterruptedException e) {
				// e.printStackTrace();
			}

		};
		return lecture;
	}

	@SuppressWarnings("static-access")
	public static Runnable ecriture() {
		Runnable ecriture = () -> {
			try {
				for (int i = 0; i < 1000; i++) {

					myLock.writeLock().lock();
					Thread.currentThread().sleep(1);
					count++;
					myLock.writeLock().unlock();
				}
			} catch (InterruptedException e) {

			}

		};
		return ecriture;

	}
	
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		// ecriture
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(ecriture());
			t.start();

		}
		// lecture
		for (int i = 0; i < 15; i++) {
			Thread t = new Thread(lecture());
			t.start();
			t.join();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("le temps d'execution est : " + (endTime - startTime));
	}

}
