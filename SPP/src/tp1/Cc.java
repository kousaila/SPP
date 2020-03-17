package tp1;

public class Cc {
	private int counter = 0;

	synchronized public void inc() {
		counter++;
	}

	public synchronized void incPair(Cc other) {
		this.inc();
		other.inc();
	}

	public int getCounter() {
		return counter;
	}

	public static void main(String[] args) {
		Cc ba = new Cc();
		@SuppressWarnings("unused")
		Cc baOther = new Cc();
		ba.incPair(ba);
		System.out.println(ba.counter);

	}

}
