package tp1;

public class ThreadPrinter extends Thread{
	public void run() {
		for (int i = 0; i < 100000; i++) {
			long locvar=Multithread.count;
			if (i%20000==0) {
				System.out.println("id= "+this.getId()+", cout= "+locvar);
			}			
		}
	}
	public static void main(String[] args) {
	
	}

}
