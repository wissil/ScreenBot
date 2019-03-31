package com.util.ai.screenbot.support.colors;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ColorDeterminator {

	/**
	 * Determines the average color of the given image.<br>
	 * Average color means the average of colors of all pixels in the given image.
	 * 
	 * @param image
	 * @return
	 */
	public static Color determine(BufferedImage image) {
		long sumR = 0L; // red
		long sumG = 0L; // green
		long sumB = 0L; // blue
		
		final int width = image.getWidth();
		final int height = image.getHeight();
		
	    for (int x=0; x<width; x++) {
	        for (int y=0; y<height; y++) {
	            final Color pixel = new Color(image.getRGB(x, y));
	            sumR += pixel.getRed();
	            sumG += pixel.getGreen();
	            sumB += pixel.getBlue();
	        }
	    }
	    
	    long nPixels = width * height;
	    
	    final int r = (int) (sumR / nPixels);
	    final int g = (int) (sumG / nPixels);
	    final int b = (int) (sumB / nPixels);
	    
	    return new Color(r, g, b);
	}
}
