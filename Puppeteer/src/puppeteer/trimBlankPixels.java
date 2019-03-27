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
    int imgHeight = img.getHeight();
    int imgWidth  = img.getWidth();
    int transparent = (0 & 0xff) << 24 | (0 & 0xff) << 16 | (0 & 0xff) << 8 | (0 & 0xff);

    //TRIM WIDTH
    int widthStart  = imgWidth;
    int widthEnd = 0;
    for(int i = 0; i < imgHeight; i++) {
        for(int j = imgWidth - 1; j >= 0; j--) {
            if(img.getRGB(j, i) != transparent &&
                    j < widthStart) {
                widthStart = j;
            }
            if(img.getRGB(j, i) != transparent &&
                    j > widthEnd) {
                widthEnd = j;
                break;
            }
        }
    }
    //TRIM HEIGHT
    int heightStart = imgHeight;
    int heightEnd = 0;
    for(int i = 0; i < imgWidth; i++) {
        for(int j = imgHeight - 1; j >= 0; j--) {
            if(img.getRGB(i, j) != transparent &&
                    j < heightStart) {
                heightStart = j;
            }
            if(img.getRGB(i, j) != transparent &&
                    j > heightEnd) {
                heightEnd = j;
                break;
            }
        }
    }

    //adjustments might be needed
    if (widthStart > 0)
    	widthStart--;
    if (heightStart > 0)
    	heightStart--;
    
    int finalWidth = widthEnd - widthStart;
    int finalHeight = heightEnd - heightStart;
    
    finalWidth++;
    finalHeight++;
    
    return img.getSubimage(widthStart, heightStart, finalWidth, finalHeight);
}
}

