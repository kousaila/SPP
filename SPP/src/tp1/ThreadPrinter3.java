package tp1;

public class ThreadPrinter3 extends Thread{
	
	
	public void run() {
		for (int i = 0; i < 1000; i++) {
			MultiThreadV3.r.lock();
			try {
				ThreadPrinter3.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long locvar=Multithread.count;
			MultiThreadV3.r.unlock();
			if (i%200==0) {
				System.out.println("id= "+this.getId()+", cout= "+locvar);
			}			
		}
	}
	public static void main(String[] args) {
	
	}

}