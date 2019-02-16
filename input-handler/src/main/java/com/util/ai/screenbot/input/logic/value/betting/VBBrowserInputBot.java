package com.util.ai.screenbot.input.logic.value.betting;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.sikuli.script.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
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

	public void navigateToOddsUpperLeftCorner() {
		BotCoordinates oddsCoordinates = getOddsUpperLeftCoordinates();
		mouseHandler.moveMouse(oddsCoordinates.x, oddsCoordinates.y);
	}

	public BufferedImage takeBetInfoScreenshot() {
		BotCoordinates oddsCoordinates = getOddsUpperLeftCoordinates();

		BufferedImage image = screenHandler.takeScreenshot(oddsCoordinates.x, oddsCoordinates.y,
				(int) Math.round(ScreenConfig.width * vbConstants.getBetInfoScreenshotWidth()),
				(int) Math.round(ScreenConfig.height * vbConstants.getBetInfoScreenshotHeight()));

		return image;
	}

	public void navigateToStakeUpperLeftCorner() {
		BotCoordinates stakeCoordinates = getStakeUpperLeftCoordinates();
		mouseHandler.moveMouse(stakeCoordinates.x, stakeCoordinates.y);
	}

	public void navigateToValueUpperLeftCorner() {
		BotCoordinates valueCoordinates = getValueUpperLeftCoordinates();
		mouseHandler.moveMouse(valueCoordinates.x, valueCoordinates.y);
	}

	public void clickCancel() throws FatalVBException {
		log.debug("Clicking cancel button ...");

		try {
			SikuliUtils.clickOnElement("Cancel.png");
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Cancel button not found.", e);
		}
	}

	public void clickConfirm() throws FatalVBException {
		log.debug("Clicking confirm placed bet ...");

		try {
			SikuliUtils.clickOnElement("ConfirmPlacedBet.png");
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Confirm placed bet button not found.", e);
		}
	}

	public void clickConfirmOk() throws FatalVBException {
		log.debug("Clicking confirm OK ...");

		try {
			SikuliUtils.clickOnElement("ConfirmPlacedBet_OK.png");
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Confirm OK button not found.", e);
		}
	}

	private BotCoordinates getOddsUpperLeftCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

		Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getOddsInfoHeight());

		return new BotCoordinates(betX, betY);
	}

	private BotCoordinates getStakeUpperLeftCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

		Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getStakeInfoHeight());

		return new BotCoordinates(betX, betY);
	}

	private BotCoordinates getValueUpperLeftCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

		Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getValueInfoHeight());

		return new BotCoordinates(betX, betY);
	}

	public Rectangle getBrowserDimensions() {
		return this.browserDimensions;
	}

	public void waitForBettingBrowser() throws BettingBrowserTimeoutException {
		log.debug("Waiting for the betting browser to load ...");

		try {
			final long start = System.currentTimeMillis();
			SikuliUtils.waitForElement("BettingBrowser_Done", 35);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SikuliUtils.waitForElement("BettingBrowser_Done", 30);
			final long end = System.currentTimeMillis();
			final long duration = end - start;

			log.debug(String.format("Betting browser waited for %d ms!", duration));
		} catch (GuiElementNotFoundException e) {
			try {
				final long start = System.currentTimeMillis();
				SikuliUtils.waitForElement("BettingBrowser_Done", 35);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e2) {

				}
				SikuliUtils.waitForElement("BettingBrowser_Done", 30);
				final long end = System.currentTimeMillis();
				final long duration = end - start;

				log.debug(String.format("Betting browser waited for %d ms!", duration));
			} catch (GuiElementNotFoundException e1) {
				final long start = System.currentTimeMillis();
				try {
					SikuliUtils.waitForElement("BettingBrowser_Done", 35);
				} catch (GuiElementNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e2) {

				}
				try {
					SikuliUtils.waitForElement("BettingBrowser_Done", 30);
				} catch (GuiElementNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					throw new BettingBrowserTimeoutException("Betting browser done element not found.", e);
				}
				final long end = System.currentTimeMillis();
				final long duration = end - start;

				log.debug(String.format("Betting browser waited for %d ms!", duration));
			}
		}
	}
}
