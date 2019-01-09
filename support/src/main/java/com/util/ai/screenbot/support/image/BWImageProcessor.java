package com.util.ai.screenbot.support.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.util.ImageHelper;


public class BWImageProcessor implements ImageProcessor {
	
	private static final int SCALE = 5;
	
	private static final int WHITE_TRESHOLD_NEG = 120;
	private static final int BLACK_TRESHOLD_NEG = 60;
	
	private static final int WHITE_TRESHOLD_POS = 210;
	private static final int BLACK_TRESHOLD_POS = 60;
	
	private static final Color WHITE = new Color(255, 255, 255);
	private static final Color BLACK = new Color(0, 0, 0);
	
	@Override
	public BufferedImage process(BufferedImage image, boolean negative) {
		final int _width = image.getWidth() * SCALE;
		final int _height = image.getHeight() * SCALE;
		
		image = ImageHelper.convertImageToGrayscale(image);
		if (negative) image = ImageHelper.invertImageColor(image);

		image = negative ? 
				enhancePixels(image, WHITE_TRESHOLD_NEG) : 
				enhancePixels(image, WHITE_TRESHOLD_POS);
				
		image = ImageHelper.getScaledInstance(image, _width, _height);

		return image;
	}
	
	private static BufferedImage enhancePixels(BufferedImage image, int whiteTreshold) {
		for (int x = 0, width = image.getWidth(); x < width; x++) {
			for (int y = 0, height = image.getHeight(); y < height; y++) {
				final Color pixelColor = new Color(image.getRGB(x, y), true);
				
				processPixelOnPositive(image, x, y, pixelColor, whiteTreshold);
			}
		}
		
		return image;
	}

	@SuppressWarnings("unused")
	private static void processPixelOnNegative(BufferedImage image, int x, int y, Color pixelColor, int whiteTreshold) {
		if (isWhite(pixelColor, whiteTreshold)) {
			image.setRGB(x, y, BLACK.getRGB());
		} else {
			image.setRGB(x, y, WHITE.getRGB());
		}
	}
	
	private static void processPixelOnPositive(BufferedImage image, int x, int y, Color pixelColor, int whiteTreshold) {
		if (isWhite(pixelColor, whiteTreshold)) {
			image.setRGB(x, y, WHITE.getRGB());
		} else {
			image.setRGB(x, y, BLACK.getRGB());
		}
	}

	private static boolean isWhite(Color color, int whiteTreshold) {
		return 
				color.getGreen() >= whiteTreshold || 
				color.getBlue() >= whiteTreshold || 
				color.getRed() >= whiteTreshold;
	}
	
	@SuppressWarnings("unused")
	private static boolean isBlack(Color color, int blackTreshold) {
		return 
				color.getGreen() <= blackTreshold && 
				color.getBlue() <= blackTreshold && 
				color.getRed() <= blackTreshold;
	}
}
