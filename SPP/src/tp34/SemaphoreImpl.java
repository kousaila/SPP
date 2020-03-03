package tp34;

public class SemaphoreImpl implements SemaphoreInterface {
	int counter;
	int waiting;

	public SemaphoreImpl() {
		this.counter = 0;
		this.waiting = 0;
	}

	@Override
	public synchronized void up() {
		this.counter++;
		if (counter <= 0) {
			notify();
			if (waiting > 0) {
				waiting--;
			}
		}

	}

	@Override
	public synchronized void down() {
		this.counter--;
		if (counter < 0) {
			this.waiting++;
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			;
			
		}

	}

	@Override
	public int releaseAll() {
		int temp =waiting; 
		counter+=waiting; 
		notifyAll(); 
		waiting=0;
	
		return temp;
	}

}
