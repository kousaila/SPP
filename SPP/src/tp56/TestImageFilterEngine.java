/**
 * 
 */
package tp56;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author KOUKOU
 *
 */
public class TestImageFilterEngine {

	String originalpath="15226222451_5fd668d81a_c.jpg";
	String grayImgpath="TEST_IMAGES/15226222451_5fd668d81a_c_gray.png";
	String gaussianImgpath="TEST_IMAGES/15226222451_5fd668d81a_c_gaussian_contour.png";
	private static boolean compareImages(BufferedImage set,BufferedImage original) {
		boolean res=true;
		if (set.getWidth()!=original.getWidth()||set.getHeight()!=original.getHeight()){
			System.out.println("1 : "+original.getWidth()+"\n2 : "+set.getHeight());
			return !res;
		}
		for (int i = 0; i < original.getHeight(); i++) {
			for (int j = 0; j < original.getWidth(); j++) {
				if (original.getRGB(j, i)!=set.getRGB(j, i)) {
					System.out.println("1 : "+original.getRGB(j, i)+"\n2 : "+set.getRGB(j, i));
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

//	/**
//	 * Test method for {@link tp56.SingleThreadedImageFilteringEngine#loadImage(java.lang.String)}.
//	 */
//	@Test
//	public void testLoadImage() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link tp56.SingleThreadedImageFilteringEngine#writeOutPngImage(java.lang.String)}.
	 */
	@Test
	public void testWriteOutPngImage() {
		
	}

//	/**
//	 * Test method for {@link tp56.SingleThreadedImageFilteringEngine#setImg(java.awt.image.BufferedImage)}.
//	 */
//	@Test
//	public void testSetImg() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link tp56.SingleThreadedImageFilteringEngine#getImg()}.
//	 */
//	@Test
//	public void testGetImg() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link tp56.SingleThreadedImageFilteringEngine#applyFilter(tp56.IFilter)}.
	 * @throws Exception 
	 */
	@Test
	public void testApplyFilterGray() throws Exception {
		SingleThreadedImageFilteringEngine imtotest= new SingleThreadedImageFilteringEngine();
		imtotest.loadImage(originalpath);
		imtotest.applyFilter(new GrayFilter());
		BufferedImage grayimg  = ImageIO.read(new File(grayImgpath));
		imtotest.writeOutPngImage("kam.png");
		assertTrue(compareImages(grayimg, imtotest.getImg()));
		
	}
	@Test
	public void testApplyFilterGaussian() throws Exception {
		SingleThreadedImageFilteringEngine imtotest= new SingleThreadedImageFilteringEngine();
		imtotest.loadImage(originalpath);
		imtotest.applyFilter(new GaussianContourExtractorFilter());
		imtotest.writeOutPngImage("genaratedGaussian.png");
		BufferedImage guass  = ImageIO.read(new File(gaussianImgpath));
		assertTrue(compareImages(imtotest.getImg(),guass));
	}


}