package com.util.ai.screenbot.main.bookie.handlers;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BetException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;

public interface BookieHandler {

	void initialize(Rectangle browserDimensions);

	void placeBet(double stake) throws BetException;

	void removeBet() throws FatalValueBettingException;

	boolean isBetCorrect();

	BufferedImage getBookmakerOddsImage();

	void neutralClick();

	BufferedImage getMinStakeImage();

	BufferedImage getMaxStakeImage();

	BufferedImage getBalanceStakeImage();

	void checkBettingSlip() throws BetSlipException, BetNotFoundException;
}
