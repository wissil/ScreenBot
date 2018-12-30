package com.util.ai.screenbot.input.logic.marathonbet;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.marathonbet.AbstractMarathonbetConstants;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.AbstractInputBot;

public class MarathonbetInputBot extends AbstractInputBot {

	protected static final Logger log = LoggerFactory.getLogger(MarathonbetInputBot.class);

	protected AbstractMarathonbetConstants marathonbetConstants;

	private Rectangle browserDimensions;

	public MarathonbetInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractMarathonbetConstants marathonbetConstants) {
		super(keyboardHandler, screenHandler, mouseHandler);
		this.marathonbetConstants = Objects.requireNonNull(marathonbetConstants);
	}

	public void initialize(Rectangle browserDimensions) {
		this.browserDimensions = browserDimensions;
	}

	public void navigateToBettingSlipButton() {

		BotCoordinates bettingSlipButtonCoordinates = getBettingSlipCoordinates();

		mouseHandler.moveMouse(bettingSlipButtonCoordinates.x, bettingSlipButtonCoordinates.y);
	}

	public void clickBettingSlip() {
		navigateToBettingSlipButton();

		mouseHandler.leftClick();
	}

	public Boolean checkBettingSlip() {

		BotCoordinates bettingSlipButtonCoordinates = getBettingSlipCoordinates();

		Color color = screenHandler.detectColor(bettingSlipButtonCoordinates.x, bettingSlipButtonCoordinates.y);
		log.info(color.toString());

		// Check if betting slip button is green
		if (color.getRed() != 0 && color.getGreen() != 143 && color.getBlue() != 76) {
			log.info("Bet not correct");
			return false;
		}

		return true;
	}

	public void navigateToRemoveAllButton() {

		BotCoordinates removeAllButtonCoordinates = getRemoveAllButtonCoordinates();

		mouseHandler.moveMouse(removeAllButtonCoordinates.x, removeAllButtonCoordinates.y);
	}

	public void navigateToBetButton() {

		BotCoordinates betButtonCoordinates = getBetButtonCoordinates();

		mouseHandler.moveMouse(betButtonCoordinates.x, betButtonCoordinates.y);
	}

	private BotCoordinates getBettingSlipCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBettingSlipWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBettingSlipHeight());

		return new BotCoordinates(betX, betY);
	}

	private BotCoordinates getRemoveAllButtonCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math.round(
				ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getRemoveAllButtonWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBettingButtonsHeight());

		return new BotCoordinates(betX, betY);
	}

	private BotCoordinates getBetButtonCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBetButtonWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBettingButtonsHeight());

		return new BotCoordinates(betX, betY);
	}

}
