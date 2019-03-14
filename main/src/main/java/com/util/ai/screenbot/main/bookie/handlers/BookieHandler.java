package com.util.ai.screenbot.main.bookie.handlers;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BettingBrowserTimeoutException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;

public interface BookieHandler {

	void placeBet(double stake) throws InvalidBetSlipException;

	void removeBet() throws InvalidBetSlipException;

	BufferedImage getBookmakerOddsImage() throws InvalidBetSlipException;

	BufferedImage getMinStakeImage() throws InvalidBetSlipException;

	BufferedImage getMaxStakeImage() throws InvalidBetSlipException;

	BufferedImage getBalanceStakeImage() throws FatalVBException;

	void checkBettingSlip() throws InvalidBetSlipException;

	void waitForBettingBrowserToLoad() throws BettingBrowserTimeoutException;

}
