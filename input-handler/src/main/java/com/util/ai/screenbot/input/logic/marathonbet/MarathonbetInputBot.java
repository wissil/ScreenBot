package com.util.ai.screenbot.input.logic.marathonbet;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.marathonbet.AbstractMarathonbetConstants;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.GuiElementNotFoundException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
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

	private static void clickBettingSlip() throws InvalidBetSlipException {
		log.debug("Clicking on bet slip header element ...");

		try {
			SikuliUtils.clickOnElement("marathon/Marathon_BetSlip_Loaded.png");
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Bet slip header element is missing.", e);
		}
	}

	public void checkBettingSlip() throws InvalidBetSlipException {
		log.debug("Waiting for the bet slip to load ...");

		try {
			SikuliUtils.waitForElement("marathon/Marathon_BetSlip_Loaded.png", 5);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Bet slip header element is missing.", e);
		}

		clickBettingSlip();
		log.debug("Bet slip successfully loaded!");
	}

	public void clickRemoveAll() throws InvalidBetSlipException {
		log.debug("Clicking remove all button ...");

		try {
			SikuliUtils.clickOnElement("marathon/Marathon_RemoveAll.png");
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Remove all element is missing.", e);
		}
	}

	public void clickBet() throws InvalidBetSlipException {
		log.debug("Clicking the bet button ...");

		try {
			SikuliUtils.clickOnElement("marathon/Marathon_PlaceBet.png");
			SikuliUtils.clickOnElement("marathon/Marathon_OK.png");
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Place bet element is missing.", e);
		}
	}

	public void setBetStake(String stake) throws InvalidBetSlipException {
		log.debug("Setting the bet stake ...");

		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_ActiveCursor.png", String.valueOf(stake)))
			return;
		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_InactiveCursor.png", String.valueOf(stake)))
			return;
		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_Text.png", String.valueOf(stake)))
			return;
		throw new InvalidBetSlipException("Could not find input stake element.");
	}

	public BufferedImage takeBookmakerOddsScreenshot() throws InvalidBetSlipException {
		log.debug("Taking bookmaker odds screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement("marathon/Marathon_OddsInput.png", 40);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Odds input element is missing.", e);
		}
	}

	public BufferedImage takeBalanceScreenshot() throws FatalVBException {
		log.debug("Taking bookmaker balance screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement("marathon/Marathon_Balance.png", 70);
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Balance could not be read.");
		}
	}

	public BufferedImage takeMaxStakeScreenshot() throws InvalidBetSlipException {
		log.debug("Taking max stake screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement("marathon/Marathon_MaxStake", 48);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Max stake element is missing.", e);
		}
	}

	public BufferedImage takeMinStakeScreenshot() throws InvalidBetSlipException {
		log.debug("Taking min stake screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement("marathon/Marathon_MinStake", 48);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Min stake element is missing.", e);
		}
	}
}