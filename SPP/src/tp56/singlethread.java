package tp56;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class singlethread implements IImageFilteringEngine {

	BufferedImage imgIn;
	BufferedImage imgOut;
	String prefixe = "TEST_IMAGES/";

	@Override
	public void loadImage(String inputImage) throws Exception {
		this.imgIn = ImageIO.read(new File(prefixe + inputImage));

	}

	@Override
	public void writeOutPngImage(String outFile) throws Exception {

		File f = new File(prefixe + outFile);
		ImageIO.write(this.imgIn, "png", f);

	}

	@Override
	public void setImg(BufferedImage newImg) {

		this.imgIn = newImg;

	}

	@Override
	public BufferedImage getImg() {

		return this.imgIn;
	}

	@Override
	public void applyFilter(IFilter someFilter) {
		// generating new image from original
		for (int x = someFilter.getMargin(); x < imgIn.getWidth(); x++) {
			for (int y = someFilter.getMargin(); y < imgIn.getHeight(); y++) {
				
				applyFilterAtPoint(x,y,imgIn,imgOut);
			} // EndFor y
		} // EndFor x

	}

//	@Override
//	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
//
//				int rgb = imgIn.getRGB(x, y);
//				// extracting red, green and blue components from rgb integer
//				int red = (rgb >> 16) & 0x000000FF;
//				int green = (rgb >> 8) & 0x000000FF;
//				int blue = (rgb) & 0x000000FF;
//				// computing new color from extracted components
//				int newRgb = (((red << 8) | 0) << 8) | 0; // rotating RGB values
//				imgOut.setRGB(x, y, newRgb);
//		
//	}

}
