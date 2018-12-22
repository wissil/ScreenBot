package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;

public interface InputHandler {

	boolean isNewBetPresent();
	
	BufferedImage getSingleBetImage();
	
	BufferedImage getPlaceBetImage();
	
	BufferedImage getOddsInputImage();
}
