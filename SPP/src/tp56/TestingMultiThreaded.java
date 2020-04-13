/**
 * 
 */
package tp56;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author sievi
 *
 */
public class TestingMultiThreaded {

	// initialisation des images
	String graytoLoad = "15226222451_5fd668d81a_c_gray.png";
	String originalpath = "15226222451_5fd668d81a_c.jpg";
	String grayImgpath = "TEST_IMAGES/15226222451_5fd668d81a_c_gray.png";
	String gaussianImgpath = "TEST_IMAGES/15226222451_5fd668d81a_c_gaussian_contour.png";

	private static boolean compareImages(BufferedImage set, BufferedImage original) {
		boolean res = true;
		if (set.getWidth() != original.getWidth() || set.getHeight() != original.getHeight()) {
			return !res;
		}

		for (int j = 0; j < original.getWidth(); j++) {
			for (int i = 0; i < original.getHeight(); i++) {
				if (original.getRGB(j, i) != set.getRGB(j, i)) {
					System.out.println(
							"1 : " + i + "   " + original.getRGB(i, j) + "\n2 : " + j + "    " + set.getRGB(i, j));
					return !res;
				}
			}
		}
		return res;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link tp56.SingleThreadedImageFilteringEngine#applyFilter(tp56.IFilter)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testApplyFilterGray() throws Exception {
		IImageFilteringEngine imtotest = new MultiThreadedImageFilteringEngine(19);
		imtotest.loadImage(originalpath);
		imtotest.applyFilter(new GrayFilter());
		imtotest.writeOutPngImage("genaratedGray1.png");
		BufferedImage grayimg = ImageIO.read(new File(grayImgpath));
		assertTrue(compareImages(grayimg, imtotest.getImg()));

	}
	@Test
	public void testApplyFilterGaussian() throws Exception {
		IImageFilteringEngine imtotestA= new MultiThreadedImageFilteringEngine(2);
		imtotestA.loadImage(graytoLoad);
		imtotestA.applyFilter(new GaussianContourExtractorFilter());
		imtotestA.writeOutPngImage("genaratedGaussian1.png");
		BufferedImage gaussianim  = ImageIO.read(new File(gaussianImgpath));
		assertTrue(compareImages(imtotestA.getImg(),gaussianim));
	}

}
