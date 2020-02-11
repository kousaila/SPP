/**
 * 
 */
package tpSPP;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sievi
 *
 */
public class MyRWLock {

	static long count = 0;
	static int flag = 0;
	static int flagWrite = 0;
	// static ReentrantReadWriteLock myLock = new ReentrantReadWriteLock();

	public static void lockRead() {
		flag = 1;
	}

	public static void lockWrite() {
		flagWrite = 1;
	}

	public static void unlockRead() {
		flag = 0;
	}

	public static void unlockWrite() {
		flagWrite = 0;
	}

	public static Runnable lecture() {
		if (flagWrite == 0) {
			Runnable lecture = () -> {
				for (int i = 0; i < 1000; i++) {

					if (i % 200 == 0) {
						// myLock.readLock().lock();
						// Thread.currentThread().sleep(1);
						lockRead();
						long loc = count;
						System.out.println(Thread.currentThread().getId() + " : " + loc);
						// myLock.readLock().unlock();
						unlockRead();
					}

				}
			};

			return lecture;
		}
		return null;

	}

	public static Runnable ecriture() {
		Runnable ecriture = () -> {
			for (int i = 0; i < 1000; i++) {

				// myLock.writeLock().lock();
				// Thread.currentThread().sleep(1);
				if (flag == 0) {
					lockWrite();
					count++;
					unlockWrite();
				} else {
					
				}
				// myLock.writeLock().unlock();
			}

		};
		return ecriture;

	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
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
