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
			Thread.sleep(200);
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
		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_ActiveCursor.png", String.valueOf(stake)))
			return;
		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_InactiveCursor.png", String.valueOf(stake)))
			return;
		if (SikuliUtils.writeToElement("marathon/Marathon_InputStake_Text.png", String.valueOf(stake)))
			return;
		throw new FatalVBException("Could not find input stake element.");
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
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_MaxStake", 44);
	}

	public BufferedImage takeMinStakeScreenshot() throws FatalVBException {
		return SikuliUtils.getImageRigtToElement("marathon/Marathon_MinStake", 44);
	}

	private BotCoordinates getBettingSlipCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBettingSlipWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBettingSlipHeight());

		return new BotCoordinates(betX, betY);
	}

	private BotCoordinates getBalanceCoordinates() {
		Integer betX = (int) (browserDimensions.x + Math
				.round(ScreenConfig.screenCoef * browserDimensions.width * marathonbetConstants.getBalanceWidth()));

		Integer betY = browserDimensions.y
				+ Math.round(browserDimensions.height * marathonbetConstants.getBalanceHeight());

		return new BotCoordinates(betX, betY);
	}
}