package tp56;

import java.awt.image.BufferedImage;

public class GrayFilter implements IFilter {

	@Override
	public int getMargin() {
		return 0;
	}

	@Override
	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
		//int rgb=imgIn.getRGB(0, 0);
//		try {
			int rgb = imgIn.getRGB(x, y);
//		}catch(Exception e) {
//			System.out.println("val :"+ x+"   long :"+imgIn.getWidth()+"\n");
//			System.out.println("val :"+ y+"   larg :"+imgIn.getHeight());
//		}
		int red = (rgb >> 16) & 0x000000FF;
		int green = (rgb >> 8) & 0x000000FF;
		int blue = (rgb) & 0x000000FF;
		int grey = (red + green + blue) / 3;
		int toreturn = (((grey << 8) | grey) << 8) | grey;
		imgOut.setRGB(x, y, toreturn);

	}

}