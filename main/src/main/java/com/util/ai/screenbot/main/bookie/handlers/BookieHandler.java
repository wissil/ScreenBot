package com.util.ai.screenbot.main.bookie.handlers;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.exceptions.NoBetFoundException;

public interface BookieHandler {

	void initialize(Rectangle browserDimensions);

	void placeBet(double stake);

	void removeBet() throws FatalValueBettingException;

	boolean isBetCorrect();

	BufferedImage getBookmaerOddsImage();

	void neutralClick();

	BufferedImage getMinStakeImage();

	BufferedImage getMaxStakeImage();

	BufferedImage getBalanceStakeImage();

	void checkBettingSlip() throws BetSlipException, NoBetFoundException;
}
