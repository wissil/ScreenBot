package com.util.ai.screenbot.input.logic;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.AbstractVBConstants;
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

		BetCoordinates oddsCoordinates = getOddsUpperLeftCoordinates();

		mouseHandler.moveMouse(oddsCoordinates.x, oddsCoordinates.y);
	}

	public BufferedImage takeOddsScreenshot() {
		BetCoordinates oddsCoordinates = getOddsUpperLeftCoordinates();

		return null;
	}

	public void navigateToStakeUpperLeftCorner() {

		BetCoordinates stakeCoordinates = getStakeUpperLeftCoordinates();

		mouseHandler.moveMouse(stakeCoordinates.x, stakeCoordinates.y);
	}

	public BufferedImage takeStakeScreenshot() {
		BetCoordinates stakeCoordinates = getStakeUpperLeftCoordinates();
		return null;
	}

	public void navigateToValueUpperLeftCorner() {

		BetCoordinates valueCoordinates = getValueUpperLeftCoordinates();

		mouseHandler.moveMouse(valueCoordinates.x, valueCoordinates.y);
	}

	public BufferedImage takeValueScreenshot() {
		BetCoordinates valueCoordinates = getValueUpperLeftCoordinates();
		return null;
	}

	public void navigateToCancelButton() {

		BetCoordinates cancelButtonCoordinates = getCancelButtonCoordinates();

		mouseHandler.moveMouse(cancelButtonCoordinates.x, cancelButtonCoordinates.y);
	}

	public void clickCancel() {
		navigateToCancelButton();

		mouseHandler.leftClick();
	}

	public void navigateToConfirmButton() {

		BetCoordinates confirmButtonCoordinates = getConfirmButtonCoordinates();

		mouseHandler.moveMouse(confirmButtonCoordinates.x, confirmButtonCoordinates.y);
	}

	public void clickConfirm() {
		navigateToConfirmButton();

		mouseHandler.leftClick();
	}

	public void navigateToConfirmOkButton() {

		BetCoordinates confirmOkButtonCoordinates = getConfirmOkButtonCoordinates();

		mouseHandler.moveMouse(confirmOkButtonCoordinates.x, confirmOkButtonCoordinates.y);
	}

	public void clickConfirmOk() {
		navigateToConfirmOkButton();

		mouseHandler.leftClick();
	}

	private BetCoordinates getOddsUpperLeftCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

		Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getOddsInfoHeight());

		return new BetCoordinates(betX, betY);
	}

	private BetCoordinates getStakeUpperLeftCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

		Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getStakeInfoHeight());

		return new BetCoordinates(betX, betY);
	}

	private BetCoordinates getValueUpperLeftCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getInfoWidth()));

		Integer betY = browserDimensions.y + Math.round(browserDimensions.height * vbConstants.getValueInfoHeight());

		return new BetCoordinates(betX, betY);
	}

	private BetCoordinates getCancelButtonCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getCancelButtonWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * vbConstants.getBrowserButtonsHeight());

		return new BetCoordinates(betX, betY);
	}

	private BetCoordinates getConfirmButtonCoordinates() {
		Integer betX = (int) (browserDimensions.x
				+ Math.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getConfirmButtonWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * vbConstants.getBrowserButtonsHeight());

		return new BetCoordinates(betX, betY);
	}

	private BetCoordinates getConfirmOkButtonCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * vbConstants.getConfirmOkButtonWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * vbConstants.getConfirmOkButtonHeight());

		return new BetCoordinates(betX, betY);
	}
}
