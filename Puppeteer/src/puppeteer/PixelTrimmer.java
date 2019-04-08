package puppeteer;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PixelTrimmer

// I totally stole this from someone on stackoverflow because I'm lazy:
// https://stackoverflow.com/questions/7385616/crop-trim-a-jpg-file-with-empty-space-with-java

{
	
	//Separating it out so the real meat of the code does 
	public BufferedImage TrimImage(BufferedImage img) {
		Rectangle bounds = getImageContentBounds_Original(img);
		return img.getSubimage(bounds.x, bounds.y, bounds.width, bounds.height);
	}
	
	
	
	
	
	
	
	public Rectangle getImageContentBounds_PuppyModified(BufferedImage img)
	{
		final int initialWidth = img.getWidth();
		final int initialHeight = img.getHeight();
		
		
		// int transparent = getARGB32(0, 0, 0, 0);
		final int transparent = 0; // this works for any arrangement of channels XD    —PP
		
		
		
		
		//All our best-laid codes will fail if the entire thing is empty—I mean, the low bound would be > than the high bound! XD     So we have to special-case it :3    —PP
		boolean entireImageEmpty = true;
		
		
		
		
		// Find X bounds :>    —PP
		int xInclusiveLowBound = initialWidth;  //Start it off at the worst-case, then if we find anything, it'll always be better (croppier XD) or the same as this :D    —PP
		int xInclusiveHighBound = 0;  //Start it off at the worst-case, then if we find anything, it'll always be better (croppier XD) or the same as this :D    —PP
		
		for (int y = 0; y < initialHeight; y++)  //Scan each row (order doesn't matter)  :3    —PP
		{
			boolean allEmpty = true;
			
			//Scan X's low to high to find the first non-empty pixel for this row :>    —PP
			for (int x = 0; x < initialWidth; x++)
			{
				if (img.getRGB(x, y) != transparent && x < xInclusiveLowBound)  //if (this pixel is nonempty && it's lower than for all other previous rows), then make this the new global lowest-nonempty-X-value! :D    —PP
				{
					xInclusiveLowBound = x;
					allEmpty = false;
					break;  //No point in keeping going XD stop the loop early! :D    —PP
				}
			}
			
			if (!allEmpty)  //Let the first loop tell the second loop if there's no point in it running XD    —PP
			{
				entireImageEmpty = false;
				
				//Scan X's high to low to find the last non-empty pixel for this row :>    —PP
				for (int x = initialWidth - 1; x >= 0; x--)
				{
					if (img.getRGB(x, y) != transparent && x > xInclusiveHighBound)  //if (this pixel is nonempty && it's higher than all for other previous rows), then make this the new global highest-nonempty-X-value! :D    —PP
					{
						xInclusiveHighBound = x;
						break;  //No point in keeping going XD stop the loop early! :D    —PP
					}
				}
			}
		}
		
		
		
		
		
		
		if (!entireImageEmpty)  //The *big* first loop tells the *big* second loop if there's no point in it running 8>    —PP
		{
			// Find Y bounds :>
			int yInclusiveLowBound = initialHeight;  //Start it off at the worst-case, then if we find anything, it'll always be better (croppier XD) or the same as this :D    —PP
			int yInclusiveHighBound = 0;  //Start it off at the worst-case, then if we find anything, it'll always be better (croppier XD) or the same as this :D    —PP
			
			for (int x = 0; x < initialWidth; x++)  //Scan each column (order doesn't matter)  :3    —PP
			{
				boolean allEmpty = true;
				
				//Scan Y's low to high to find the first non-empty pixel for this column :>    —PP
				for (int y = 0; y < initialHeight; y++)
				{
					if (img.getRGB(x, y) != transparent && y < yInclusiveLowBound)  //if (this pixel is nonempty && it's lower than for all other previous columns), then make this the new global lowest-nonempty-Y-value! :D    —PP
					{
						yInclusiveLowBound = y;
						allEmpty = false;
						break;  //No point in keeping going XD stop the loop early! :D    —PP
					}
				}
				
				if (!allEmpty)  //Let the first loop tell the second loop if there's no point in it running XD    —PP
				{
					//Scan Y's high to low to find the last non-empty pixel for this column :>    —PP
					for (int y = initialHeight - 1; y >= 0; y--)
					{
						if (img.getRGB(x, y) != transparent && y > yInclusiveHighBound)  //if (this pixel is nonempty && it's higher than all for other previous columns), then make this the new global highest-nonempty-Y-value! :D    —PP
						{
							yInclusiveHighBound = y;
							break;  //No point in keeping going XD stop the loop early! :D    —PP
						}
					}
				}
			}
			
			
			
			assert xInclusiveLowBound <= xInclusiveHighBound;
			assert yInclusiveLowBound <= yInclusiveHighBound;
			
			
			// For anything; string/array/list indexes/sizes, x's/widths, y's/heights, matrix elements—anything :D
			// 
			// 	!	ExclusiveHighBound = InclusiveHighBound + 1
			// 	!	Interval Size = ExclusiveHighBound - InclusiveLowBound
			// 		Interval Size = (InclusiveHighBound + 1) - InclusiveLowBound
			// 		Interval Size = InclusiveHighBound + 1 - InclusiveLowBound
			// 	!	Interval Size = InclusiveHighBound - InclusiveLowBound + 1
			// 			—PP ^w^
			
			final int finalWidth = xInclusiveHighBound - xInclusiveLowBound + 1;
			final int finalHeight = yInclusiveHighBound - yInclusiveLowBound + 1;
			
			return new Rectangle(xInclusiveLowBound, yInclusiveLowBound, finalWidth, finalHeight);
		}
		else
		{
			//Entire image is empty XD
			return new Rectangle(0, 0, 0, 0);
		}
	}    
	
	
	
	
	
	
	
	
	
	
	
	
	public Rectangle getImageContentBounds_Original(BufferedImage img) {
		final int initialWidth  = img.getWidth();
		final int initialHeight = img.getHeight();
		
		//int transparent = getARGB32(0, 0, 0, 0);
		final int transparent = 0;  //this works for any arrangement of channels XD
		
		//TRIM X
		int xInclusiveLowBound = initialWidth;
		int xInclusiveHighBound = 0;
		for(int y = 0; y < initialHeight; y++) {
			for(int x = initialWidth - 1; x >= 0; x--) {
				if(img.getRGB(x, y) != transparent &&
				x < xInclusiveLowBound) {
					xInclusiveLowBound = x;
				}
				if(img.getRGB(x, y) != transparent &&
				x > xInclusiveHighBound) {
					xInclusiveHighBound = x;
					break;
				}
			}
		}
		
		//TRIM Y
		int yInclusiveLowBound = initialHeight;
		int yInclusiveHighBound = 0;
		for(int x = 0; x < initialWidth; x++) {
			for(int y = initialHeight - 1; y >= 0; y--) {
				if(img.getRGB(x, y) != transparent &&
				y < yInclusiveLowBound) {
					yInclusiveLowBound = y;
				}
				if(img.getRGB(x, y) != transparent &&
				y > yInclusiveHighBound) {
					yInclusiveHighBound = y;
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
		
		return new Rectangle(xInclusiveLowBound, yInclusiveLowBound, finalWidth, finalHeight);
	}
	
	
	
	
	
	public int getARGB32(int alpha, int red, int green, int blue)
	{
		return (alpha & 0xff) << 24 | (red & 0xff) << 16 | (green & 0xff) << 8 | (blue & 0xff);
	}
}

