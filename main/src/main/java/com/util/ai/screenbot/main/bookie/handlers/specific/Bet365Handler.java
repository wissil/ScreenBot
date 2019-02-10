package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.main.bookie.handlers.AbstractBookieHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;

public class Bet365Handler extends AbstractBookieHandler {

	public Bet365Handler(InputHandler in) {
		super(in);
	}

	@Override
	public void placeBet(double stake) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeBet() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBetCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BufferedImage getBookmakerOddsImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getMinStakeImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getMaxStakeImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getBalanceStakeImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkBettingSlip() throws BetSlipException, BetNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void initialize(Rectangle browserDimensions) {
		// TODO Auto-generated method stub

	}
}
