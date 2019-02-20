package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BettingBrowserTimeoutException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.main.bookie.Bookie;

public interface InputHandler {

	boolean isNewBetPresent();

	void clickBetOnTopEvent() throws FatalVBException;

	void openBettingBrowserWindow();

	void openMainWindow();

	BufferedImage getSingleBetImage() throws FatalVBException;

	BufferedImage getBookmakerOddsImage(Bookie bookie) throws InvalidBetSlipException;

	BufferedImage getOddsInputImage() throws FatalVBException;

	BufferedImage getMinStakeImage(Bookie bookie) throws InvalidBetSlipException;

	BufferedImage getMaxStakeImage(Bookie bookie) throws InvalidBetSlipException;

	BufferedImage getBalanceStakeImage(Bookie bookie) throws FatalVBException;

	void removeTopBet();

	void removeAllBetsFromTopBetEvent() throws FatalVBException;

	void clickOKAtBettingBrowser() throws FatalVBException;

	void clickCancelAtBettingBrowser() throws FatalVBException;

	void placeBet(Bookie bookie, double stake) throws InvalidBetSlipException;

	void removeBet(Bookie bookie) throws InvalidBetSlipException;

	void checkBettingSlip(Bookie bookie) throws InvalidBetSlipException, BetNotFoundException, FatalVBException;

	void waitForBettingBrowserToLoad() throws BettingBrowserTimeoutException;

	void logBet();
}
