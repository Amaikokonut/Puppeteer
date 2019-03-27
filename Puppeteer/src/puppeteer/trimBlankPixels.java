package puppeteer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class trimBlankPixels

//I totally stole this from someone on stackoverflow because I'm lazy:
//https://stackoverflow.com/questions/7385616/crop-trim-a-jpg-file-with-empty-space-with-java

{
    public static BufferedImage TrimImage(BufferedImage img) {
	final int initialWidth  = img.getWidth();
    final int initialHeight = img.getHeight();
    
    //int transparent = getARGB32(0, 0, 0, 0);
    final int transparent = 0;  //this works for any arrangement of channels XD

    //TRIM X
    int xInclusiveLowBound = initialWidth;
    int xInclusiveHighBound = 0;
    for(int i = 0; i < initialHeight; i++) {
        for(int j = initialWidth - 1; j >= 0; j--) {
            if(img.getRGB(j, i) != transparent &&
                    j < xInclusiveLowBound) {
                xInclusiveLowBound = j;
            }
            if(img.getRGB(j, i) != transparent &&
                    j > xInclusiveHighBound) {
                xInclusiveHighBound = j;
                break;
            }
        }
    }
    
    //TRIM Y
    int yInclusiveLowBound = initialHeight;
    int yInclusiveHighBound = 0;
    for(int i = 0; i < initialWidth; i++) {
        for(int j = initialHeight - 1; j >= 0; j--) {
            if(img.getRGB(i, j) != transparent &&
                    j < yInclusiveLowBound) {
                yInclusiveLowBound = j;
            }
            if(img.getRGB(i, j) != transparent &&
                    j > yInclusiveHighBound) {
                yInclusiveHighBound = j;
                break;
            }
        }
    }

    //adjustments might be needed
    if (xInclusiveLowBound > 0)
    	xInclusiveLowBound--;
    if (yInclusiveLowBound > 0)
    	yInclusiveLowBound--;
    
    
    // For anything; string/array/list indexes/sizes, x's/widths, y's/heights, matrix elements—anything :D
    //	ExclusiveHighBound = InclusiveHighBound + 1
    //	Interval Size = ExclusiveHighBound - InclusiveLowBound
    //	Interval Size = (InclusiveHighBound + 1) - InclusiveLowBound
    //	Interval Size = InclusiveHighBound + 1 - InclusiveLowBound
    //	Interval Size = InclusiveHighBound - InclusiveLowBound + 1
    //		^w^
    
    final int finalWidth = xInclusiveHighBound - xInclusiveLowBound + 1;
    final int finalHeight = yInclusiveHighBound - yInclusiveLowBound + 1;
    
    return img.getSubimage(xInclusiveLowBound, yInclusiveLowBound, finalWidth, finalHeight);
}
    
    
    
    
    
    public static int getARGB32(int alpha, int red, int green, int blue)
    {
    	return (alpha & 0xff) << 24 | (red & 0xff) << 16 | (green & 0xff) << 8 | (blue & 0xff);
    }
}

