package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BetException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.main.bookie.Bookie;

public interface InputHandler {

	boolean isNewBetPresent();

	void clickBetOnTopEvent() throws FatalVBException;

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

	void removeAllBetsFromTopBetEvent() throws FatalVBException;

	void clickOKAtBettingBrowser() throws FatalVBException;

	void clickCancelAtBettingBrowser() throws FatalVBException;

	void placeBet(Bookie bookie, double stake) throws BetException, FatalVBException;

	void removeBet(Bookie bookie) throws FatalVBException;

	boolean isBetPlaceable(Bookie bookie, double stake, double balance, double max, double min)
			throws FatalVBException;

	void clickNeutralArea(Bookie bookie);

	void checkBettingSlip(Bookie bookie) throws BetSlipException, BetNotFoundException, FatalVBException;
}
