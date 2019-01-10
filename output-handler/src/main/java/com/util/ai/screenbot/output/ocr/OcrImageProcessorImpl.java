package com.util.ai.screenbot.output.ocr;

import java.awt.Color;
import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.util.ImageHelper;

public class OcrImageProcessorImpl implements OcrImageProcessor {
	
	private static final Color WHITE = new Color(255, 255, 255);
	private static final Color BLACK = new Color(0, 0, 0);

	@Override
	public BufferedImage process(BufferedImage image, OcrImageProcessingConf conf) {
		final int SCALE = conf.SCALE();
		final int WHITE_TRESH = conf.WHITE_TRESHOLD();
		final boolean negative = conf.isNegative();
		
		final int _width = image.getWidth() * SCALE;
		final int _height = image.getHeight() * SCALE;
		
		image = ImageHelper.convertImageToGrayscale(image);
		if (negative) image = ImageHelper.invertImageColor(image);

		image =	enhancePixels(image, WHITE_TRESH);
				
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
}
