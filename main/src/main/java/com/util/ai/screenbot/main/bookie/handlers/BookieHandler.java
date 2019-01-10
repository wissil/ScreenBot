package com.util.ai.screenbot.main.bookie.handlers;

import java.awt.image.BufferedImage;

public interface BookieHandler {

	void placeBet(double stake);

	void removeBet();

	boolean isBetCorrect(double stake);
	
	BufferedImage getPlaceBetImage();
}
