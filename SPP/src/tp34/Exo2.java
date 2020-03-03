package tp34;

import java.util.concurrent.Exchanger;

public class Exo2 {
	static Exchanger<String> exchanger = new Exchanger<String>();
	static Runnable Alice = () -> {
		String currentString = "Ping";
		try {
			Thread.currentThread().getName();
			currentString = exchanger.exchange(currentString);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

	static Runnable Bob = () -> {
		String currentString = "Pong";

		try {
			Thread.currentThread().getName();
			currentString = exchanger.exchange(currentString);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {

			Thread alice = new Thread(Alice);
			System.out.println("Iteration :"+i+" ");
			System.out.print(alice.getName());
		}
		for (int i = 0; i < 5; i++) {
			Thread bob = new Thread(Bob);
			System.out.println("Iteration :"+i+" ");
			System.out.print(Thread.currentThread().getName());
		}

	}
}
