package com.util.ai.screenbot.input.logic.value.betting;

import static com.util.ai.screenbot.input.constants.VBGuiConstants.BETTING_BROWSER_BUSY;
import static com.util.ai.screenbot.input.constants.VBGuiConstants.BETTING_BROWSER_DONE;
import static com.util.ai.screenbot.input.constants.VBGuiConstants.BET_INFO;
import static com.util.ai.screenbot.input.constants.VBGuiConstants.CANCEL;
import static com.util.ai.screenbot.input.constants.VBGuiConstants.CONFIRM_PLACED_BET;
import static com.util.ai.screenbot.input.constants.VBGuiConstants.CONFIRM_PLACED_BET_OK;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.sikuli.script.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.constants.value.betting.AbstractVBConstants;
import com.util.ai.screenbot.input.exceptions.BettingBrowserTimeoutException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.GuiElementNotFoundException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.utils.SikuliUtils;

public class VBBrowserInputBot extends VBInputBot {

	protected static final Logger log = LoggerFactory.getLogger(VBBrowserInputBot.class);

	private Rectangle browserDimensions;

	public VBBrowserInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractVBConstants vbConstants) {
		super(keyboardHandler, screenHandler, mouseHandler, vbConstants);
	}

	public void initializeBettingBrowser() {
		App.focus("Betting Browser");

		// Initialize Betting Browser screen dimensions
		this.browserDimensions = checkScreen();
	}

	public BufferedImage takeBetInfoScreenshot() throws FatalVBException {
		log.debug("Taking bet info screenshot ...");

		try {
			return SikuliUtils.getImageRigtToElement(BET_INFO, 50);
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Bet Info region not found.", e);
		}
	}

	public void clickCancel() throws FatalVBException {
		log.debug("Clicking cancel button ...");

		try {
			SikuliUtils.clickOnElement(CANCEL);
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Cancel button not found.", e);
		}
	}

	public void clickConfirm() throws FatalVBException {
		log.debug("Clicking confirm placed bet ...");

		try {
			SikuliUtils.clickOnElement(CONFIRM_PLACED_BET);
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Confirm placed bet button not found.", e);
		}
	}

	public void clickConfirmOk() throws FatalVBException {
		log.debug("Clicking confirm OK ...");

		try {
			SikuliUtils.clickOnElement(CONFIRM_PLACED_BET_OK);
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Confirm OK button not found.", e);
		}
	}

	public Rectangle getBrowserDimensions() {
		return this.browserDimensions;
	}

	public void waitForBettingBrowser() throws BettingBrowserTimeoutException {
		log.debug("Waiting for the betting browser to load ...");

		while (!SikuliUtils.waitForElement(BETTING_BROWSER_DONE, 30)
				&& SikuliUtils.waitForElementToVanish(BETTING_BROWSER_BUSY, 2));

		// wait for max 40 seconds for the second appearance
		if (!SikuliUtils.waitForElement(BETTING_BROWSER_DONE, 20)) {
			throw new BettingBrowserTimeoutException("Betting browser done element not found.");
		}
	}
}
