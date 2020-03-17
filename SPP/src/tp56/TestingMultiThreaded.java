/**
 * 
 */
package tp56;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
	MultiThreadedImageFilteringEngine im = new MultiThreadedImageFilteringEngine(5);
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
					System.out.println("1 :"+original.getRGB(i, j)+"\n2 :"+set.getRGB(i, j));
					return !res;
				}
			}
		}
		return res;
	}
	
	public boolean sameImg(MultiThreadedImageFilteringEngine i, BufferedImage initImage,int num) throws IOException {
		System.out.println("test "+num+" : \n"+ " taille init : " + initImage.getHeight() + " " + initImage.getWidth() + "\n taille fin :"
				+ i.getImg().getHeight() + " " + i.getImg().getWidth());
		
		if (i.getImg().getWidth() != initImage.getWidth() || i.getImg().getHeight() != initImage.getHeight()) {
			System.out.println("pas la même taille");
		}
		for (int x = 0; x < initImage.getWidth(); x++) {
			for (int y = 0; y < initImage.getHeight(); y++) {
				if (initImage.getRGB(x, y) != i.getImg().getRGB(x, y)) {
					System.out.println("1 : "+ initImage.getRGB(x, y)+"\n2 : "+ i.getImg().getRGB(x, y)); 
					return false;
				}
			} // EndFor y
		} // EndFor x
		return true;
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
//	// 4
//	@Test
//	public void testBlackContourFilter() throws Exception {
//		im.loadImage("FourCircles.png");
//		im.applyFilter(new BasicContourExtractorFilter());
//		// reading supposed result image
//		BufferedImage inImg = ImageIO.read(new File("TEST_IMAGES/FourCircles.png"));
//
//		assertTrue("pas les même images", sameImg(im, inImg,4) == true);
//	}
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
	
	//6
	@Test
	public void testGrey() throws Exception {
		im.loadImage(originalpath);
		im.applyFilter(new GrayFilter());
		// reading image fin
		BufferedImage inImg = ImageIO.read(new File(grayImgpath));
//		BufferedImage inImg = ImageIO.read(new File("TEST_IMAGES/FourCircles_gray.png"));
		File f = new File("TEST_IMAGES/tmp"+8+".png");
		ImageIO.write(im.getImg(), "png", f);
		im.writeOutPngImage("gen.png");
		assertTrue("pas les même images", sameImg(im, inImg,6) == true);
	}
	/**
	 * Test method for {@link tp56.SingleThreadedImageFilteringEngine#applyFilter(tp56.IFilter)}.
	 * @throws Exception 
	 */
	@Test
	public void testApplyFilterGray() throws Exception {
		IImageFilteringEngine imtotest= new SingleThreadedImageFilteringEngine();
		imtotest.loadImage(originalpath);
		imtotest.applyFilter(new RedFilter());
		File f = new File("TEST_IMAGES/tmp"+9+".png");
		ImageIO.write(im.getImg(), "png", f);
		//im.writeOutPngImage("gen.png");
		BufferedImage grayimg  = ImageIO.read(new File("TEST_IMAGES/tmp.png"));
		assertTrue(compareImages(grayimg, imtotest.getImg()));
		
	}

}
