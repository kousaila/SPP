/**
 * 
 */
package tp56;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author sievi
 *
 */
public class ThreadWorker extends Thread {

	int debut;
	int fin;
	IFilter filter;
	int number;

	public ThreadWorker(int number, int deb, int end, IFilter someFilter) {
		this.debut = deb;
		this.fin = end;
		this.filter = someFilter;
		this.number = number;
	}

	public ThreadWorker() {

	}

	public void run() {

		while (true) {
			try {
				MultiThreadedImageFilteringEngine.barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (int y = this.debut; y < this.fin; y++) {
				for (int x = this.filter.getMargin(); x < MultiThreadedImageFilteringEngine.imgIn.getWidth()
						- this.filter.getMargin(); x++) {
					this.filter.applyFilterAtPoint(x, y, MultiThreadedImageFilteringEngine.imgIn,
							MultiThreadedImageFilteringEngine.imgOut);
				}
			}
			try {
				MultiThreadedImageFilteringEngine.barrier2.await();
			} catch (InterruptedException | BrokenBarrierException e) {

				e.printStackTrace();
			}
		}
	}

}
