/**
 * 
 */
package tp56;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import javax.imageio.ImageIO;

/**
 * @author sievi
 *
 */
public class MultiThreadedImageFilteringEngine implements IImageFilteringEngine {

	private static int numberThread;
	static CyclicBarrier barrier;
	static CyclicBarrier barrier2;
	static BufferedImage imgIn;
	static BufferedImage imgOut;
	public static int count = 0;
	int margin;
	String prefixe = "TEST_IMAGES/";
	String image = "15226222451_5fd668d81a_c.jpg";

	public MultiThreadedImageFilteringEngine(int p) {
		MultiThreadedImageFilteringEngine.numberThread = p;

		// initialisation des barrières
		barrier = new CyclicBarrier(p);
		barrier2 = new CyclicBarrier(p);

	}

	@Override
	public void loadImage(String inputImage) throws Exception {
		MultiThreadedImageFilteringEngine.imgIn = ImageIO.read(new File(prefixe + inputImage));

	}

	@Override
	public void writeOutPngImage(String outFile) throws Exception {

		File f = new File(prefixe + outFile);
		ImageIO.write(MultiThreadedImageFilteringEngine.imgOut, "png", f);

	}

	@Override
	public void setImg(BufferedImage newImg) {

		MultiThreadedImageFilteringEngine.imgIn = newImg;

	}

	@Override
	public BufferedImage getImg() {

		return MultiThreadedImageFilteringEngine.imgOut;
	}

	@Override
	public void applyFilter(IFilter someFilter) throws IOException {

		int sizeDeb = someFilter.getMargin();
		imgOut = new BufferedImage(imgIn.getWidth() - 2 * someFilter.getMargin(),
				imgIn.getHeight() - 2 * someFilter.getMargin(), BufferedImage.TYPE_INT_RGB);
		int marge = ((imgOut.getHeight() - 2 * someFilter.getMargin())
				/ (MultiThreadedImageFilteringEngine.numberThread));
		int sizeEnd = sizeDeb + marge;
		if (marge * MultiThreadedImageFilteringEngine.numberThread == imgOut.getHeight()) {

			for (int i = 0; i < MultiThreadedImageFilteringEngine.numberThread; i++) {
				System.out.println(sizeDeb + "  " + sizeEnd);
				ThreadWorker g = new ThreadWorker("t" + i, sizeDeb, sizeEnd, someFilter);
				Thread t = new Thread(g);
				t.start();
				sizeDeb = sizeEnd;
				sizeEnd += marge;
				// System.out.println(sizeDeb+" "+sizeEnd);
				// someFilterA=someFilter;
//			threadWorking[i].setFilter(someFilter); 
//			//attribut filter
//			threadWorking[i].setDebut(size);
//			
//			sizeEnd += ((imgOut.getHeight() - someFilter.getMargin()) / this.numberThread);
//			threadWorking[i].setFin(size);
				System.out.println("thread" + g.name);

			}

		} else {
			System.out.println("nombre de thread pas diviseur de l'image");
			for (int i = 0; i < MultiThreadedImageFilteringEngine.numberThread; i++) {
				System.out.println(sizeDeb + "  " + sizeEnd);
				ThreadWorker g = new ThreadWorker("t" + (i + 1), sizeDeb, sizeEnd, someFilter);
				Thread t = new Thread(g);
				t.start();
				sizeDeb = sizeEnd;
				if (MultiThreadedImageFilteringEngine.numberThread - 2 == i) {
					sizeEnd = imgOut.getHeight();
				} else {
					sizeEnd += marge;
				}
				System.out.println("thread" + g.name);

			}

		}
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

		try {
			barrier2.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

}
