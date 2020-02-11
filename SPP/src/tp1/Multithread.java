package tp1;
import java.util.concurrent.locks.*;

public class Multithread extends Thread{
static long count =0;
public static ReentrantLock myLock= new ReentrantLock();
public void run() {
	for (int i = 0; i < 100000; i++) {
		myLock.lock();
		Multithread.count++;
		myLock.unlock();
	}
}
	public static void main(String[] args) throws InterruptedException {
		long startTime=System.currentTimeMillis();
		for (int i = 0; i < 5; i++) {
			Thread t=new Multithread();
			t.start();
		
		}
		for (int i = 0; i < 15; i++) {
			Thread t=new ThreadPrinter();
			t.start();
			t.join();
		}
		long endTime=System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}

}
