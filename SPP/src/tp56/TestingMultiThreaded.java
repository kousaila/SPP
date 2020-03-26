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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author sievi
 *
 */
public class TestingMultiThreaded {
//	public static int count=0;
	Random random = new Random();
	int num = random.nextInt(10) + 1;
	MultiThreadedImageFilteringEngine im = new MultiThreadedImageFilteringEngine(8);
	String originalpath="15226222451_5fd668d81a_c.jpg";
	String grayImgpath="TEST_IMAGES/15226222451_5fd668d81a_c_gray.png";
	String gaussianImgpath="TEST_IMAGES/15226222451_5fd668d81a_c_gaussian_contour.png";
	private static boolean compareImages(BufferedImage set,BufferedImage original) {
		boolean res=true;
		if (set.getWidth()!=original.getWidth()||set.getHeight()!=original.getHeight()){
			return !res;
		}
		for (int i = 0; i < original.getWidth(); i++) {
			for (int j = 0; j < original.getHeight(); j++) {
				if (original.getRGB(i, j)!=set.getRGB(i, j)) {
					System.out.println("1 : "+i+"   "+original.getRGB(i, j)+"\n2 : "+j+"    "+set.getRGB(i, j));
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
//	 * Test method for
//	 * {@link tp56.MultiThreadedImageFilteringEngine#MultiThreadedImageFilteringEngine(int)}.
//	 */
//	@Test
//	public void testMultiThreadedImageFilteringEngine() {
//		fail("Not yet implemented");
//	}

//	/**
//	 * Test method for
//	 * {@link tp56.MultiThreadedImageFilteringEngine#setImg(java.awt.image.BufferedImage)}.
//	 */
//	@Test
//	public void testSetImg() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link tp56.MultiThreadedImageFilteringEngine#getImg()}.
//	 */
//	@Test
//	public void testGetImg() {
//		fail("Not yet implemented");
//	}

//	/**
//	 * Test method for
//	 * {@link tp56.MultiThreadedImageFilteringEngine#applyFilter(tp56.IFilter)}.
//	 */
//	@Test
//	public void testApplyFilter() {
//		fail("Not yet implemented");
//	}

//	// 0
//	@Test
//	public void testBlackGreyFilter() throws Exception {
//		im.loadImage("FourCircles.png");
//		im.applyFilter(new GrayFilter());
//		// reading image in
//		BufferedImage inImg = ImageIO.read(new File("TEST_IMAGES/FourCircles_gray.png"));
//		File f = new File("TEST_IMAGES/tmp"+10+".png");
//		ImageIO.write(im.getImg(), "png", f);
//		assertTrue("pas les même images", sameImg(im, inImg,0) == true);
//	}
//
//	// 1
//	@Test
//	public void testWhiteGreyFilter() throws Exception {
//		im.loadImage("15226222451_5fd668d81a_c.jpg");
//		im.applyFilter(new GrayFilter());
//		// reading image in
//		BufferedImage inImg = ImageIO.read(new File("TEST_IMAGES/testGrey.png"));
//
//		assertTrue("pas les même images", sameImg(im, inImg,1) == true);
//	}
//
//	// 2
//	@Test
//	public void testColorGreyFilterCircles() throws Exception {
//		im.loadImage("FourCircles.png");
//		im.applyFilter(new GrayFilter());
//		// reading image in
//		BufferedImage inImg = ImageIO.read(new File("TEST_IMAGES/FourCircles_Gray.png"));
//
//		assertTrue("pas les même images", sameImg(im, inImg,2) == true);
//	}
//
//	// 3
//	@Test
//	public void testColorContourFilterCircles() throws Exception {
//		im.loadImage("FourCircles_Gray.png");
//		im.applyFilter(new GrayFilter());
//		// reading image in
//		BufferedImage inImg = ImageIO.read(new File("TEST_IMAGES/FourCircles_Gray_Contour.png"));
//
//		assertTrue("pas les même images", sameImg(im, inImg,3) == true);
//	}
//

//
//	// 5
//	@Test
//	public void testWhiteContourFilter() throws Exception {
//		im.loadImage("WhiteRec.png");
//		im.applyFilter(new BasicContourExtractorFilter());
//		// reading image fin
//		BufferedImage inImg = ImageIO.read(new File("TEST_IMAGES/WhiteRecContour.png"));
//
//		assertTrue("pas les même images", sameImg(im, inImg,5) == true);
//	}
	
	
	/**
	 * Test method for {@link tp56.SingleThreadedImageFilteringEngine#applyFilter(tp56.IFilter)}.
	 * @throws Exception 
	 */
	@Test
	public void testApplyFilterGray() throws Exception {
		MultiThreadedImageFilteringEngine imtotest= new MultiThreadedImageFilteringEngine(112);
		imtotest.loadImage(originalpath);
		imtotest.applyFilter(new GrayFilter());
		imtotest.writeOutPngImage("gen1.png");
		BufferedImage grayimg  = ImageIO.read(new File(grayImgpath));
		assertTrue(compareImages(grayimg, imtotest.getImg()));
		
	}

}
