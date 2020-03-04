package tp56;

/**
 * IImageFilteringEngine.java
 * 
 * Time-stamp: <2017-03-10 14:27:04 ftaiani>
 * @author Francois Taiani   <francois.taiani@irisa.fr>
 * $Id$
 * 
 */

import java.awt.image.*;

/**
 * @author Francois Taiani   <francois.taiani@irisa.fr>
 */
public interface IImageFilteringEngine {

  public void loadImage(String inputImage) throws Exception ;
  public void writeOutPngImage(String outFile) throws Exception ;
  public void setImg(BufferedImage newImg) ;
  public BufferedImage getImg() ;
  public void applyFilter(IFilter someFilter) ;
  
} // EndInterface IImageFilteringEngine
