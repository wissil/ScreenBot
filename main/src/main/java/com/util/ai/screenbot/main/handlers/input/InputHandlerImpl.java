package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.exceptions.NoBetFoundException;
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
		return bookie.getHandler().getBookmaerOddsImage();
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
	public boolean isBetPlaceable(Bookie bookie, double stake, double balance, double min, double max) {
		boolean stakeCorrect = (stake >= balance) && (stake >= min) && (stake <= max);
		return stakeCorrect && bookie.getHandler().isBetCorrect();
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

	@Override
	public void checkBettingSlip(Bookie bookie) throws BetSlipException, NoBetFoundException {
		bookie.getHandler().checkBettingSlip();

	}

	@Override
	public void removeAllBetsFromTopBetEvent() {
		mainBot.removeAllBetsFromTopBetEvent();

	}

	@Override
	public void clickBetOnTopEvent() {
		mainBot.betOnTopBet();

	}

	@Override
	public void intializeBookieBot(Bookie bookie) {
		bookie.getHandler().initialize(browserBot.getBrowserDimensions());

	}

}
