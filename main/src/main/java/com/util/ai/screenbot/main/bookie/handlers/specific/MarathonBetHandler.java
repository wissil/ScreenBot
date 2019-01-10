package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.image.BufferedImage;

import com.util.ai.screenbot.main.bookie.handlers.AbstractBookieHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;

public class MarathonBetHandler extends AbstractBookieHandler {

	public MarathonBetHandler(InputHandler in) {
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
	public boolean isBetCorrect(double stake) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BufferedImage getPlaceBetImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
