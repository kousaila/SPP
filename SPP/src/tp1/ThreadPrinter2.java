package tp1;


public class ThreadPrinter2 extends Thread{
	
	public void run() {
		for (int i = 0; i < 100000; i++) {
			MultiThreadV2.r.lock();
			long locvar=Multithread.count;
			MultiThreadV2.r.unlock();
			if (i%20000==0) {
				System.out.println("id= "+this.getId()+", cout= "+locvar);
			}			
		}
	}
	public static void main(String[] args) {
	
	}

}
