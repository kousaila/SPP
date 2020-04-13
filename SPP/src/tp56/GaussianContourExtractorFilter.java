package tp56;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class GaussianContourExtractorFilter implements IFilter{

	@Override
	public int getMargin() {
		
		return 5;
	}

	@Override
	public void applyFilterAtPoint(int x, int y, BufferedImage imgIn, BufferedImage imgOut) {
		double dx=0,dy=0;
		for (int i = -getMargin(); i <= getMargin(); i++) {
			for (int j = -getMargin(); j <= getMargin(); j++) {
				int rgb=imgIn.getRGB(x+i, y+j);
				int bleu=rgb& 0x000000FF;
				double temp=bleu*Math.exp(-0.25*(Math.pow(i, 2)+Math.pow(j, 2)));
				dx+= Integer.signum(i)*temp;
				dy+= Integer.signum(j)*temp;							
			}
		}
		
		double res = Math.sqrt(Math.pow(dx, 2)+Math.pow(dy, 2));
		int gaussian= (int)	Math.round(Math.max(0.0, (double)255-0.5*res));
		imgOut.setRGB(x-this.getMargin(), y-this.getMargin(), new Color(gaussian,gaussian,gaussian).getRGB());

		
	}

}
