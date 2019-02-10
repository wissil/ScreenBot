package com.util.ai.screenbot.main.bookie.handlers;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BetException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;

public interface BookieHandler {

	void initialize(Rectangle browserDimensions);

	void placeBet(double stake) throws BetException, FatalVBException;

	void removeBet() throws FatalVBException;

	boolean isBetCorrect() throws FatalVBException;

	BufferedImage getBookmakerOddsImage() throws FatalVBException;

	BufferedImage getMinStakeImage() throws FatalVBException;

	BufferedImage getMaxStakeImage() throws FatalVBException;

	BufferedImage getBalanceStakeImage();

	void checkBettingSlip() throws BetSlipException, BetNotFoundException, FatalVBException;
}
