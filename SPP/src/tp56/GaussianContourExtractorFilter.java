package tp56;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GaussianContourExtractorFilter implements IFilter{

	@Override
	public int getMargin() {
		
		return 1;
	}

	@Override
	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
//		 int rgb    = imgIn.getRGB(x,y);
//		 int rgbXPlus= imgIn.getRGB(x+1, y);
//		 int rgbXmoin=  imgIn.getRGB(x-1, y);
//		 int rgbYPlus=  imgIn.getRGB(x, y+1);
//		 int rgbYmoin= imgIn.getRGB(x, y-1);
//    
//		 int blueXPlus   = (rgbXPlus ) & 0x000000FF;
//		 int blueXmoin  = (rgbXmoin ) & 0x000000FF;
//		 int blueYPlus   = (rgbYPlus ) & 0x000000FF;
//		 int blueYmoin   = (rgbYmoin ) & 0x000000FF;
//    
//      double horGrad= 0.5*(blueXPlus-blueXmoin);
//      double verGrad=0.5*(blueYPlus-blueYmoin);
//      
//      double delta=Math.sqrt(Math.pow(horGrad, 2)+Math.pow(verGrad, 2));
//      // computing new color from extracted components
//      int res =(int)Math.max(0.0,(255-(double)(8*delta))) ;
//      int newRgb = ( ( (res << 8) | res ) << 8 ) |  res ;// rotating RGB values
//      imgOut.setRGB(x-this.getMargin(),y-this.getMargin(),(int) newRgb);
		double dx=0,dy=0;
		for (int i = -getMargin(); i < getMargin(); i++) {
			for (int j = -getMargin(); j < getMargin(); j++) {
				int rgb=imgIn.getRGB(x+i, y+j);
				int bleu=rgb& 0x000000FF;
				double temp=bleu*Math.exp(-0.25*(Math.pow(i, 2)+Math.pow(j, 2)));
				dx+= Integer.signum(i)*temp;
				dy+= Integer.signum(j)*temp;							
			}
		}
		
		double res = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
		int gaussian= (int)	Math.max(0.0, (double)255-0.5*res);
		imgOut.setRGB(x-this.getMargin(), y-this.getMargin(), new Color(gaussian,gaussian,gaussian).getRGB());
		
		
	}

}
