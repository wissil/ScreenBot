package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.exceptions.NoBetFoundException;
import com.util.ai.screenbot.main.bookie.Bookie;

public interface InputHandler {

	boolean isNewBetPresent();

	void clickBetOnTopEvent();

	void openBettingBrowserWindow();

	void openMainWindow();

	void intializeBookieBot(Bookie bookie);

	BufferedImage getSingleBetImage();

	BufferedImage getBookmakerOddsImage(Bookie bookie);

	BufferedImage getOddsInputImage();

	BufferedImage getBrowsingStatusImage();

	BufferedImage getMinStakeImage(Bookie bookie);

	BufferedImage getMaxStakeImage(Bookie bookie);

	BufferedImage getBalanceStakeImage(Bookie bookie);

	void removeTopBet();

	void removeAllBetsFromTopBetEvent();

	void clickOKAtBettingBrowser();

	void clickCancelAtBettingBrowser();

	void placeBet(Bookie bookie, double stake);

	void removeBet(Bookie bookie) throws FatalValueBettingException;

	boolean isBetPlaceable(Bookie bookie, double stake, double balance, double min, double max);

	void clickNeutralArea(Bookie bookie);

	void checkBettingSlip(Bookie bookie) throws BetSlipException, NoBetFoundException;
}
