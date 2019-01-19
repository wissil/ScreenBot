package com.util.ai.screenbot.main.bookie.handlers;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;

public interface BookieHandler {

	void placeBet(double stake);

	void removeBet() throws FatalValueBettingException;

	boolean isBetCorrect();

	BufferedImage getBookmaerOddsImage();

	void neutralClick();

	BufferedImage getMinStakeImage();

	BufferedImage getMaxStakeImage();

	BufferedImage getBalanceStakeImage();
}
