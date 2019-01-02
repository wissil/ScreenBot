package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.main.bookie.Bookie;

public interface InputHandler {

	boolean isNewBetPresent();
	
	void openBettingBrowserWindow();
	
	void openMainWindow();
	
	BufferedImage getSingleBetImage();
	
	BufferedImage getPlaceBetImage();
	
	BufferedImage getOddsInputImage();
	
	void removeTopBet();
	
	void clickOKAtBettingBrowser();
	
	void clickCancelAtBettingBrowser();
	
	void placeBet(Bookie bookie, double stake);
	
	void removeBet(Bookie bookie);
}
