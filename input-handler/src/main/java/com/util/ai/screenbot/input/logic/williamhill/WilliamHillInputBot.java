package com.util.ai.screenbot.input.logic.williamhill;

import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.williamhill.WilliamHillGuiConstants;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.GuiElementNotFoundException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.AbstractInputBot;
import com.util.ai.screenbot.input.utils.SikuliUtils;

public class WilliamHillInputBot extends AbstractInputBot {

	protected static final Logger log = LoggerFactory.getLogger(WilliamHillInputBot.class);

	public WilliamHillInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler,
			MouseHandler mouseHandler) {
		super(keyboardHandler, screenHandler, mouseHandler);

	}

	public void checkBettingSlip() throws InvalidBetSlipException {
		log.debug("Waiting for the bet slip to load ...");

		if (!SikuliUtils.waitForElement(WilliamHillGuiConstants.WILLIAM_HILL_BET_SLIP_LOADED, 5)) {
			throw new InvalidBetSlipException("Bet slip header element is missing.");
		}

		log.debug("Bet slip successfully loaded!");
	}

	public void clickRemoveAll() throws InvalidBetSlipException {
		log.debug("Clicking remove all button ...");

		try {
			SikuliUtils.clickOnElement(WilliamHillGuiConstants.WILLIAM_HILL_REMOVE_ALL);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Remove all element is missing.", e);
		}
	}

	public void clickBet() throws InvalidBetSlipException {
		log.debug("Clicking the bet button ...");

		try {
			SikuliUtils.clickOnElement(WilliamHillGuiConstants.WILLIAM_HILL_PLACE_BET);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Place bet element is missing.", e);
		}
	}

	public void setBetStake(String stake) throws InvalidBetSlipException {
		log.debug("Setting the bet stake ...");

		if (SikuliUtils.writeToElement(WilliamHillGuiConstants.WILLIAM_HILL_INPUT_STAKE_TEXT, String.valueOf(stake)))
			return;
		throw new InvalidBetSlipException("Could not find input stake element.");
	}

	public void neutralClick() throws InvalidBetSlipException {
		try {
			SikuliUtils.clickOnElement((WilliamHillGuiConstants.WILLIAM_HILL_INPUT_STAKE_TEXT));
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Bet slip header element is missing.");
		}
	}

	public BufferedImage takeBalanceScreenshot() throws FatalVBException {
		log.debug("Taking bookmaker balance screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement(WilliamHillGuiConstants.WILLIAM_HILL_BALANCE, 70);
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Balance could not be read.");
		}
	}

	public BufferedImage takeBookmakerOddsScreenshot() throws InvalidBetSlipException {
		log.debug("Taking bookmaker odds screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement(WilliamHillGuiConstants.WILLIAM_HILL_ODDS, 30);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Odds input element is missing.", e);
		}
	}
}
