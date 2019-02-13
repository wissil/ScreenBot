package com.util.ai.screenbot.input.logic.marathonbet;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.marathonbet.AbstractMarathonbetConstants;
import com.util.ai.screenbot.input.exceptions.BetException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.AbstractInputBot;
import com.util.ai.screenbot.input.utils.SikuliUtils;

public class MarathonbetInputBot extends AbstractInputBot {

	protected static final Logger log = LoggerFactory.getLogger(MarathonbetInputBot.class);

	protected AbstractMarathonbetConstants marathonbetConstants;

	public MarathonbetInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractMarathonbetConstants marathonbetConstants) {
		super(keyboardHandler, screenHandler, mouseHandler);
		this.marathonbetConstants = Objects.requireNonNull(marathonbetConstants);
	}

	public void clickBettingSlip() throws FatalVBException {
		SikuliUtils.clickOnElement("marathon/Marathon_BetSlip_Loaded.png");
	}

	public void checkBettingSlip() throws BetSlipException, BetNotFoundException, FatalVBException {
		log.debug("Waiting for bet slip loaded ...");
		SikuliUtils.waitForElement("marathon/Marathon_BetSlip_Loaded.png", 5);
		clickBettingSlip();
		log.info("Bet slip OK!");
	}

	public void clickRemoveAll() throws FatalVBException {
		SikuliUtils.clickOnElement("marathon/Marathon_RemoveAll.png");
	}

	public void clickBet() throws BetException, FatalVBException {
		SikuliUtils.clickOnElement("marathon/Marathon_PlaceBet.png");
		SikuliUtils.clickOnElement("marathon/Marathon_OK.png");
	}

	public void setBetStake(String stake) throws FatalVBException {
		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_ActiveCursor.png", String.valueOf(stake)))
			return;
		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_InactiveCursor.png", String.valueOf(stake)))
			return;
		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_Text.png", String.valueOf(stake)))
			return;
		throw new FatalVBException("Could not find input stake element.");
	}

	public BufferedImage takeBookmakerOddsScreenshot() throws FatalVBException {
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_OddsInput.png", 40);
	}

	public BufferedImage takeBalanceScreenshot() throws FatalVBException {
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_Balance.png", 70);
	}

	public BufferedImage takeMaxStakeScreenshot() throws FatalVBException {
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_MaxStake", 46);
	}

	public BufferedImage takeMinStakeScreenshot() throws FatalVBException {
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_MinStake", 46);
	}
}