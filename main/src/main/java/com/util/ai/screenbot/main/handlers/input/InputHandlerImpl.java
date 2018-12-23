package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.logic.VBInputBot;
import com.util.ai.screenbot.main.bookie.Bookie;

public class InputHandlerImpl implements InputHandler {
	
	private final VBInputBot inputBot;
	
	public InputHandlerImpl(VBInputBot inputBot) {
		this.inputBot = Objects.requireNonNull(inputBot);
	}

	@Override
	public boolean isNewBetPresent() {
		return inputBot.checkTopBet();
	}

	@Override
	public BufferedImage getSingleBetImage() {
		return inputBot.takeTopBetScreenshot();
	}

	@Override
	public void openBettingBrowserWindow() {
		inputBot.initializeBettingBrowser();
	}

	@Override
	public void openMainWindow() {
		inputBot.initializeValueBetting();
	}

	@Override
	public void removeTopBet() {
		inputBot.removeTopBet();
	}

	@Override
	public void placeBet(Bookie bookie) {
		bookie.getHandler().placeBet();
	}

	@Override
	public void removeBet(Bookie bookie) {
		bookie.getHandler().removeBet();
	}
	
	@Override
	public BufferedImage getPlaceBetImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getOddsInputImage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void clickOKAtBettingBrowser() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void clickCancelAtBettingBrowser() {
		// TODO Auto-generated method stub	
	}

}
