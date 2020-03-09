package tp56;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class singlethread implements IFilter, IImageFilteringEngine {

	BufferedImage imgIn;
	String prefixe = "TEST_IMAGES/";

	@Override
	public void loadImage(String inputImage) throws Exception {
		this.inImg = ImageIO.read(new File(prefixe + inputImage));

	}

	@Override
	public void writeOutPngImage(String outFile) throws Exception {
		File f = new File(prefixe + outFile);
		ImageIO.write(this.inImg, "png", f);

	}

	@Override
	public void setImg(BufferedImage newImg) {
		this.inImg = newImg;

	}

	@Override
	public BufferedImage getImg() {

		return this.inImg;
	}

	@Override
	public void applyFilter(IFilter someFilter) {
		someFilter.applyFilterAtPoint(x, y, imgIn, imgOut);

	}

	@Override
	public int getMargin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
		// generating new image from original
		for (int x1 = x; x1 < imgIn.getWidth(); x1++) {
			for (int y1 = y; y1 < imgIn.getHeight(); y1++) {
				int rgb = imgIn.getRGB(x1, y1);
				// extracting red, green and blue components from rgb integer
				int red = (rgb >> 16) & 0x000000FF;
				int green = (rgb >> 8) & 0x000000FF;
				int blue = (rgb) & 0x000000FF;
				// computing new color from extracted components
				int newRgb = (((red << 8) | 0) << 8) | 0; // rotating RGB values
				imgOut.setRGB(x1, y1, newRgb);
			} // EndFor y
		} // EndFor x

	}

}
