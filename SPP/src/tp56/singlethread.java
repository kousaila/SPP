package tp56;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class singlethread implements IFilter,IImageFilteringEngine{

    BufferedImage inImg ;
    String prefixe="TEST_IMAGES/";
	@Override
	public void loadImage(String inputImage) throws Exception {
		this.inImg=ImageIO.read(new File(prefixe+inputImage));
		
	}

	@Override
	public void writeOutPngImage(String outFile) throws Exception {
	    File f = new File(prefixe+outFile);
	    ImageIO.write(this.inImg, "png", f);
		
	}

	@Override
	public void setImg(BufferedImage newImg) {
		this.inImg=newImg;
		
	}

	@Override
	public BufferedImage getImg() {
		
		return this.inImg;
	}

	@Override
	public void applyFilter(IFilter someFilter) {
		
		
	}

	@Override
	public int getMargin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
		// TODO Auto-generated method stub
		
	}

}
