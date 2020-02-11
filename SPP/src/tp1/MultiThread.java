package tpSPP;

import java.io.ObjectInputStream.GetField;

public class MultiThread {
	static long count = 0;

	static Runnable lecture = () -> {
		long loc = count;
		for (int i = 0; i < 100000; i++) {
			if (i % 20000 == 0) {
				System.out.println(Thread.currentThread().getId()+" : "+loc);
			}
		}
	};

	static Runnable ecriture = () -> {
		for (int i = 0; i < 100000; i++) {
			count++;
		}

	};

	public static void main(String[] args) {
		// ecriture
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(ecriture);
			t.start();
		}
		// lecture
		for (int i = 0; i < 15; i++) {
			Thread t = new Thread(lecture);
			t.start();
		}
	}

}
