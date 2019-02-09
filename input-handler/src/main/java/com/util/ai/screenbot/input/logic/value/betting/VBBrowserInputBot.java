package com.util.ai.screenbot.input.logic.value.betting;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.sikuli.script.FindFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.value.betting.AbstractVBConstants;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;

public class VBBrowserInputBot extends VBInputBot {

	protected static final Logger log = LoggerFactory.getLogger(VBBrowserInputBot.class);

	private Rectangle browserDimensions;

	public VBBrowserInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractVBConstants vbConstants) {
		super(keyboardHandler, screenHandler, mouseHandler, vbConstants);
	}

	public void initializeBettingBrowser() {
		initialize(AbstractVBConstants.VALUE_BETTING_BROWSER_PREFIX);

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

	public void clickCancel() throws FatalValueBettingException {
		try {
			SCREEN.wait("marathon/Cancel.png", 5);
			SCREEN.click("marathon/Cancel.png");
		}
		catch (FindFailed e) {
			throw new FatalValueBettingException("Not able to click cancel.", e);
		}
	}

	public void clickConfirm() throws FatalValueBettingException {
		try {
			SCREEN.wait("marathon/ConfirmPlacedBet.png", 5);
			SCREEN.click("marathon/ConfirmPlacedBet.png");
		}
		catch (FindFailed e) {
			throw new FatalValueBettingException("Not able to click confirm.", e);
		}
	}

	public void clickConfirmOk() throws FatalValueBettingException {
		try {
			SCREEN.wait("marathon/ConfirmPlacedBet_OK.png", 5);
			SCREEN.click("marathon/ConfirmPlacedBet_OK.png");
		}
		catch (FindFailed e) {
			throw new FatalValueBettingException("Not able to click confirm.", e);
		}
	}

	public void navigateToBrowsingStatus() {

		BotCoordinates browsingStatusCoordinates = getBrowsingStatusUpperLeftCornerCoordinates();

		mouseHandler.moveMouse(browsingStatusCoordinates.x, browsingStatusCoordinates.y);
	}

	public BufferedImage takeBrowsingStatusScreenshot() {
		BotCoordinates valueCoordinates = getBrowsingStatusUpperLeftCornerCoordinates();

		BufferedImage image = screenHandler.takeScreenshot(valueCoordinates.x, valueCoordinates.y,
				(int) Math.round(ScreenConfig.width * vbConstants.getBrowsingStatusScreenshotWidth()),
				(int) Math.round(ScreenConfig.height * vbConstants.getBrowsingStatusScreenshotHeight()));

		return image;
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

	private BotCoordinates getCancelButtonCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getCancelButtonWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * vbConstants.getBrowserButtonsHeight());

		return new BotCoordinates(betX, betY);
	}

	private BotCoordinates getBrowsingStatusUpperLeftCornerCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getBrowsingStatusWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * vbConstants.getBrowsingStatusHeight());

		return new BotCoordinates(betX, betY);
	}

	public Rectangle getBrowserDimensions() {
		return this.browserDimensions;
	}
}
