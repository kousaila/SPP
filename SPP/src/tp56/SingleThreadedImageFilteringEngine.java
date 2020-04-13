package tp56;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SingleThreadedImageFilteringEngine implements IImageFilteringEngine{

    BufferedImage inImg ,outImg;
    int margin;
    String prefixe="TEST_IMAGES/";
	@Override
	public void loadImage(String inputImage) throws Exception {
		this.inImg=ImageIO.read(new File(prefixe+inputImage));
		
	}

	@Override
	public void writeOutPngImage(String outFile) throws Exception {
	    File f = new File(prefixe+outFile);
	    ImageIO.write(this.outImg, "png", f);
		
	}

	@Override
	public void setImg(BufferedImage newImg) {
		this.outImg=newImg;
		
	}

	@Override
	public BufferedImage getImg() {
		
		return this.outImg;
	}

	@Override
	public void applyFilter(IFilter someFilter) {
		margin=someFilter.getMargin();
		outImg = new BufferedImage(inImg.getWidth()-2*margin,
				inImg.getHeight()-2*margin,
				BufferedImage.TYPE_INT_RGB);
		for (int x = someFilter.getMargin(); x <inImg.getWidth()-someFilter.getMargin(); x++) {
			for (int y = someFilter.getMargin(); y < inImg.getHeight()-someFilter.getMargin(); y++) {
				someFilter.applyFilterAtPoint(x, y, inImg,outImg);
			}

		}
		this.inImg = this.outImg;
//        this.outImg = new BufferedImage(this.inImg.getWidth() - 2 * someFilter.getMargin(),
//                this.inImg.getHeight() - 2 * someFilter.getMargin(),
//                BufferedImage.TYPE_INT_RGB);
//
//        for(int x = someFilter.getMargin(); x < this.inImg.getWidth() - someFilter.getMargin(); x++) {
//            for(int y = someFilter.getMargin(); y < this.inImg.getHeight() - someFilter.getMargin(); y++) {
//                someFilter.applyFilterAtPoint(x, y, this.inImg, this.outImg);
//            }
//        }
//        this.inImg = this.outImg;
//		
		}

}