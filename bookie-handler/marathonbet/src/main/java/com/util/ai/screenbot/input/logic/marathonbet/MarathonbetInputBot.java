package com.util.ai.screenbot.input.logic.marathonbet;

import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_BALANCE;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_BET_SLIP_LOADED;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_INPUT_STAKE_ACTIVE_CURSOR;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_INPUT_STAKE_INACTIVE_CURSOR;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_INPUT_STAKE_TEXT;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_MAX_STAKE;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_MIN_STAKE;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_ODDS_INPUT;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_OK;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_PLACE_BET;
import static com.util.ai.screenbot.input.constants.marathonbet.MarathonbetGuiConstants.MARATHON_REMOVE_ALL;

import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.GuiElementNotFoundException;
import com.util.ai.screenbot.input.exceptions.InvalidBetSlipException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.AbstractInputBot;
import com.util.ai.screenbot.input.utils.SikuliUtils;


public class MarathonbetInputBot extends AbstractInputBot {

	private static final Logger log = LoggerFactory.getLogger(MarathonbetInputBot.class);

	public MarathonbetInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler) {
		super(keyboardHandler, screenHandler, mouseHandler);
	}

	private static void clickBettingSlip() throws InvalidBetSlipException {
		log.debug("Clicking on bet slip header element ...");

		try {
			SikuliUtils.clickOnElement(MARATHON_BET_SLIP_LOADED);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Bet slip header element is missing.", e);
		}
	}

	public void checkBettingSlip() throws InvalidBetSlipException {
		log.debug("Waiting for the bet slip to load ...");
		
		if (!SikuliUtils.waitForElement(MARATHON_BET_SLIP_LOADED, 5)) {
			throw new InvalidBetSlipException("Bet slip header element is missing.");
		}

		clickBettingSlip();
		log.debug("Bet slip successfully loaded!");
	}

	public void clickRemoveAll() throws InvalidBetSlipException {
		log.debug("Clicking remove all button ...");

		try {
			SikuliUtils.clickOnElement(MARATHON_REMOVE_ALL);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Remove all element is missing.", e);
		}
	}

	public void clickBet() throws InvalidBetSlipException {
		log.debug("Clicking the bet button ...");

		try {
			SikuliUtils.clickOnElement(MARATHON_PLACE_BET);
			SikuliUtils.clickOnElement(MARATHON_OK);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Place bet element is missing.", e);
		}
	}

	public void setBetStake(String stake) throws InvalidBetSlipException {
		log.debug("Setting the bet stake ...");

		if (SikuliUtils.writeToElement(MARATHON_INPUT_STAKE_ACTIVE_CURSOR, String.valueOf(stake)))
			return;
		if (SikuliUtils.writeToElement(MARATHON_INPUT_STAKE_INACTIVE_CURSOR, String.valueOf(stake)))
			return;
		if (SikuliUtils.writeToElement(MARATHON_INPUT_STAKE_TEXT, String.valueOf(stake)))
			return;
		throw new InvalidBetSlipException("Could not find input stake element.");
	}

	public BufferedImage takeBookmakerOddsScreenshot() throws InvalidBetSlipException {
		log.debug("Taking bookmaker odds screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement(MARATHON_ODDS_INPUT, 40);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Odds input element is missing.", e);
		}
	}

	public BufferedImage takeBalanceScreenshot() throws FatalVBException {
		log.debug("Taking bookmaker balance screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement(MARATHON_BALANCE, 70);
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Balance could not be read.");
		}
	}

	public BufferedImage takeMaxStakeScreenshot() throws InvalidBetSlipException {
		log.debug("Taking max stake screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement(MARATHON_MAX_STAKE, 48);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Max stake element is missing.", e);
		}
	}

	public BufferedImage takeMinStakeScreenshot() throws InvalidBetSlipException {
		log.debug("Taking min stake screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement(MARATHON_MIN_STAKE, 48);
		} catch (GuiElementNotFoundException e) {
			throw new InvalidBetSlipException("Min stake element is missing.", e);
		}
	}
}