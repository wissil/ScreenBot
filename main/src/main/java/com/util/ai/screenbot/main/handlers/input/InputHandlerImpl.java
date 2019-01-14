package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.logic.value.betting.VBBrowserInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBMainInputBot;
import com.util.ai.screenbot.main.bookie.Bookie;

public class InputHandlerImpl implements InputHandler {

	private final VBMainInputBot mainBot;

	private final VBBrowserInputBot browserBot;

	public InputHandlerImpl(VBMainInputBot mainBot, VBBrowserInputBot browserBot) {
		this.mainBot = Objects.requireNonNull(mainBot);
		this.browserBot = Objects.requireNonNull(browserBot);
	}

	@Override
	public boolean isNewBetPresent() {
		return mainBot.checkTopBet();
	}

	@Override
	public BufferedImage getSingleBetImage() {
		return mainBot.takeTopBetScreenshot();
	}

	@Override
	public void openBettingBrowserWindow() {
		browserBot.initializeBettingBrowser();
	}

	@Override
	public void openMainWindow() {
		mainBot.initializeValueBetting();
	}

	@Override
	public void removeTopBet() {
		mainBot.removeTopBet();
	}

	@Override
	public void placeBet(Bookie bookie, double stake) {
		bookie.getHandler().placeBet(stake);
	}

	@Override
	public void removeBet(Bookie bookie) throws FatalValueBettingException {
		bookie.getHandler().removeBet();
	}

	@Override
	public BufferedImage getBookmakerOddsImage(Bookie bookie) {
		// bookie specific
		//
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getOddsInputImage() {
		return browserBot.takeBetInfoScreenshot();
	}

	@Override
	public BufferedImage getBrowsingStatusImage() {
		return browserBot.takeBrowsingStatusScreenshot();
	}

	@Override
	public void clickOKAtBettingBrowser() {
		browserBot.clickConfirm();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// Do nothing
		}
		browserBot.clickConfirmOk();
	}

	@Override
	public void clickCancelAtBettingBrowser() {
		browserBot.clickCancel();
	}

	@Override
	public boolean isBetPlaceable(Bookie bookie, double stake) {
		return bookie.getHandler().isBetCorrect(stake);
	}

	@Override
	public void clickNeutralArea(Bookie bookie) {
		bookie.getHandler().neutralClick();

	}

	@Override
	public BufferedImage getMinStakeImage(Bookie bookie) {

		return bookie.getHandler().getMinStakeImage();
	}

	@Override
	public BufferedImage getMaxStakeImage(Bookie bookie) {
		return bookie.getHandler().getMaxStakeImage();
	}

	@Override
	public BufferedImage getBalanceStakeImage(Bookie bookie) {
		return bookie.getHandler().getBalanceStakeImage();
	}

}
