package tp56;

import java.awt.image.BufferedImage;

public class GrayFilter implements IFilter{

	@Override
	public int getMargin() {
		return 0;
	}

	@Override
	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
		int rgb= imgIn.getRGB(x, y);
		 int red    = (rgb >> 16) & 0x000000FF;
	     int green  = (rgb >>  8) & 0x000000FF;
	     int blue   = (rgb      ) & 0x000000FF;
	     int grey= (red+green+blue)/3;
	     int toreturn = ( ( (grey << 8) | grey ) << 8 ) |  grey ;
	     imgOut.setRGB(x,y,toreturn);
		
	}

}
