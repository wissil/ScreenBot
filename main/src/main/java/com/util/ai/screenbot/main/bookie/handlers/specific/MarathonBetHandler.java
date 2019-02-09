package com.util.ai.screenbot.main.bookie.handlers.specific;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Objects;

import com.util.ai.screenbot.input.exceptions.BetException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
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
	public void placeBet(double stake) throws BetException, FatalVBException {
		marathonBot.setBetStake(String.valueOf(stake));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// Do nothing
		}
		marathonBot.clickBet();
	}

	@Override
	public void removeBet() throws FatalVBException {
		marathonBot.clickRemoveAll();

	}

	@Override
	public boolean isBetCorrect() throws FatalVBException {

		try {
			marathonBot.checkBettingSlip();
		} catch (BetSlipException | BetNotFoundException e) {
			return false;
		}

		return true;
	}

	@Override
	public BufferedImage getBookmakerOddsImage() {
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
	public void checkBettingSlip() throws BetSlipException, BetNotFoundException, FatalVBException {
		marathonBot.checkBettingSlip();
	}

	@Override
	public void initialize(Rectangle browserDimensions) {
		marathonBot.initialize(browserDimensions);

	}
}
