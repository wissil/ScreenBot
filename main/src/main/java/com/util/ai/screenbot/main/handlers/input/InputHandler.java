package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BetException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.main.bookie.Bookie;

public interface InputHandler {

	boolean isNewBetPresent();

	void clickBetOnTopEvent() throws FatalValueBettingException;

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

	void clickOKAtBettingBrowser() throws FatalValueBettingException;

	void clickCancelAtBettingBrowser() throws FatalValueBettingException;

	void placeBet(Bookie bookie, double stake) throws BetException, FatalValueBettingException;

	void removeBet(Bookie bookie) throws FatalValueBettingException;

	boolean isBetPlaceable(Bookie bookie, double stake, double balance, double max, double min)
			throws FatalValueBettingException;

	void clickNeutralArea(Bookie bookie);

	void checkBettingSlip(Bookie bookie) throws BetSlipException, BetNotFoundException, FatalValueBettingException;
}
