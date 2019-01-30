package com.util.ai.screenbot.input.logic.marathonbet;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.marathonbet.AbstractMarathonbetConstants;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalValueBettingException;
import com.util.ai.screenbot.input.exceptions.NoBetFoundException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.AbstractInputBot;

public class MarathonbetInputBot extends AbstractInputBot {

	protected static final Logger log = LoggerFactory.getLogger(MarathonbetInputBot.class);

	protected AbstractMarathonbetConstants marathonbetConstants;

	private Rectangle browserDimensions;

	private Integer buttonDeviation = 0; // If bet slip is shorter because of short name
	private Integer stakeDeviation = 0; // for some reason it can also go up

	public MarathonbetInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractMarathonbetConstants marathonbetConstants) {
		super(keyboardHandler, screenHandler, mouseHandler);
		this.marathonbetConstants = Objects.requireNonNull(marathonbetConstants);
	}

	public void initialize(Rectangle browserDimensions) {
		this.browserDimensions = browserDimensions;
		buttonDeviation = 0;
		stakeDeviation = 0;
	}

	public void navigateToBettingSlipButton() {

		BotCoordinates bettingSlipButtonCoordinates = getBettingSlipCoordinates();

		mouseHandler.moveMouse(bettingSlipButtonCoordinates.x, bettingSlipButtonCoordinates.y);
	}

	public void clickBettingSlip() {
		navigateToBettingSlipButton();

		mouseHandler.leftClick();
	}

	public void checkBettingSlip() throws BetSlipException, NoBetFoundException {

		BotCoordinates bettingSlipButtonCoordinates = getBettingSlipCoordinates();

		Color bettingSlipButtonColor = screenHandler.detectColor(bettingSlipButtonCoordinates.x,
				bettingSlipButtonCoordinates.y);
		log.info("Bettin slip color: " + bettingSlipButtonColor.toString());

		if (bettingSlipButtonColor.equals(marathonbetConstants.getMarathonbetLightGreen())) {
			clickBettingSlip();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// Do nothing
			}
			bettingSlipButtonColor = screenHandler.detectColor(bettingSlipButtonCoordinates.x,
					bettingSlipButtonCoordinates.y);
			log.info("Bettin slip color: " + bettingSlipButtonColor.toString());
		}

		// Check if betting slip button is green
		if (!bettingSlipButtonColor.equals(marathonbetConstants.getMarathonbetGreen())) {

			log.info("Slip not correct");
			throw new NoBetFoundException("There is no bet in betting slip");
		}

		BotCoordinates removeAllButtonCoordinates = getRemoveAllButtonCoordinates();
		BotCoordinates betButtonCoordinates = getBetButtonCoordinates();

		Color removeAllButtonColor = screenHandler.detectColor(removeAllButtonCoordinates.x,
				removeAllButtonCoordinates.y);

		log.info("RemoveAll button color:" + removeAllButtonColor.toString());

		// Check if remove all button is red
		if (!marathonbetConstants.getMarathonbetRed().contains(removeAllButtonColor)) {

			boolean foundRemoveAllButton = false;

			for (int step = 1; step <= 5; step++) {

				int deviation = step * marathonbetConstants.getDeviation();

				removeAllButtonColor = screenHandler.detectColor(removeAllButtonCoordinates.x,
						removeAllButtonCoordinates.y - deviation);

				log.info("Upper removeAll button color:" + removeAllButtonColor.toString());

				if (marathonbetConstants.getMarathonbetRed().contains(removeAllButtonColor)) {
					foundRemoveAllButton = true;
					this.buttonDeviation = deviation * -1;
					break;
				} else {
					removeAllButtonColor = screenHandler.detectColor(removeAllButtonCoordinates.x,
							removeAllButtonCoordinates.y + deviation);

					log.info("Lower removeAll button color:" + removeAllButtonColor.toString());

					if (marathonbetConstants.getMarathonbetRed().contains(removeAllButtonColor)) {
						foundRemoveAllButton = true;
						this.buttonDeviation = deviation;
						break;
					}
				}
			}

			if (!foundRemoveAllButton) {
				this.buttonDeviation = 0;
				log.info("Remove button not correct");
				throw new BetSlipException("RemoveAll button is not positioned correctly");
			}

		}

		Color betButtonColor = screenHandler.detectColor(betButtonCoordinates.x,
				betButtonCoordinates.y + this.buttonDeviation);

		// Check if betting button is green
		if (!betButtonColor.equals(marathonbetConstants.getMarathonbetGreen())) {
			log.info("Bet button not correct");
			throw new BetSlipException("Bet button is not positioned correctly");
		}

		BotCoordinates stakeInputCoordinates = getBetInputStakeCoordinates();

		Color stakeInputColor = screenHandler.detectColor(stakeInputCoordinates.x, stakeInputCoordinates.y);

		// Check if input stake is white
		if (!stakeInputColor.equals(marathonbetConstants.getMarathonbetWhite())) {

			stakeInputColor = screenHandler.detectColor(stakeInputCoordinates.x,
					stakeInputCoordinates.y - marathonbetConstants.getDeviation());

			log.info("Upper stake input color:" + stakeInputColor.toString());
			this.stakeDeviation = marathonbetConstants.getDeviation() * -1;

			if (!stakeInputColor.equals(marathonbetConstants.getMarathonbetWhite())) {

				stakeInputColor = screenHandler.detectColor(stakeInputCoordinates.x,
						stakeInputCoordinates.y + marathonbetConstants.getDeviation());

				log.info("Lower stake input color:" + stakeInputColor.toString());
				this.stakeDeviation = marathonbetConstants.getDeviation();

				if (!stakeInputColor.equals(marathonbetConstants.getMarathonbetWhite())) {
					this.buttonDeviation = 0;
					log.info("Remove button not correct");
					throw new BetSlipException("Stake input field is not positioned correctly");
				}
			}

		}

		log.info("Bet slip OK!");

	}

	public void navigateToRemoveAllButton() {

		BotCoordinates removeAllButtonCoordinates = getRemoveAllButtonCoordinates();

		mouseHandler.moveMouse(removeAllButtonCoordinates.x, removeAllButtonCoordinates.y);
	}

	public void clickRemoveAll() throws FatalValueBettingException {

		BotCoordinates removeAllButtonCoordinates = getRemoveAllButtonCoordinates();

		Color removeAllButtonColor = screenHandler.detectColor(removeAllButtonCoordinates.x,
				removeAllButtonCoordinates.y);

		// Check if remove all button is red
		if (marathonbetConstants.getMarathonbetRed().contains(removeAllButtonColor)) {
			mouseHandler.moveMouse(removeAllButtonCoordinates.x, removeAllButtonCoordinates.y);
			mouseHandler.leftClick();
		} else {
			throw new FatalValueBettingException("Not able to removeAll");
		}

	}

	public void navigateToBetButton() {

		BotCoordinates betButtonCoordinates = getBetButtonCoordinates();

		mouseHandler.moveMouse(betButtonCoordinates.x, betButtonCoordinates.y);
	}

	public void clickBet() {
		navigateToBetButton();

		mouseHandler.leftClick();
	}

	public void navigateToStakeInputButton() {

		BotCoordinates stakeInputCoordinates = getBetInputStakeCoordinates();

		mouseHandler.moveMouse(stakeInputCoordinates.x, stakeInputCoordinates.y);
	}

	public void setBetStake(String stake) {
		navigateToStakeInputButton();
		mouseHandler.leftClick();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// Do nothing
		}
		keyboardHandler.write(stake);
	}

	public void navigateToOddsInputButton() {

		BotCoordinates oddsInputCoordinates = getBetInputOddsCoordinates();

		mouseHandler.moveMouse(oddsInputCoordinates.x, oddsInputCoordinates.y);
	}

	public BufferedImage takeBookmakerOddsScreenshot() {
		BotCoordinates oddsInputCoordinates = getBetInputOddsCoordinates();

		BufferedImage image = screenHandler.takeScreenshot(oddsInputCoordinates.x, oddsInputCoordinates.y,
				(int) Math.round(ScreenConfig.width * marathonbetConstants.getOddsScreenshotWidth()),
				(int) Math.round(ScreenConfig.height * marathonbetConstants.getOddsScreenshotHeight()));

		return image;
	}

	public void navigateToNeutralClick() {

		BotCoordinates neutralClickCoordinates = getNeutralClickCoordinates();

		mouseHandler.moveMouse(neutralClickCoordinates.x, neutralClickCoordinates.y);
	}

	public void neutralClick() {

		navigateToStakeInputButton();

		mouseHandler.leftClick();
	}

	public void navigateToMinStake() {

		BotCoordinates minStakeCoordinates = getMinStakeCoordinates();

		mouseHandler.moveMouse(minStakeCoordinates.x, minStakeCoordinates.y);
	}

	public void navigateToMaxStake() {

		BotCoordinates maxStakeCoordinates = getMaxStakeCoordinates();

		mouseHandler.moveMouse(maxStakeCoordinates.x, maxStakeCoordinates.y);
	}

	public void navigateToBalance() {

		BotCoordinates balanceCoordinates = getBalanceCoordinates();

		mouseHandler.moveMouse(balanceCoordinates.x, balanceCoordinates.y);
	}

	public BufferedImage takeBalanceScreenshot() {
		BotCoordinates balanceCoordinates = getBalanceCoordinates();

		BufferedImage image = screenHandler.takeScreenshot(balanceCoordinates.x, balanceCoordinates.y,
				(int) Math.round(ScreenConfig.width * marathonbetConstants.getBalanceScreenshotWidth()),
				(int) Math.round(ScreenConfig.height * marathonbetConstants.getBalanceScreenshotHeight()));

		return image;
	}

	public BufferedImage takeMaxStakeScreenshot() {
		BotCoordinates maxStakeCoordinates = getMaxStakeCoordinates();

		BufferedImage image = screenHandler.takeScreenshot(maxStakeCoordinates.x, maxStakeCoordinates.y,
				(int) Math.round(ScreenConfig.width * marathonbetConstants.getMinMaxStakeScreenshotWidth()),
				(int) Math.round(ScreenConfig.height * marathonbetConstants.getMinMaxStakeScreenshotHeight()));

		return image;
	}

	public BufferedImage takeMinStakeScreenshot() {
		BotCoordinates minStakeCoordinates = getMinStakeCoordinates();

		BufferedImage image = screenHandler.takeScreenshot(minStakeCoordinates.x, minStakeCoordinates.y,
				(int) Math.round(ScreenConfig.width * marathonbetConstants.getMinMaxStakeScreenshotWidth()),
				(int) Math.round(ScreenConfig.height * marathonbetConstants.getMinMaxStakeScreenshotHeight()));

		return image;
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

		return new BotCoordinates(betX, betY + this.buttonDeviation);
	}

	private BotCoordinates getBetButtonCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBetButtonWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBettingButtonsHeight());

		return new BotCoordinates(betX, betY + this.buttonDeviation);
	}

	private BotCoordinates getBetInputStakeCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math.round(
				ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBetInputStakeWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBetInputStakeHeight());

		return new BotCoordinates(betX, betY + this.stakeDeviation);
	}

	private BotCoordinates getBetInputOddsCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math.round(
				ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBetInputOddsWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBetInputOddsHeight());

		return new BotCoordinates(betX, betY + this.stakeDeviation);
	}

	private BotCoordinates getNeutralClickCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math.round(
				ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getNeutralClickWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getNeutralClickHeight());

		return new BotCoordinates(betX, betY);
	}

	private BotCoordinates getMaxStakeCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getMaxStakeWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getMinMaxStakeHeight());

		return new BotCoordinates(betX, betY + this.buttonDeviation);
	}

	private BotCoordinates getMinStakeCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getMinStakeWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getMinMaxStakeHeight());

		return new BotCoordinates(betX, betY + this.buttonDeviation);
	}

	private BotCoordinates getBalanceCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBalanceWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBalanceHeight());

		return new BotCoordinates(betX, betY);
	}
}