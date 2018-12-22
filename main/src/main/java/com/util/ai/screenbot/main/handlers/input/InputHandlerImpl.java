package com.util.ai.screenbot.main.handlers.input;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.logic.VBInputBot;

public class InputHandlerImpl implements InputHandler {
	
	private final VBInputBot inputBot;
	
	public InputHandlerImpl(VBInputBot inputBot) {
		this.inputBot = Objects.requireNonNull(inputBot);
	}

	@Override
	public boolean isNewBetPresent() {
		return inputBot.checkTopBet();
	}

	@Override
	public BufferedImage getSingleBetImage() {
		return inputBot.takeTopBetScreenshot();
	}

	@Override
	public BufferedImage getPlaceBetImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BufferedImage getOddsInputImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
