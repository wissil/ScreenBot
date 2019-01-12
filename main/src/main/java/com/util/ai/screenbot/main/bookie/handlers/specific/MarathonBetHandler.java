package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.logic.marathonbet.MarathonbetInputBot;
import com.util.ai.screenbot.main.bookie.handlers.AbstractBookieHandler;
import com.util.ai.screenbot.main.handlers.input.InputHandler;

public class MarathonBetHandler extends AbstractBookieHandler {

	private MarathonbetInputBot marathonBot;

	public MarathonBetHandler(InputHandler in, MarathonbetInputBot marathonBot) {

		super(in);
		this.marathonBot = Objects.requireNonNull(marathonBot);
	}

	@Override
	public void placeBet(double stake) {
		marathonBot.setBetStake(String.valueOf(stake));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// Do nothing
		}
		marathonBot.clickBet();
	}

	@Override
	public void removeBet() throws FatalValueBettingException {
		marathonBot.clickRemoveAll();

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
