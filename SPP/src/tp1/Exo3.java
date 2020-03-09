package tp1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Exo3 {

	static long count = 0;
	static List<Long> Id = new ArrayList<Long>();
	private static final Semaphore empty = new Semaphore(10);
	private static final Semaphore full = new Semaphore(0);
	private static final Semaphore qSem = new Semaphore(1);

	static Runnable insert = () -> {
		int a = (int) (Math.random() * 10);

		try {
			empty.acquire();
			qSem.acquire();
			try {
				Id.add(a, Thread.currentThread().getId());
				System.out.println(
						Thread.currentThread().getId() + " : " + a + ", nom: " + Thread.currentThread().getName());

			} catch (IndexOutOfBoundsException e1) {

			} finally {
				qSem.release();
				full.release();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();

		}

	};

	static Runnable delete = () -> {
		int a = (int) (Math.random() * 10);

		try {
			full.acquire();
			qSem.acquire();
			try {
				Id.get(a);
				if(Id.get(a)!=null) {
					System.out.println(
							Thread.currentThread().getId() + " : " + a + ", nom: " + Thread.currentThread().getName());
					Id.remove(a);
				}

			} catch (IndexOutOfBoundsException e2) {

			} finally {
				qSem.release();
				empty.release();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	};

	public static void main(String[] args) throws InterruptedException {

		long startTime = System.currentTimeMillis();
		// ecriture

		for (int i = 0; i < 10; i++) {

			Thread t = new Thread(insert, "Insert");

			t.start();
		}
		for (int i = 0; i < 10000; i++) {

			// lecture
			Thread t1 = new Thread(delete, "Delete");
			t1.start();
		}

		long endTime = System.currentTimeMillis();
		System.out.println("le temps d'execution est : " + (endTime - startTime));
		System.out.println("****************************" + (Id.size()));

	}

}
