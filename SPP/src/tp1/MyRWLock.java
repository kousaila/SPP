/**
 * 
 */
package tp1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author sievi
 *
 */
public class MyRWLock {

	static long count = 0,nbreaders=0;
	static ReentrantLock count_l = new ReentrantLock();
	static ReentrantLock fairAcces = new ReentrantLock();
	private static final Semaphore l = new Semaphore(1, true);

	public static void lockRead() throws InterruptedException {
		fairAcces.lock();
		count_l.lock();
		if (nbreaders==0) {
			l.acquire();
		}
		nbreaders++;
		count_l.unlock();
		fairAcces.unlock();
	}

	public static void lockWrite() throws InterruptedException {
	fairAcces.lock();
	l.acquire();
	}

	public static void unlockRead() {
		count_l.lock();
		nbreaders--;
		if (nbreaders==0) {
			l.release();
		}
		count_l.unlock();
	}

	public static void unlockWrite() {
		l.release();
		fairAcces.unlock();
	}

	
	

}
