package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BettingBrowserTimeoutException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.input.logic.value.betting.VBBrowserInputBot;
import com.util.ai.screenbot.input.logic.value.betting.VBMainInputBot;
import com.util.ai.screenbot.input.utils.DiskUtils;
import com.util.ai.screenbot.input.utils.SystemUtils;
import com.util.ai.screenbot.main.bookie.Bookie;

public class InputHandlerImpl implements InputHandler {

	private final VBMainInputBot mainBot;

	private final VBBrowserInputBot browserBot;

	public InputHandlerImpl(VBMainInputBot mainBot, VBBrowserInputBot browserBot) {
		this.mainBot = Objects.requireNonNull(mainBot);
		this.browserBot = Objects.requireNonNull(browserBot);

	}

	@Override
	public BufferedImage getSingleBetImage() throws FatalVBException {
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
	public void placeBet(Bookie bookie, double stake) throws InvalidBetSlipException {
		bookie.getHandler().placeBet(stake);
	}

	@Override
	public void removeBet(Bookie bookie) throws InvalidBetSlipException {
		bookie.getHandler().removeBet();
	}

	@Override
	public BufferedImage getBookmakerOddsImage(Bookie bookie) throws InvalidBetSlipException {
		BufferedImage bookmakerOdds = bookie.getHandler().getBookmakerOddsImage();
		DiskUtils.saveBookmakerOddsToDisk(bookmakerOdds);
		return bookmakerOdds;
	}

	@Override
	public BufferedImage getOddsInputImage() throws FatalVBException {
		BufferedImage betInfo = browserBot.takeBetInfoScreenshot();
		DiskUtils.saveBetInfoToDisk(betInfo);
		return betInfo;
	}

	@Override
	public void clickOKAtBettingBrowser() throws FatalVBException {
		browserBot.clickConfirm();
		browserBot.clickConfirmOk();
	}

	@Override
	public void clickCancelAtBettingBrowser() throws FatalVBException {
		browserBot.clickCancel();
	}

	@Override
	public BufferedImage getMinStakeImage(Bookie bookie) throws InvalidBetSlipException {
		BufferedImage minStake = bookie.getHandler().getMinStakeImage();
		DiskUtils.saveMinStakeToDisk(minStake);
		return minStake;
	}

	@Override
	public BufferedImage getMaxStakeImage(Bookie bookie) throws InvalidBetSlipException {
		BufferedImage maxStake = bookie.getHandler().getMaxStakeImage();
		DiskUtils.saveMaxStakeToDisk(maxStake);
		return maxStake;
	}

	@Override
	public BufferedImage getBalanceStakeImage(Bookie bookie) throws FatalVBException {
		BufferedImage balance = bookie.getHandler().getBalanceStakeImage();
		DiskUtils.saveBalanceToDisk(balance);
		return balance;
	}

	@Override
	public void checkBettingSlip(Bookie bookie) throws InvalidBetSlipException, BetNotFoundException, FatalVBException {
		bookie.getHandler().checkBettingSlip();

	}

	@Override
	public void removeAllBetsFromTopBetEvent() throws FatalVBException {
		mainBot.removeAllBetsFromTopBetEvent();

	}

	@Override
	public void clickBetOnTopEvent() throws FatalVBException {
		mainBot.betOnTopBet();

	}

	@Override
	public void waitForBettingBrowserToLoad() throws BettingBrowserTimeoutException {
		browserBot.waitForBettingBrowser();
	}

	@Override
	public void logBet() {
		DiskUtils.logBetToFile(SystemUtils.getClipboardContents());

	}

}
