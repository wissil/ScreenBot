package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.main.bookie.handlers.AbstractBookieHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;

public class WilliamHillHandler extends AbstractBookieHandler {

	public WilliamHillHandler(InputHandler in) {
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
	public void checkBettingSlip() throws InvalidBetSlipException {
		// TODO Auto-generated method stub

	}

}
