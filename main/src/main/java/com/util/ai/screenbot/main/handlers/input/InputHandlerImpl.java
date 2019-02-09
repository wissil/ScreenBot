package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.BetException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.logic.value.betting.VBBrowserInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBMainInputBot;
import com.util.ai.screenbot.input.utils.DiskUtils;
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
		BufferedImage singleBet = mainBot.takeTopBetScreenshot();
		DiskUtils.saveBetToDisk(singleBet);
		return singleBet;
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
	public void placeBet(Bookie bookie, double stake) throws BetException, FatalValueBettingException {
		bookie.getHandler().placeBet(stake);
	}

	@Override
	public void removeBet(Bookie bookie) throws FatalValueBettingException {
		bookie.getHandler().removeBet();
	}

	@Override
	public BufferedImage getBookmakerOddsImage(Bookie bookie) {
		BufferedImage bookmakerOdds = bookie.getHandler().getBookmakerOddsImage();
		DiskUtils.saveBookmakerOddsToDisk(bookmakerOdds);
		return bookmakerOdds;
	}

	@Override
	public BufferedImage getOddsInputImage() {
		BufferedImage betInfo = browserBot.takeBetInfoScreenshot();
		DiskUtils.saveBetInfoToDisk(betInfo);
		return betInfo;
	}

	@Override
	public BufferedImage getBrowsingStatusImage() {
		BufferedImage browsingStatus = browserBot.takeBrowsingStatusScreenshot();
		DiskUtils.saveBrowsingStatusToDisk(browsingStatus);
		return browsingStatus;
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
	public boolean isBetPlaceable(Bookie bookie, double stake, double balance, double max, double min) throws FatalValueBettingException {
		boolean stakeCorrect = (stake <= balance) && (stake >= min) && (stake <= max);
		return stakeCorrect && bookie.getHandler().isBetCorrect();
	}

	@Override
	public void clickNeutralArea(Bookie bookie) {
		bookie.getHandler().neutralClick();

	}

	@Override
	public BufferedImage getMinStakeImage(Bookie bookie) {

		BufferedImage minStake = bookie.getHandler().getMinStakeImage();
		DiskUtils.saveMinStakeToDisk(minStake);
		return minStake;
	}

	@Override
	public BufferedImage getMaxStakeImage(Bookie bookie) {
		BufferedImage maxStake = bookie.getHandler().getMaxStakeImage();
		DiskUtils.saveMaxStakeToDisk(maxStake);
		return maxStake;
	}

	@Override
	public BufferedImage getBalanceStakeImage(Bookie bookie) {
		BufferedImage balance = bookie.getHandler().getBalanceStakeImage();
		DiskUtils.saveBalanceToDisk(balance);
		return balance;
	}

	@Override
	public void checkBettingSlip(Bookie bookie) throws BetSlipException, BetNotFoundException, FatalValueBettingException {
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
