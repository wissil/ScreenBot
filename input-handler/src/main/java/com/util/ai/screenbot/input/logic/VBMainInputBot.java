package com.util.ai.screenbot.input.logic;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.AbstractVBConstants;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;

public class VBMainInputBot extends VBInputBot {

	protected static final Logger log = LoggerFactory.getLogger(VBMainInputBot.class);

	private Rectangle appDimensions;

	public VBMainInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractVBConstants vbConstants) {
		super(keyboardHandler, screenHandler, mouseHandler, vbConstants);
	}

	public void initializeValueBetting() {
		initialize(AbstractVBConstants.VALUE_BETTING_APP_PREFIX);

		// Initialize Value Betting screen dimensions
		this.appDimensions = checkScreen();
	}

	public void navigateToTopBetUpperLeft() {

		BetCoordinates betCoordinates = getTopBetTopLeftCornerCoordinates();

		mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
	}

	public void navigateToTopBetLowerLeft() {

		BetCoordinates betCoordinates = getTopBetLowerLeftCornerCoordinates();

		mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
	}

	public void navigateToTopBetMiddle() {

		BetCoordinates betCoordinates = getTopBetMiddleCoordinates();

		mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
	}

	public Boolean checkTopBet() {

		BetCoordinates betCoordinates = getTopBetMiddleCoordinates();

		mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
		mouseHandler.leftClick();

		Color color = screenHandler.detectColor(betCoordinates.x, betCoordinates.y);

		Boolean topBetExists = !color.equals(Color.WHITE);

		log.debug("Top bet exists? --> " + topBetExists);
		return topBetExists;
	}

	public BufferedImage takeTopBetScreenshot() {

		BetCoordinates betCoordinates = getTopBetTopLeftCornerCoordinates();

		BufferedImage image = screenHandler.takeScreenshot(betCoordinates.x, betCoordinates.y,
				(int) Math.round(ScreenConfig.width * vbConstants.getBetScreenshotWidth()),
				(int) Math.round(ScreenConfig.height * vbConstants.getBetScreenshotHeight()));

		return image;

	}

	/**
	 * Place mouse cursor in the middle of top bet. Right click. Wait. Left click
	 */
	public void betOnTopBet() {
		BetCoordinates betCoordinates = getTopBetMiddleCoordinates();

		mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
		mouseHandler.leftClick();

		mouseHandler.rightClick();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// Do nothing
		}
		mouseHandler.leftClick();
	}

	/**
	 * Place mouse cursor in the middle of top bet. Right click. Move it a bit lower
	 * and then right. Left click afterwards.
	 */
	public void removeTopBet() {

		BetCoordinates betCoordinates = getTopBetMiddleCoordinates();

		mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
		mouseHandler.leftClick();

		mouseHandler.rightClick();
		Point p = MouseInfo.getPointerInfo().getLocation();

		int newY = p.y + (int) Math.round(ScreenConfig.height * vbConstants.getRemoveBetMouseMovementHeight());

		mouseHandler.moveMouse(p.x, newY);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Do nothing
		}

		int newX = p.x + (int) Math.round(ScreenConfig.width * vbConstants.getRemoveBetMouseMovementWidth());

		mouseHandler.moveMouse(newX, newY);

		// mouseHandler.leftClick(); FIXME - test purposes
	}

	private BetCoordinates getTopBetMiddleCoordinates() {
		Integer betX = (int) (appDimensions.x
				+ Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetMiddleWidth()));

		Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetMiddleHeight());

		log.debug("topBetMiddleX: " + betX);
		log.debug("topBetMiddleY: " + betY);

		return new BetCoordinates(betX, betY);
	}

	private BetCoordinates getTopBetTopLeftCornerCoordinates() {
		Integer betX = (int) (appDimensions.x
				+ Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetCornerWidth()));

		Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetUpperCornerHeight());

		log.debug("topBetLeftUpperCornerX: " + betX);
		log.debug("topBetLeftUpperCornerY: " + betY);

		return new BetCoordinates(betX, betY);
	}

	private BetCoordinates getTopBetLowerLeftCornerCoordinates() {
		Integer betX = (int) (appDimensions.x
				+ Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetCornerWidth()));

		Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetLowerCornerHeight());

		log.debug("topBetLeftLowerCornerX: " + betX);
		log.debug("topBetLeftLowerCornerY: " + betY);

		return new BetCoordinates(betX, betY);
	}

}
