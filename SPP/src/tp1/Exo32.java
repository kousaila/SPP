package tp1;

import java.util.ArrayList;
import java.util.List;

public class Exo32 {

	static long count = 0;
	static List<Integer> Id=new ArrayList<Integer>();
	private static Object mylock;

	public static Runnable lecture() throws InterruptedException {
		Runnable lecture = () -> {
			for (int i = 0; i < 1000; i++) {

				if (i % 200 == 0) {
//						mylock.lockRead();
//						Thread.currentThread().sleep(1);
					long loc = count;

					System.out.println(Thread.currentThread().getId() + " : " + loc);
//						mylock.unlockRead();
				}

			}

		};
		return lecture;
	}

	@SuppressWarnings("static-access")
	static Runnable ecriture = () -> {

		for (int i = 0; i < 1000; i++) {

			try {
				//mylock.lockWrite();
				Thread.currentThread().sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			count++;
			//mylock.unlockWrite();
		}
	};

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		// ecriture
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(ecriture);
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
