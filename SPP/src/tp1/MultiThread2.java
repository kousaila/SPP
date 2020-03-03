package tp1;


import java.util.concurrent.locks.ReentrantLock;

public class MultiThread2 {
	static long count = 0;
	static ReentrantLock myLock= new ReentrantLock();
	
	static Runnable lecture = () -> {
		
		for (int i = 0; i < 100000; i++) {
			myLock.lock();
			long loc = count;
			
			if (i % 20000 == 0) {
				System.out.println(Thread.currentThread().getId()+" : "+loc);
			}
			myLock.unlock();
		}
		
	};

	static Runnable ecriture = () -> {
		for (int i = 0; i < 100000; i++) {
			myLock.lock();
			count++;
			myLock.unlock();
		}

	};

	public static void main(String[] args) throws InterruptedException {
		long startTime= System.currentTimeMillis();
		// ecriture
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(ecriture);
			t.start();
		}
		// lecture
		for (int i = 0; i < 15; i++) {
			Thread t = new Thread(lecture);
			t.start();
			t.join();
		}
		long endTime=System.currentTimeMillis();
		System.out.println("le temps d'execution est : " +(endTime-startTime));
	}

}
