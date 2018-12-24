package com.util.ai.screenbot.support.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BWImageProcessor implements ImageProcessor {
	
	private static final int WHITE_TRESHOLD = 220;
	private static final int BLACK_TRESHOLD = 110;
	
	private static final Color WHITE = new Color(255, 255, 255);
	private static final Color BLACK = new Color(0, 0, 0);

	@Override
	public BufferedImage process(BufferedImage image, boolean negative) {
		for (int x = 0, width = image.getWidth(); x < width; x++) {
			for (int y = 0, height = image.getHeight(); y < height; y++) {
				final Color pixelColor = new Color(image.getRGB(x, y), true);
				
				if (negative) {
					processPixelOnNegative(image, x, y, pixelColor);
				} else {
					processPixelOnPositive(image, x, y, pixelColor);
				}
			}
		}
		
		return image;
	}
	
	private static void processPixelOnNegative(BufferedImage image, int x, int y, Color pixelColor) {
		if (isWhite(pixelColor)) {
			image.setRGB(x, y, BLACK.getRGB());
		} else {
			image.setRGB(x, y, WHITE.getRGB());
		}
	}
	
	private static void processPixelOnPositive(BufferedImage image, int x, int y, Color pixelColor) {
		if (!isBlack(pixelColor)) {
			image.setRGB(x, y, WHITE.getRGB());
		} else {
			image.setRGB(x, y, BLACK.getRGB());
		}
	}

	private static boolean isWhite(Color color) {
		return 
				color.getGreen() >= WHITE_TRESHOLD || 
				color.getBlue() >= WHITE_TRESHOLD || 
				color.getRed() >= WHITE_TRESHOLD;
	}
	
	private static boolean isBlack(Color color) {
		return 
				color.getGreen() <= BLACK_TRESHOLD || 
				color.getBlue() <= BLACK_TRESHOLD || 
				color.getRed() <= BLACK_TRESHOLD;
	}
}
