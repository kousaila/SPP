/**
 * 
 */
package tp56;

import java.util.concurrent.BrokenBarrierException;

/**
 * @author sievi
 *
 */
public class ThreadWorker implements Runnable {

	int debut;
	int fin;
	IFilter filter;
	String name;

	public ThreadWorker(String name, int deb, int end, IFilter someFilter) {
		this.debut = deb;
		this.fin = end;
		this.filter = someFilter;
		this.name = name;
	}

	public ThreadWorker() {

	}

	@Override
	public synchronized void run() {

		while (true) {
			try {
				MultiThreadedImageFilteringEngine.barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
//				return;
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
				return;
			}
		}
	}

}
