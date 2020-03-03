package tp1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Exo3 {

	static long count = 0;
	static List<Long> Id = new ArrayList<Long>();

	static Runnable insert = () -> {
		int a=(int) (Math.random()*10000);
		for (int i = 0; i < 1000; i++) {
			Id.add(a, Thread.currentThread().getId());
			System.out.println(Thread.currentThread().getId() + " : " + a);
			S
		}

	};

	static Runnable delete = () -> {
		int a=(int) (Math.random()*10000);
		for (int i = 0; i < 10000; i++) {
			Id.remove(a);
		}
	};

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		// ecriture
		for (int i = 0; i < 10000; i++) {
			Id.add((long)1);
		}
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(insert);
			t.start();

		}
		// lecture
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(delete);
			t.start();
			t.join();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("le temps d'execution est : " + (endTime - startTime));
	}

}
