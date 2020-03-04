package tp56;

/**
 * SimpleImageProcessingExample.java
 * 
 * Time-stamp: <2017-03-09 22:26:51 ftaiani>
 * @author Francois Taiani   <francois.taiani@irisa.fr>
 * $Id$
 * 
 */

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.Color ;
import java.io.File;

/**
 * @author Francois Taiani   <francois.taiani@irisa.fr>
 */
public class SimpleImageProcessingExample {

  static public void main(String[] args) throws Exception {
    // reading image in
    BufferedImage inImg  = ImageIO.read(new File("TEST_IMAGES/15226222451_5fd668d81a_c.jpg"));
    // creating new image
    BufferedImage outImg = new BufferedImage(inImg.getWidth(),
                                             inImg.getHeight(),
                                             BufferedImage.TYPE_INT_RGB);
    // generating new image from original
    for (int x = 0; x < inImg.getWidth(); x++) {
      for (int y = 0; y < inImg.getHeight(); y++) {
          int rgb    = inImg.getRGB(x,y);
          // extracting red, green and blue components from rgb integer
          int red    = (rgb >> 16) & 0x000000FF;
          int green  = (rgb >>  8) & 0x000000FF;
          int blue   = (rgb      ) & 0x000000FF;
          // computing new color from extracted components
          int newRgb = ( ( (red << 8) | 0 ) << 8 ) | 0 ; // rotating RGB values
          outImg.setRGB(x,y,newRgb);
      } // EndFor y
    } // EndFor x

    // writing out new image
    File f = new File("TEST_IMAGES/tmp.png");
    ImageIO.write(outImg, "png", f);
    
  } // EndMain

} // EndClass SimpleImageProcessingExample
