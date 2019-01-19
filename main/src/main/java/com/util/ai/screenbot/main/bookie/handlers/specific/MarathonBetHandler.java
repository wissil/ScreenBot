package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.exceptions.NoBetFoundException;
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
	public boolean isBetCorrect() {

		try {
			marathonBot.checkBettingSlip();
		} catch (BetSlipException | NoBetFoundException e) {
			return false;
		}

		return true;
	}

	@Override
	public BufferedImage getBookmaerOddsImage() {
		return marathonBot.takeBookmakerOddsScreenshot();
	}

	@Override
	public void neutralClick() {
		marathonBot.neutralClick();
	}

	@Override
	public BufferedImage getMinStakeImage() {
		return marathonBot.takeMinStakeScreenshot();
	}

	@Override
	public BufferedImage getMaxStakeImage() {
		return marathonBot.takeMaxStakeScreenshot();
	}

	@Override
	public BufferedImage getBalanceStakeImage() {
		return marathonBot.takeBalanceScreenshot();
	}

	@Override
	public void checkBettingSlip() throws BetSlipException, NoBetFoundException {
		marathonBot.checkBettingSlip();
	}
}
