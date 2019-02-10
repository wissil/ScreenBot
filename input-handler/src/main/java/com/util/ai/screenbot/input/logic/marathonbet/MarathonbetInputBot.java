package com.util.ai.screenbot.input.logic.marathonbet;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.ai.screenbot.input.config.ScreenConfig;
import com.util.ai.screenbot.input.constants.marathonbet.AbstractMarathonbetConstants;
import com.util.ai.screenbot.input.exceptions.BetException;
import com.util.ai.screenbot.input.exceptions.BetNotFoundException;
import com.util.ai.screenbot.input.exceptions.BetSlipException;
import com.util.ai.screenbot.input.exceptions.FatalVBException;
import com.util.ai.screenbot.input.handlers.keyboard.KeyboardHandler;
import com.util.ai.screenbot.input.handlers.mouse.MouseHandler;
import com.util.ai.screenbot.input.handlers.screen.ScreenHandler;
import com.util.ai.screenbot.input.logic.AbstractInputBot;
import com.util.ai.screenbot.input.utils.SikuliUtils;
import com.util.ai.screenbot.support.colors.ColorComparator;

public class MarathonbetInputBot extends AbstractInputBot {

	protected static final Logger log = LoggerFactory.getLogger(MarathonbetInputBot.class);

	protected AbstractMarathonbetConstants marathonbetConstants;

	private Rectangle browserDimensions;

	private Integer buttonDeviation = 0; // If bet slip is shorter because of short name

	public MarathonbetInputBot(KeyboardHandler keyboardHandler, ScreenHandler screenHandler, MouseHandler mouseHandler,
			AbstractMarathonbetConstants marathonbetConstants) {
		super(keyboardHandler, screenHandler, mouseHandler);
		this.marathonbetConstants = Objects.requireNonNull(marathonbetConstants);
	}

	public void initialize(Rectangle browserDimensions) {
		this.browserDimensions = browserDimensions;
	}

	public void clickBettingSlip() throws FatalVBException {
		SikuliUtils.clickOnElement("marathon/Marathon_BetSlip.png");
	}

	public void checkBettingSlip() throws BetSlipException, BetNotFoundException, FatalVBException {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// Do nothing
		}

		BotCoordinates bettingSlipButtonCoordinates = getBettingSlipCoordinates();

		Color bettingSlipButtonColor = screenHandler.detectColor(bettingSlipButtonCoordinates.x,
				bettingSlipButtonCoordinates.y);
		log.info("Bettin slip color: " + bettingSlipButtonColor.toString());

		if (!ColorComparator.areEqualColors(bettingSlipButtonColor, marathonbetConstants.getMarathonbetGreen(), 0)) { // No
			// deviation
			clickBettingSlip();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// Do nothing
		}
		bettingSlipButtonColor = screenHandler.detectColor(bettingSlipButtonCoordinates.x,
				bettingSlipButtonCoordinates.y);
		log.info("Bettin slip color: " + bettingSlipButtonColor.toString());

		// Check if betting slip button is green
		if (!ColorComparator.areEqualColors(bettingSlipButtonColor, marathonbetConstants.getMarathonbetGreen(), 0)) { // No
			// deviation
			log.info("Slip not correct");
			throw new BetNotFoundException("There is no bet in betting slip");
		}

		BotCoordinates removeAllButtonCoordinates = getRemoveAllButtonCoordinates();

		Color removeAllButtonColor = screenHandler.detectColor(removeAllButtonCoordinates.x,
				removeAllButtonCoordinates.y);

		log.info("RemoveAll button color:" + removeAllButtonColor.toString());

		// Check if remove all button is red
		if (!ColorComparator.areEqualColors(removeAllButtonColor, marathonbetConstants.getMarathonbetRed().get(0),
				marathonbetConstants.getColorDeviation())) {

			boolean foundRemoveAllButton = false;

			for (int step = 1; step <= 5; step++) {

				int deviation = step * marathonbetConstants.getDeviation();

				removeAllButtonColor = screenHandler.detectColor(removeAllButtonCoordinates.x,
						removeAllButtonCoordinates.y - deviation);

				log.info("Upper removeAll button color:" + removeAllButtonColor.toString());

				if (ColorComparator.areEqualColors(removeAllButtonColor,
						marathonbetConstants.getMarathonbetRed().get(0), marathonbetConstants.getColorDeviation())) {
					foundRemoveAllButton = true;
					this.buttonDeviation = deviation * -1;
					break;
				} else {
					removeAllButtonColor = screenHandler.detectColor(removeAllButtonCoordinates.x,
							removeAllButtonCoordinates.y + deviation);

					log.info("Lower removeAll button color:" + removeAllButtonColor.toString());

					if (ColorComparator.areEqualColors(removeAllButtonColor,
							marathonbetConstants.getMarathonbetRed().get(0),
							marathonbetConstants.getColorDeviation())) {
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

		log.info("Bet slip OK!");
	}

	public void clickRemoveAll() throws FatalVBException {
		SikuliUtils.clickOnElement("marathon/Marathon_RemoveAll.png");
	}

	public void clickBet() throws BetException, FatalVBException {
		SikuliUtils.clickOnElement("marathon/Marathon_PlaceBet.png");
		SikuliUtils.clickOnElement("marathon/Marathon_OK.png");
	}

	public void setBetStake(String stake) throws FatalVBException {
		try {
			SikuliUtils.writeToElement("marathon/Marathon_BetInput.png", String.valueOf(stake));
		} catch (FatalVBException e) {
			SikuliUtils.writeToElement("marathon/Marathon_InputBetText.png", String.valueOf(stake));
		}
	}

	public BufferedImage takeBookmakerOddsScreenshot() throws FatalVBException {
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_OddsInput.png", 40);
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

	public BufferedImage takeMaxStakeScreenshot() throws FatalVBException {
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_MaxStake", 40);
	}

	public BufferedImage takeMinStakeScreenshot() throws FatalVBException {
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_MinStake", 40);
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

	private BotCoordinates getBalanceCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBalanceWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBalanceHeight());

		return new BotCoordinates(betX, betY);
	}
}