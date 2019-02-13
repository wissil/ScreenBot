package com.util.ai.screenbot.input.logic.value.betting;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.sikuli.script.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.value.betting.AbstractVBConstants;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.exceptions.GuiElementNotFoundException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.utils.SikuliUtils;

public class VBMainInputBot extends VBInputBot {

	protected static final Logger log = LoggerFactory.getLogger(VBMainInputBot.class);

	private Rectangle appDimensions;

	public VBMainInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractVBConstants vbConstants) {
		super(keyboardHandler, screenHandler, mouseHandler, vbConstants);
	}

	public void initializeValueBetting() {
		App.focus("ValueBetting");
		// Initialize Value Betting screen dimensions
		this.appDimensions = checkScreen();
	}

	public Boolean checkTopBet() {

		BotCoordinates betCoordinates = getTopBetMiddleCoordinates();

		mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
		mouseHandler.leftClick();

		Color color = screenHandler.detectColor(betCoordinates.x, betCoordinates.y);

		Boolean topBetExists = !color.equals(Color.WHITE);

		log.debug("Top bet exists? --> " + topBetExists);
		return topBetExists;
	}

	public BufferedImage takeTopBetScreenshot() throws FatalVBException {
		log.debug("Taking top bet screenshot ...");
		
		try {
			return SikuliUtils.getImageBelowElement("SingleBet_Header.png", 20);
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Top bet image not found.", e);
		}
	}

	/**
	 * Place mouse cursor in the middle of top bet. Right click. Wait. Left click
	 * 
	 * @throws FatalVBException
	 */
	public void betOnTopBet() throws FatalVBException {
		BotCoordinates betCoordinates = getTopBetMiddleCoordinates();

		mouseHandler.moveMouse(betCoordinates.x, betCoordinates.y);
		mouseHandler.leftClick();

		log.debug("Clicking the bet on top ...");
		
		try {
			SikuliUtils.clickOnElement("Bet.png");
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Bet on top not found.", e);
		}
	}

	/**
	 * Place mouse cursor in the middle of top bet. Right click. Move it a bit lower
	 * and then right. Left click afterwards.
	 */
	public void removeTopBet() {

		BotCoordinates betCoordinates = getTopBetMiddleCoordinates();

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

		mouseHandler.leftClick();
	}

	public void removeAllBetsFromTopBetEvent() throws FatalVBException {
		log.debug("Removing bets from the top event ...");
		
		try {
			SikuliUtils.clickOnElement("Bet.png");
			mouseHandler.rightClick();

			SikuliUtils.clickOnElement("Hide.png");
			SikuliUtils.clickOnElement("AllBetsForSelectedMatch.png");
		} catch (GuiElementNotFoundException e) {
			throw new FatalVBException("Could not remove bets from the top event ...", e);
		}
	}

	private BotCoordinates getTopBetMiddleCoordinates() {
		Integer betX = (int) (appDimensions.x
				+ Math.round(ScreenConfig.screenCoef * appDimensions.width * vbConstants.getTopBetMiddleWidth()));

		Integer betY = appDimensions.y + Math.round(appDimensions.height * vbConstants.getTopBetMiddleHeight());

		log.debug("topBetMiddleX: " + betX);
		log.debug("topBetMiddleY: " + betY);

		return new BotCoordinates(betX, betY);
	}
}
