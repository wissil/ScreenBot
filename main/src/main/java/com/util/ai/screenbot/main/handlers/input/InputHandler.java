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

	BufferedImage getSingleBetImage() throws FatalVBException;

	BufferedImage getBookmakerOddsImage(Bookie bookie) throws FatalVBException;

	BufferedImage getOddsInputImage();

	BufferedImage getMinStakeImage(Bookie bookie) throws FatalVBException;

	BufferedImage getMaxStakeImage(Bookie bookie) throws FatalVBException;

	BufferedImage getBalanceStakeImage(Bookie bookie);

	void removeTopBet();

	void removeAllBetsFromTopBetEvent() throws FatalVBException;

	void clickOKAtBettingBrowser() throws FatalVBException;

	void clickCancelAtBettingBrowser() throws FatalVBException;

	void placeBet(Bookie bookie, double stake) throws BetException, FatalVBException;

	void removeBet(Bookie bookie) throws FatalVBException;

	boolean isBetPlaceable(Bookie bookie, double stake, double balance, double max, double min) throws FatalVBException;

	void checkBettingSlip(Bookie bookie) throws BetSlipException, BetNotFoundException, FatalVBException;

	void waitForBettingBrowserToLoad() throws FatalVBException;
}
